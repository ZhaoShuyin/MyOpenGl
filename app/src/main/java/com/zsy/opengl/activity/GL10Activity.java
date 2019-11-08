package com.zsy.opengl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zsy.opengl.R;

/**
 * @Title gl10的demo
 * @date 2019/11/6
 * @autor Zsy
 */
public class GL10Activity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gl10);

        /*GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setRenderer(new FontRenderer());
        //设置渲染模式(须在 setRenderer() 之后)
        //1.非主动渲染RENDERMODE_WHEN_DIRTY(0) 2.RENDERMODE_CONTINUOUSLY主动渲染(1)
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(glSurfaceView);*/
    }

}
