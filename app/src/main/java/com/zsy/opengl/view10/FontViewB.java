package com.zsy.opengl.view10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.zsy.opengl.render10.font.FontRender;
import com.zsy.opengl.view.MyRenderer;
import com.zsy.opengl.view.MyRendererTest;

/**
 * @Title com.zsy.opengl.view10
 * @date 2019/11/8
 * @autor Zsy
 */

public class FontViewB extends GLSurfaceView {
    public FontViewB(Context context) {
        this(context,null);
    }

    public FontViewB(Context context, AttributeSet attrs) {
        super(context, attrs);
        setRenderer(new MyRendererTest());
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
