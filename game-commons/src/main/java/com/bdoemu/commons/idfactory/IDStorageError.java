package com.bdoemu.commons.idfactory;

/**
 * @ClassName IDStorageError
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/20 15:23
 * VERSION 1.0
 */

public class IDStorageError extends Error{

    public IDStorageError(){

    }

    public IDStorageError(String message){
        super(message);
    }

    public IDStorageError(String message,Throwable cause){
        super(message,cause);
    }

    public IDStorageError(Throwable cause){
        super(cause);
    }
}
