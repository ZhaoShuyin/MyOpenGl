package com.zsy.opengl.render10.projection;

import android.opengl.GLSurfaceView;

import com.zsy.opengl.utils.BufferUtil;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @Title opengl投影 1.透视投影 2.正投影
 * @date 2019/11/11
 * @autor Zsy
 * https://www.jianshu.com/p/bb064551d46b
 * https://www.oschina.net/question/565065_67280?sort=time
 */
public class ProjectionRender implements GLSurfaceView.Renderer {

    /**
     * 0.0f, 0.1f, 0,
     * -0.1f, -0.1f, 0.0f,
     * 0.1f, -0.1f, 0.0f
     */

    //0.5
    float[] floatsA = new float[]{
            0.0f, 1f, 0f,
            -0.5f, 0f, 0f,
            0.5f, 0f, 0f
    };
    private FloatBuffer bufferA;

    public ProjectionRender() {
        bufferA = BufferUtil.floatToBuffer(floatsA);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.0f, 0.5f, 0.5f, 1.0f);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        /**
         * 设置投影视窗 参数 视窗大小(比例float类型),视原点到视窗(近距离),视原点到最远深度(远距离)
         * 深度在 N 和 F 之间的才会投影到视窗
         */
        gl.glMatrixMode(GL10.GL_PROJECTION);
        float ratio = (float) width / height;
        //透视投影
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 5);
        //正投影
//        gl.glOrthof(-ratio, ratio, -1, 1, 1, 5);
        /**
         * 切换矩阵模式并开启深度测试()
         */
        gl.glMatrixMode(GL10.GL_MODELVIEW);    //否则不显示
        gl.glEnable(GL10.GL_DEPTH_TEST);       //否则z数值无效

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //重置屏幕
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        //坐标重置
        gl.glLoadIdentity();
        //移动坐标
        gl.glTranslatef(0.0F, 0.0F, -1.0F);
        //绘制深度为1的图形
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bufferA);
        gl.glColor4f(1.0f, 0, 0, 1.0f);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);//GL_TRIANGLE_STRIP


        //坐标重置
        gl.glLoadIdentity();
        //移动坐标
        gl.glTranslatef(-1.5F, 0.0F, -3.0F);
        //绘制深度为1的图形
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bufferA);
        gl.glColor4f(0, 1.0f, 0, 1.0f);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);//GL_TRIANGLE_STRIP


        //坐标重置
        gl.glLoadIdentity();
        //移动坐标
        gl.glTranslatef(2.5F, -2.0F, -3.0F);
        //绘制深度为1的图形
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bufferA);
        gl.glColor4f(0, 0f, 1.0f, 1.0f);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);//GL_TRIANGLE_STRIP
    }

}
