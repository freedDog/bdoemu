package com.bdoemu.commons.xml;

import com.bdoemu.commons.utils.ClassUtils;
import com.bdoemu.commons.xml.models.XmlDataHolder;
import com.bdoemu.core.configs.XmlDataConfig;
import com.bdoemu.core.startup.StartupComponent;
import org.apache.commons.lang3.StringUtils;
import org.atteo.classindex.ClassIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName XmlDataLoader
 * @Description XML 数据加载
 * @Author JiangBangMing
 * @Date 2019/6/24 11:40
 * VERSION 1.0
 */
@StartupComponent("Data")
public class XmlDataLoader {

    private static final Logger log;
    private static final AtomicReference<Object> instance;
    private static final Map<String, XmlDataHolder> holders;

    private XmlDataLoader() {
        for (final Class<?> holderClass : ClassIndex.getAnnotated(XmlDataHolderRoot.class)) {
            try {
                XmlDataLoader.holders.put(holderClass.getAnnotation(XmlDataHolderRoot.class).value(), (XmlDataHolder) ClassUtils.singletonInstance(holderClass));
            }
            catch (Exception e) {
                XmlDataLoader.log.error("Error while initialize XmlHolder [{}]", holderClass.getSimpleName(), e);
            }
        }
        this.load(null);
    }

    public void load(final Class<? extends XmlDataHolder> holderClass) {
        if (StringUtils.isEmpty(XmlDataConfig.ROOT_DIR)) {
            return;
        }
        try {
            final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setIgnoringComments(true);
            dbFactory.setIgnoringElementContentWhitespace(true);
            final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            final Path dir = Paths.get(XmlDataConfig.ROOT_DIR, new String[0]);
            this.directoryStream(dir, dBuilder, holderClass);
        }
        catch (ParserConfigurationException ex) {
            XmlDataLoader.log.error("Error while XML loading.", (Throwable)ex);
        }
    }

    private void directoryStream(final Path dir, final DocumentBuilder dBuilder, final Class holderClass) {
        if (!Files.exists(dir, new LinkOption[0])) {
            return;
        }
        try (final DirectoryStream<Path> ds = Files.newDirectoryStream(dir, new DirectoryStream.Filter<Path>() {
            FileSystem fs = dir.getFileSystem();
            final PathMatcher matcher = this.fs.getPathMatcher("glob:*.xml");

            @Override
            public boolean accept(final Path entry) throws IOException {
                return entry.toFile().isDirectory() || this.matcher.matches(entry.getFileName());
            }
        })) {
            for (final Path d : ds) {
                if (d.toFile().isDirectory()) {
                    this.directoryStream(d, dBuilder, holderClass);
                }
                else {
                    try {
                        final Document doc = dBuilder.parse(d.toFile());
                        final Element root = doc.getDocumentElement();
                        final XmlDataHolder holder = XmlDataLoader.holders.get(root.getNodeName());
                        if (holder == null || (holderClass != null && !holder.getClass().getSimpleName().equals(holderClass.getSimpleName()))) {
                            continue;
                        }
                        final NodeList nodeList = doc.getElementsByTagName(root.getNodeName());
                        holder.loadData(nodeList);
                    }
                    catch (SAXException ex) {
                        XmlDataLoader.log.error("Error while directoryStream().",ex);
                    }
                }
            }
        }
        catch (IOException e) {
            XmlDataLoader.log.error("Failed load file: ", e);
        }
    }

    public static XmlDataLoader getInstance() {
        Object value = XmlDataLoader.instance.get();
        if (value == null) {
            synchronized (XmlDataLoader.instance) {
                value = XmlDataLoader.instance.get();
                if (value == null) {
                    final XmlDataLoader actualValue = new XmlDataLoader();
                    value = ((actualValue == null) ? XmlDataLoader.instance : actualValue);
                    XmlDataLoader.instance.set(value);
                }
            }
        }
        return (XmlDataLoader)((value == XmlDataLoader.instance) ? null : value);
    }

    static {
        log = LoggerFactory.getLogger(XmlDataLoader.class);
        instance = new AtomicReference<>();
        holders = new HashMap<>();
    }
}
