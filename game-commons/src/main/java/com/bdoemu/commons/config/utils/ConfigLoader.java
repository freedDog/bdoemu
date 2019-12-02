package com.bdoemu.commons.config.utils;

import com.bdoemu.commons.config.annotation.ConfigAfterLoad;
import com.bdoemu.commons.config.annotation.ConfigComments;
import com.bdoemu.commons.config.annotation.ConfigFile;
import com.bdoemu.commons.config.annotation.ConfigProperty;
import com.bdoemu.commons.reload.IReloadable;
import com.bdoemu.commons.reload.Reloadable;
import com.bdoemu.core.startup.StartupComponent;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.atteo.classindex.ClassIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName ConfigLoader
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/15 17:24
 * VERSION 1.0
 */

@Reloadable(name = "all", group = "config")
@StartupComponent("Configure")
public final class ConfigLoader implements IReloadable {

    private static final Logger log;
    private static final AtomicReference<Object> instance;
    private static Set<String> VAR_NAMES_CACHE;

    private ConfigLoader() {
        this.loadConfigs();
    }

    private void loadConfigs() {
        for (final Class<?> clazz : ClassIndex.getAnnotated(ConfigFile.class)) {
            boolean loadConfig = false;
            final ConfigFile annotation = clazz.getAnnotation(ConfigFile.class);
            if (annotation.loadForPackages().length > 0) {
                for (final String classPath : annotation.loadForPackages()) {
                    if (!StringUtils.isEmpty(classPath)) {
                        final URL url = this.getClass().getClassLoader().getResource(classPath.replace(".", "/"));
                        if (url != null) {
                            loadConfig = true;
                            break;
                        }
                    }
                }
            }
            else {
                loadConfig = true;
            }
            if (!loadConfig) {
                continue;
            }
            final File file = new File(annotation.name());
            if (!file.exists() && file.isDirectory()) {
                file.mkdirs();
            }
            if (!file.exists()) {
                this.buildConfig(clazz);
            }
            else {
                this.updateConfig(clazz);
            }
            this.loadConfig(clazz);
        }
    }

    @Override
    public void reload() {
        this.loadConfigs();
    }

    private void updateConfig(final Class<?> clazz) {
        final Properties properties = new Properties();
        final String fileName = clazz.getAnnotation(ConfigFile.class).name();
        try (final InputStream input = new FileInputStream(fileName)) {
            properties.load(input);
        }
        catch (IOException ex) {
            ConfigLoader.log.error("Error while calling loadConfig", (Throwable)ex);
        }
        if (properties.size() == clazz.getDeclaredFields().length) {
            return;
        }
        final StrBuilder out = new StrBuilder();
        for (final Field field : clazz.getDeclaredFields()) {
            final ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
            final String propertyValue = properties.getProperty(annotation.name());
            if (propertyValue == null && !annotation.isMap()) {
                out.appendln("");
                out.appendln(this.generateFieldConfig(field));
                ConfigLoader.log.info("Updated '{}' config with new field '{}'", (Object)fileName, (Object)annotation.name());
            }
        }
        if (out.length() > 0) {
            final Path path = Paths.get(fileName, new String[0]);
            try {
                Files.write(path, out.toString().getBytes(), StandardOpenOption.APPEND);
            }
            catch (Exception e) {
                ConfigLoader.log.error("Error while writing config update", (Throwable)e);
            }
        }
    }

    private void buildConfig(final Class<?> clazz) {
        final String fileName = clazz.getAnnotation(ConfigFile.class).name();
        final Path path = Paths.get(fileName, new String[0]);
        ConfigLoader.log.info("Generated '{}'", (Object)fileName);
        try {
            Files.deleteIfExists(path);
            Files.createDirectories(path.getParent(), (FileAttribute<?>[])new FileAttribute[0]);
        }
        catch (IOException ex) {
            ConfigLoader.log.error("Error while buildConfig()", (Throwable)ex);
            return;
        }
        final StrBuilder out = new StrBuilder();
        for (final Field field : clazz.getDeclaredFields()) {
            final String configFields = this.generateFieldConfig(field);
            if (!configFields.isEmpty()) {
                out.appendln(configFields);
            }
        }
        try {
            Files.write(path, out.toString().getBytes(), StandardOpenOption.CREATE);
        }
        catch (IOException ex2) {
            ConfigLoader.log.error("Error while writing config file: " + path, (Throwable)ex2);
        }
    }

