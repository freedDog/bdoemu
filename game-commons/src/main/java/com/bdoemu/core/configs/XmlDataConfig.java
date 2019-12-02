package com.bdoemu.core.configs;

import com.bdoemu.commons.config.annotation.ConfigComments;
import com.bdoemu.commons.config.annotation.ConfigFile;
import com.bdoemu.commons.config.annotation.ConfigProperty;

/**
 * @ClassName XmlDataConfig
 * @Description xml 配置文件
 * @Author JiangBangMing
 * @Date 2019/6/24 11:52
 * VERSION 1.0
 */
@ConfigFile(name = "configs/xmls.properties")
public class XmlDataConfig {
    @ConfigComments(comment = {"Xml files root directory path."})
    @ConfigProperty(name = "rootDir", value = "data/static_data/xml")
    public static String ROOT_DIR;
}
