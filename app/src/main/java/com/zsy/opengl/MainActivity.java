package com.zsy.opengl;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * https://www.wanandroid.com/article/list/0?cid=159
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* 以下是重点 */
        GLSurfaceView demoGlv = (GLSurfaceView) findViewById(R.id.glv_main_demo);
        // 设置OpenGL版本(一定要设置)
        demoGlv.setEGLContextClientVersion(2);
        // 设置渲染器(后面会着重讲这个渲染器的类)
        demoGlv.setRenderer(new MyRenderer());
        // 设置渲染模式为连续模式(会以60fps的速度刷新)
        demoGlv.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        /* 重点结束 */
    }
}
