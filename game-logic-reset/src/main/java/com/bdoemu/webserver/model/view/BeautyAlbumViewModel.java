package com.bdoemu.webserver.model.view;

import java.util.Map;

/**
 * @ClassName BeautyAlbumViewModel
 * @Description 美容相册视图模型
 * @Author JiangBangMing
 * @Date 2019/7/11 15:48
 * VERSION 1.0
 */

public class BeautyAlbumViewModel extends BaseViewModel{

    private String userNickname;
    private int classType;
    private boolean isCustomizationMode;

    public BeautyAlbumViewModel(final Map<String, String[]> paramMap) {
        super(paramMap);
    }
}
