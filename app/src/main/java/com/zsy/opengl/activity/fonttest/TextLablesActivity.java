package com.zsy.opengl.activity.fonttest;

import android.app.Activity;
import android.graphics.Paint;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.zsy.opengl.activity.fonttest.LabelMaker;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
 
public class TextLablesActivity extends Activity implements GLSurfaceView.Renderer{
 
    private GLSurfaceView mGLView;
    private LabelMaker mLabels;
    private int mWidth;
    private int mHeight;
    private int mLabelId1;  //text label id
    private int mLabelId2;  //text label id
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        mGLView = new GLSurfaceView(this);
        mGLView.setRenderer(this);
        setContentView(mGLView);
    }
 
 
    @Override
    public void onDrawFrame(GL10 gl) {
        mLabels.beginDrawing(gl,mWidth,mHeight);
        mLabels.draw(gl,600,600, mLabelId1);     //指定坐标，显示label 1 
        mLabels.draw(gl,600,700, mLabelId2);     //指定坐标，显示label 2
        mLabels.endDrawing(gl);
 
    }
 
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //调用LabelMaker 生成text label
        mLabels = new LabelMaker(true, 1080, 256); //Bitmap的宽和高， 可以根据你的text lable来调整大小
        Paint labelPaint = new Paint();  //text label的画笔
        labelPaint.setTextSize(32);
        labelPaint.setAntiAlias(true);
        labelPaint.setARGB(0xff, 0xff, 0x00, 0x00);
 
        mLabels.initialize(gl);
        mLabels.beginAdding(gl);
        mLabelId1 = mLabels.add(gl,"OpenGL 显示的文字",labelPaint);  //add lable1
        mLabelId2 = mLabels.add(gl,"Hello world !",labelPaint);  //add label2
        mLabels.endAdding(gl);
    }
 
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        mWidth = width;
        mHeight = height;
        gl.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 15);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}