package com.zsy.opengl;

import android.app.Application;

/**
 * @Title com.zsy.opengl
 * @date 2019/11/8
 * @autor Zsy
 */

public class MyApplication extends Application {

    public static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
