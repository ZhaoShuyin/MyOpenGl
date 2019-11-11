package com.zsy.opengl.render10.font;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @Title com.zsy.opengl.render10
 * @date 2019/11/8
 * @autor Zsy
 * Gl10     https://www.cnblogs.com/Anita9002/p/4497813.html
 * Gl10 API https://www.cnblogs.com/Anita9002/p/4476341.html
 */
public class FontRender implements GLSurfaceView.Renderer {
    private String TAG = "ZSurView";

    private int[] textures;
    private FloatBuffer vertexBuffer;
    private FloatBuffer coordBuffer;

    private static float unit = 0.41F;
    private static float revise_Y = 0.4F;
    public static float[] vertex; //顶点
    public static float[] coord;    //纹理

    public FontRender() {
        this.textures = new int[1];
        float v = 11.0F * unit - revise_Y;
        Log.e("ZSurView", "构造函数: " + v);
        /*vertex = new float[]{
                -10.5F, 4.1F, 0.0F,
                -9.5F, 4.1F, 0.0F,
                -10.5F, 4.7F, 0.0F,
                -9.5F, 4.7F, 0.0F};*/
        //纹理矩形顶点坐标数组
        vertex = new float[]{
                -10F, 4F, 0.0F,
                -9F, 4F, 0.0F,
                -10F, 5F, 0.0F,
                -9F, 5F, 0.0F};
        //纹理坐标系
        coord = new float[]{
                0.0F, 1.0F,
                1.0F, 1.0F,
                0.0F, 0.0F,
                1.0F, 0.0F};
    }


    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        Log.e(TAG, "onSurfaceCreated: ");
        //gl10.glClear(GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_COLOR_BUFFER_BIT);//16640
        //1.target (表明颜色和纹理坐标插值的效果) 2.mode(选择最有效率的选项)
        gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);//3152 ,4353
        //启动阴影平滑
        gl10.glShadeModel(GL10.GL_SMOOTH);//7425
        //设置基本颜色
        gl10.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        //设置深度
        gl10.glClearDepthf(1.0F);
        //设置线宽
        gl10.glLineWidth(1.75F);
        //启动深度测试
        gl10.glEnable(GL10.GL_DEPTH_TEST);//2929
        //深度测试类型
        gl10.glDepthFunc(GL10.GL_LEQUAL);//515
        //启动线平滑
        gl10.glEnable(GL10.GL_LINE_SMOOTH);//2848
        //*表明直线抗锯齿的效果
        gl10.glHint(GL10.GL_LINE_SMOOTH_HINT, GL10.GL_NICEST);//3154,4354

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        Log.e(TAG, "onSurfaceChanged: ");
        //屏幕清除
        gl10.glClear(GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_COLOR_BUFFER_BIT);//16640

        //设置窗口尺寸
        gl10.glViewport(0, 0, width, height);
        //
        /**
         * (设置当前矩阵模式)用来做投影矩阵变换
         *GL_MODELVIEW  : 应用视图矩阵堆的后续矩阵操作。
         *GL_PROJECTION : 应用投射矩阵堆的后续矩阵操作。
         *GL_TEXTURE    :应用纹理矩阵堆的后续矩阵操作。
         *GL_MATRIX_PALETTE_OES（OES_matrix_palette 扩展）
         *              : ——启用矩阵调色板堆栈扩展，并应用矩阵调色板堆栈后续矩阵操作。
         */
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        //坐标重置
        gl10.glLoadIdentity();
        /**
         * 设置视窗大小
         * zNear:近平面距离摄像机的距离 zFar:远平面距离摄像机的距离。
         */
        float ratio = 2.05F;
        gl10.glFrustumf(-ratio, ratio, -1.0F, 1.0F, 1.0F, 10.0F);
//        gl10.glFrustumf(-2.0F, 2.0F, -1.0F, 1.0F, 1.0F, 10.0F);
        //设置矩阵模式
        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        //坐标重置
        gl10.glLoadIdentity();
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        Log.e(TAG, "onDrawFrame: ");
        //不清除会重复渲染
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        DrawFont(gl10, vertex, "V01");
    }


    public void DrawFont(GL10 gl, float[] vertex, String leadString) {
        //创建纹理bitmap
        Bitmap bitmap = initFontBitmap(leadString);
        //设置画笔颜色
        gl.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //启用纹理，当前活动纹理单元为二维纹理
        gl.glEnable(GL10.GL_TEXTURE_2D);
        //创建纹理
        gl.glGenTextures(1, this.textures, 0);
        //绑定纹理
        gl.glBindTexture(GL10.GL_TEXTURE_2D, this.textures[0]);
        //使用GLUtils
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        //GL_TEXTURE_MAG_FILTER(10240) GL_TEXTURE_MIN_FILTER(10241) GL_LINEAR(9729)
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        //重置坐标
        gl.glLoadIdentity();
        //计算顶点浮点
        ByteBuffer bb = ByteBuffer.allocateDirect(vertex.length * 4);
        bb.order(ByteOrder.nativeOrder());
        this.vertexBuffer = bb.asFloatBuffer();
        this.vertexBuffer.put(vertex);
        this.vertexBuffer.position(0);
        //计算颜色浮点
        ByteBuffer coordbb = ByteBuffer.allocateDirect(coord.length * 4);
        coordbb.order(ByteOrder.nativeOrder());
        this.coordBuffer = coordbb.asFloatBuffer();
        this.coordBuffer.put(coord);
        this.coordBuffer.position(0);
        //开启顶点缓冲
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//'聴'
        //开启纹理缓冲
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);//'聸'
        //移动坐标系
        gl.glTranslatef(0.0F, 0.0F, -5.1F);
        //设置顶点数据
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, this.vertexBuffer);
        //设置纹理数据
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, this.coordBuffer);
        //*****开始渲染*****
        gl.glDrawArrays(5, 0, 4);
        //关闭顶点缓冲
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);//'聴'//GL_TEXTURE_COORD_ARRAY
        //关闭纹理缓冲
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        //关闭纹理
        gl.glDisable(GL10.GL_TEXTURE_2D);
        gl.glFinish();
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }

    }

    public static Bitmap initFontBitmap(String font) {
        Bitmap bitmap = Bitmap.createBitmap(64, 64, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(0);
        Paint p = new Paint();
        Typeface typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD);
        p.setAntiAlias(true);
        p.setColor(Color.argb(255, 255, 255, 0));
        p.setTypeface(typeface);
        p.setTextSize(30.0F);
        canvas.drawText(font, 2.0F, 35.0F, p);
        return bitmap;
    }

}
