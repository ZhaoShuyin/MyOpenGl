package com.zsy.opengl.render10.coordinate;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @Title opengl 坐标系 简介
 * @date 2019/11/11
 * @autor Zsy
 */
public class CoordinateRender implements GLSurfaceView.Renderer{

    /**
      抗锯齿

     1.线的抗锯齿

     glEnable(GL_LINE_SMOOTH); //启用

     glHint(GL_LINE_SMOOTH,GL_NICEST);


     //绘制

     glDisable(GL_LINE_SMOOTH); //关闭


     2.点的抗锯齿

     glEnable(GL_POINT_SMOOTH); //启用

     glHint(GL_POINT_SMOOTH,GL_NICEST);


     //绘制

     glDisable(GL_POINT_SMOOTH); //关闭


     3.多边形的抗锯齿

     glEnable(GL_POLYGON_SMOOTH); //启用

     glHint(GL_POLYGON_SMOOTH,GL_NICEST);


     //绘制

     glDisable(GL_POLYGON_SMOOTH); //关闭


     线宽的设置

     glLineWidth(线宽);

     glPointSize(点的直径);
     */


    private float vertexs[] = {
            0.0f, 0.0f, 0.0f,//原点
            1.0f, 0.0f, 0.0f,//X向右线最右点
            0.0f, 0.0f, 0.0f,//原点
            0.0f, 1.0f, 0.0f,//Y向上线最上点
            0.0f, 0.0f, 0.0f,//原点
            0.0f, 0.0f, 1.0f//Z向外线最外点
    };

    private float colors[] = {
            1.0f, 1.0f, 1.0f, 1.0f,//起点白色
            1.0f, 0.0f, 0.0f, 1.0f,//终点红色
            1.0f, 1.0f, 1.0f, 1.0f,//起点白色
            0.0f, 1.0f, 0.0f, 1.0f,//终点绿色
            1.0f, 1.0f, 1.0f, 1.0f,//起点白色
            0.0f, 0.0f, 1.0f, 1.0f//终点蓝色
    };
    private FloatBuffer vertexsBuffer;
    private FloatBuffer colorsBuffer;


    public CoordinateRender() {
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertexs.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexsBuffer = vbb.asFloatBuffer();
        vertexsBuffer.put(vertexs);
        vertexsBuffer.position(0);

        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorsBuffer = cbb.asFloatBuffer();
        colorsBuffer.put(colors);
        colorsBuffer.position(0);
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glDisable(GL10.GL_DITHER);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0) {
            height = 1;
        }
        float aspect = (float) width / height;
        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 0, aspect, 0.1f, 100.0f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glEnable(GL10.GL_LINE_SMOOTH);             //启用线抗锯齿
        gl.glHint(GL10.GL_LINE_SMOOTH,GL10.GL_NICEST);// //启用线抗锯齿

        //恢复初始坐标系，重置当前指定的矩阵为单位矩阵
            gl.glLoadIdentity();
        //绕Y轴顺时针旋转30度,绕x轴逆时针旋转30度
        gl.glRotatef(30,1,-1,0);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glLineWidth(10);
        //设置顶点坐标
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexsBuffer);
        //设置顶点颜色
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorsBuffer);
        //绘制顶点
        gl.glDrawArrays(GL10.GL_LINES, 0, vertexs.length / 3);

        //以GL_COLOR_ARRAY为参数来启用和禁止颜色矩阵，颜色矩阵初始值为禁用，
        // 不允许glDrawArrays方法和glDrawElements方法调用
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
