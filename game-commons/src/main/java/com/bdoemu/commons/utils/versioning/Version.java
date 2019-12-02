package com.bdoemu.commons.utils.versioning;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

/**
 * @ClassName Version
 * @Description 版本
 * @Author JiangBangMing
 * @Date 2019/6/22 17:48
 * VERSION 1.0
 */

public class Version {

    private static final Logger log = LoggerFactory.getLogger(Version.class);
    private static final AtomicReference<Object> instance = new AtomicReference();
    private String versionRevision;
    private String buildDate = "";
    private String buildJdk = "";

    public static Version getInstance() {
        Object value = instance.get();
        if (value == null){
            synchronized (instance) {
                value = instance.get();
                if (value == null) {
                    Version actualValue = new Version();
                    value = (actualValue == null) ? instance : actualValue;
                    instance.set(value);
                }
            }
        }
        return (Version)((value == instance) ? null : value);
    }

    public void init(Class<?> c) {
        File jarName = null;
        try {
            jarName = Locator.getClassSource(c);
            if (StringUtils.endsWith(jarName.getName(), ".jar")) {
                JarFile jarFile = new JarFile(jarName);
                Attributes attrs = jarFile.getManifest().getMainAttributes();
                setBuildJdk(attrs);
                setBuildDate(attrs);
                setVersionRevision(attrs);
            }
            else {
                this.versionRevision = "Dev";
                this.buildDate = "N/A";
                this.buildJdk = System.getProperty("java.version");
            }
        } catch (IOException e) {
            log.error("Unable to get soft information\nFile name '" + jarName.getAbsolutePath() + "' isn't a valid jar", e);
        } finally {
            info();
        }
    }

    private void setVersionRevision(Attributes attrs) {
        String versionRevision = attrs.getValue("Implementation-Version");
        if (versionRevision != null && !versionRevision.isEmpty()) {
            this.versionRevision = versionRevision;
        } else {
            this.versionRevision = "N/A";
        }
    }

    private void setBuildJdk(Attributes attrs) {
        String buildJdk = attrs.getValue("Built-JDK");
        if (buildJdk != null) {
            this.buildJdk = buildJdk;
        } else {
            buildJdk = attrs.getValue("Source-Compatibility");
            if (buildJdk != null) {
                this.buildJdk = buildJdk;
            } else {
                this.buildJdk = "-1";
            }
        }
    }

    private void setBuildDate(Attributes attrs) {
        String buildDate = attrs.getValue("Built-Date");
        if (buildDate != null) {
            this.buildDate = buildDate;
        } else {
            this.buildDate = "-1";
        }
    }

    public String getVersionRevision() {
        return this.versionRevision;
    }

    public String getBuildDate() {
        return this.buildDate;
    }

    public String getBuildJdk() {
        return this.buildJdk;
    }

    private void info() {
        log.info("=================================================");
        log.info("Revision: ................ " + getVersionRevision());
        log.info("Build date: .............. " + getBuildDate());
        log.info("=================================================");
    }
    @Override
    public String toString() {
        return "Version " + this.versionRevision + " from " + this.buildDate;
    }
}
