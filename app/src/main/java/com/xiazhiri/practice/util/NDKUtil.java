package com.xiazhiri.practice.util;

/**
 * Created by liuwencai on 16/4/1.
 */
public class NDKUtil {
    static {
        System.loadLibrary("helloJni");
    }
    public native String getString();
}
