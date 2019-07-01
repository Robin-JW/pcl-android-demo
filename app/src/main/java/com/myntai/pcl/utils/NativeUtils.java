package com.myntai.pcl.utils;

public class NativeUtils {

    public static native void test();

    static {
        System.loadLibrary("native-jni");
    }

}
