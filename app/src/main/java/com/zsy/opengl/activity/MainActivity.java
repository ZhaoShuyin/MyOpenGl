package com.zsy.opengl.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zsy.opengl.R;
import com.zsy.opengl.render20.AirHockeyRenderer;
import com.zsy.opengl.render20.MyRenderer;

/**
 * Open Graphics Library (开放图形库)
 * https://www.wanandroid.com/article/list/0?cid=159
 * Android 1.0 开始支持 OpenGL ES 1.0 及 1.1
 * Android 2.2 开始支持 OpenGL ES 2.0
 * Android 4.3 开始支持 OpenGL ES 3.0
 * Android 5.0 开始支持 OpenGL ES 3.1
 */
public class MainActivity extends AppCompatActivity {


    private GLSurfaceView glSurfaceView;

    /**
     * @Title 尝试 Gl 渲染 波形图
     * @Date 2019/9/24
     * @Autor Zsy
     * 参考:
     * 0.导入使用
     * https://www.jianshu.com/p/6581703e1d98
     * 1.基本方法介绍
     * https://blog.csdn.net/luoshiyong123/article/details/82744611
     * 2.坐标系介绍
     * https://blog.csdn.net/qq_31726827/article/details/51265186
     * 3.平移和旋转
     * https://blog.csdn.net/digitalkee/article/details/79211295
     * 4.简单设置
     * https://www.cnblogs.com/arxive/p/7002305.html
     * 5.原理 openGL EL
     * https://wenku.baidu.com/view/27a8a847960590c69fc3767a.html
     * 6.多图层
     * https://blog.csdn.net/u010462297/article/details/50589991
     * 7.中文API
     * https://blog.csdn.net/flycatdeng/article/details/82588903
     * 8.学习网站
     * https://learnopengl-cn.readthedocs.io/zh/latest/
     * 9.glMatrixMode()方法
     * https://blog.csdn.net/jiangdf/article/details/8460012
     * 10.opengl官网
     * https://www.khronos.org/opengl/wiki
     * 11.帧缓存对象 FBO
     * https://blog.csdn.net/weixin_30772105/article/details/98417736
     * 12.绘制方式
     * https://blog.csdn.net/code09/article/details/41377847
     * 13.多层绘制
     * https://blog.csdn.net/u010462297/article/details/50589991
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* 以下是重点 */
        glSurfaceView = (GLSurfaceView) findViewById(R.id.glv_main_demo);
        // 设置OpenGL版本(一定要设置)
        glSurfaceView.setEGLContextClientVersion(2);
        // 基础渲染器
//        glSurfaceView.setRenderer(new MyRenderer());
        //桌子渲染器
        glSurfaceView.setRenderer(new AirHockeyRenderer(this));
        // 设置渲染模式为连续模式(会以60fps的速度刷新)
        //1.非主动渲染RENDERMODE_WHEN_DIRTY(0) 2.RENDERMODE_CONTINUOUSLY主动渲染(1)
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        /* 重点结束 */
    }


    /**
     * 判断设备是否支持 OpenGl 2.0
     */
    public boolean isSupport20() {
        final ActivityManager activityManager =
                (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();

        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
        return supportsEs2;
    }

}
