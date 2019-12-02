package com.bdoemu.jme3.collision;

/**
 * @ClassName UnsupportedCollisionException
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/11 16:18
 * VERSION 1.0
 */

public class UnsupportedCollisionException extends UnsupportedOperationException{
    public UnsupportedCollisionException(Throwable arg0) {
        super(arg0);
    }

    public UnsupportedCollisionException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public UnsupportedCollisionException(String arg0) {
        super(arg0);
    }

    public UnsupportedCollisionException() {
    }
}