    private String generateFieldConfig(final Field field) {
        final ConfigComments configComments = field.getAnnotation(ConfigComments.class);
        final ConfigProperty configProperty = field.getAnnotation(ConfigProperty.class);
        final StrBuilder out = new StrBuilder();
        if (configComments != null && configComments.comment().length > 0) {
            for (final String txt : configComments.comment()) {
                out.appendln("# " + txt);
            }
        }
        if (ConfigLoader.VAR_NAMES_CACHE.contains(configProperty.name())) {
            ConfigLoader.log.warn("Config property name [{}] already defined!", (Object)configProperty.name());
        }
        else {
            ConfigLoader.VAR_NAMES_CACHE.add(configProperty.name());
        }
        if (configProperty.isMap()) {
            for (final String value : configProperty.values()) {
                out.appendln(value);
            }
        }
        else {
            out.appendln(configProperty.name() + " = " + configProperty.value());
        }
        return out.toString();
    }

    private void loadConfig(final Class<?> clazz) {
        final Properties properties = new Properties();
        final String fileName = clazz.getAnnotation(ConfigFile.class).name();
        ConfigLoader.log.info("Loading config file: {}", fileName);
        try (final InputStream input = new FileInputStream(fileName)) {
            properties.load(input);
        }
        catch (IOException ex) {
            ConfigLoader.log.error("Error while calling loadConfig",ex);
        }
        try {
            final Object configObject = clazz.newInstance();
            for (final Field field : clazz.getFields()) {
                final ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
                if (annotation != null) {
                    if (!Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                        ConfigLoader.log.warn("Invalid modifiers for {}", (Object)field);
                        return;
                    }
                    this.setConfigValue(configObject, field, properties, annotation);
                }
            }
            for (final Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(ConfigAfterLoad.class)) {
                    final boolean isMethodAccessible = method.isAccessible();
                    method.setAccessible(true);
                    try {
                        method.invoke(configObject, new Object[0]);
                    }
                    catch (Exception e) {
                        ConfigLoader.log.error("Error while calling ConfigAfterLoad method", (Throwable)e);
                    }
                    finally {
                        method.setAccessible(isMethodAccessible);
                    }
                }
            }
        }
        catch (Exception e2) {
            ConfigLoader.log.error("Error while initializing config object", (Throwable)e2);
        }
    }

    private void setConfigValue(final Object object, final Field field, final Properties properties, final ConfigProperty annotation) {
        final String propertyValue = properties.getProperty(annotation.name(), annotation.value());
        try {
            if (field.getType().isArray()) {
                final Class<?> baseType = field.getType().getComponentType();
                if (propertyValue != null) {
                    final String[] values = propertyValue.split(annotation.splitter());
                    final Object array = Array.newInstance(baseType, values.length);
                    field.set(null, array);
                    int index = 0;
                    for (final String arrValue : values) {
                        Array.set(array, index, ConfigTypeCaster.cast(baseType, arrValue.trim()));
                        ++index;
                    }
                    field.set(null, array);
                }
            }
            else if (field.getType().isAssignableFrom(List.class)) {
                final Class genericType = (Class)((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0];
                if (propertyValue != null) {
                    final String[] values = propertyValue.split(annotation.splitter());
                    ((List)field.get(object)).clear();
                    for (final String value : values) {
                        if (!value.trim().isEmpty()) {
                            ((List)field.get(object)).add(ConfigTypeCaster.cast((Class<Object>)genericType, value.trim()));
                        }
                    }
                }
            }
            else if (field.getType().isAssignableFrom(Map.class)) {
                ((Map)field.get(object)).clear();
                final Class valueType = (Class)((ParameterizedType)field.getGenericType()).getActualTypeArguments()[1];
                final String mapPrefix = annotation.name();
                for (final Map.Entry<Object, Object> entry : properties.entrySet()) {
                     String name = (String) entry.getKey();
                    if (name.startsWith(mapPrefix)) {
                        final String key = name.replace(mapPrefix + ".", "");
                        final Object value2 = ConfigTypeCaster.cast((Class<Object>)valueType, ((String)entry.getValue()).trim());
                        ((Map)field.get(object)).put(key, value2);
                    }
                }
            }
            else {
                ConfigTypeCaster.cast(object, field, propertyValue);
            }
        }
        catch (IllegalAccessException e) {
            ConfigLoader.log.error("Invalid modifiers for field {}", (Object)field);
        }
    }

    public static ConfigLoader getInstance() {
        Object value = ConfigLoader.instance.get();
        if (value == null) {
            synchronized (ConfigLoader.instance) {
                value = ConfigLoader.instance.get();
                if (value == null) {
                    final ConfigLoader actualValue = new ConfigLoader();
                    value = ((actualValue == null) ? ConfigLoader.instance : actualValue);
                    ConfigLoader.instance.set(value);
                }
            }
        }
        return (ConfigLoader)((value == ConfigLoader.instance) ? null : value);
    }

    static {
        log = LoggerFactory.getLogger(ConfigLoader.class);
        instance = new AtomicReference<>();
        ConfigLoader.VAR_NAMES_CACHE = new HashSet<>();
    }
}
