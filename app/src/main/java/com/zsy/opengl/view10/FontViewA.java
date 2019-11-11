package com.zsy.opengl.view10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.zsy.opengl.render10.coordinate.CoordinateRender;
import com.zsy.opengl.render10.projection.ProjectionRender;

/**
 * @Title com.zsy.opengl.view10
 * @date 2019/11/8
 * @autor Zsy
 */

public class FontViewA extends GLSurfaceView {
    public FontViewA(Context context) {
        this(context,null);
    }

    public FontViewA(Context context, AttributeSet attrs) {
        super(context, attrs);
        setRenderer(new CoordinateRender());
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
