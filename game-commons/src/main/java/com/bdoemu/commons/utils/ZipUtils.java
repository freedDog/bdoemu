package com.bdoemu.commons.utils;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ZipUtils
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 18:08
 * VERSION 1.0
 */

public class ZipUtils {

    public static FileSystem createZipFileSystem(final String zipFilename, final boolean create) throws IOException {
        final Path path = Paths.get(zipFilename, new String[0]);
        final URI uri = URI.create("jar:file:" + path.toUri().getPath());
        final Map<String, String> env = new HashMap<String, String>();
        if (create) {
            env.put("create", "true");
        }
        return FileSystems.newFileSystem(uri, env);
    }
}
