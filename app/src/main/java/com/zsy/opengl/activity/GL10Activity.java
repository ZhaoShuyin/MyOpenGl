package com.zsy.opengl.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zsy.opengl.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @Title gl10的demo
 * @date 2019/11/6
 * @autor Zsy
 */
public class GL10Activity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setRenderer(new MyRender());
        //设置渲染模式(须在 setRenderer() 之后)
        //1.非主动渲染RENDERMODE_WHEN_DIRTY(0) 2.RENDERMODE_CONTINUOUSLY主动渲染(1)
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(glSurfaceView);
    }


    class MyRender implements GLSurfaceView.Renderer {
        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
            gl10.glClearColor(0.0f, 0.5f, 0.5f, 1.0f);
        }

        @Override
        public void onSurfaceChanged(GL10 gl10, int width, int height) {
            gl10.glViewport(0, 0, width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl10) {
           /*//图象大小要根据文字大小算下,以和文本长度对应
            Bitmap bmp = Bitmap.createBitmap(256,256, Bitmap.Config.ARGB_8888);
            Canvas canvasTemp = new Canvas(bmp);
            canvasTemp.drawColor(Color.WHITE);
            Paint p = new Paint();
            String familyName ="宋体";
            Typeface font = Typeface.create(familyName,Typeface.BOLD);
            p.setColor(Color.RED);
            p.setTypeface(font);
            p.setTextSize(22);
            canvasTemp.drawText("文字渲染到Bitmap!",0,100,p);*/
            // Create an empty, mutable bitmap
            Bitmap bitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_4444);
            // get a canvas to paint over the bitmap
            Canvas canvas = new Canvas(bitmap);
            bitmap.eraseColor(0);

            // get a background image from resources
            // note the image format must match the bitmap format
            Drawable background = getResources().getDrawable(R.mipmap.ic_launcher);
            background.setBounds(0, 0, 256, 256);
            background.draw(canvas); // draw the background to our bitmap

            // Draw the text
            Paint textPaint = new Paint();
            textPaint.setTextSize(32);
            textPaint.setAntiAlias(true);
            textPaint.setARGB(0xff, 0x00, 0x00, 0x00);
            // draw the text centered
            canvas.drawText("Hello World", 16,112, textPaint);

            //Generate one texture pointer...
            gl10.glGenTextures(1, textures, 0);
           //...and bind it to our array
            gl10.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

           //Create Nearest Filtered Texture
            gl10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
            gl10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

            //Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
            gl10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
            gl10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

            //Use the Android GLUtils to specify a two-dimensional texture image from our bitmap
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

           //Clean up
            bitmap.recycle();
        }
    }

    private static float ScaleFactor = 1.0F;
    private static float unit = 0.41F;
    private static float revise_Y = 0.65F;
    private static float fHeight = 0.6F;
    public static float[] vertex_1;

    private FloatBuffer vertexBuffer;
    private FloatBuffer coordBuffer;

    private int[] textures = new int[1];

    public float[] coord = new float[]{0.0F, 1.0F * 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 0.0F};

    public void DrawFont(GL10 gl, String leadString, int index) {
        vertex_1 = new float[]{-10.5F, 11.0F * unit - revise_Y, 0.0F, -9.5F, 11.0F * unit - revise_Y, 0.0F, -10.5F, 11.0F * unit - revise_Y + fHeight, 0.0F, -9.5F, 11.0F * unit - revise_Y + fHeight, 0.0F};
        Bitmap bitmap = getBitmap();
        gl.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        //来设置一个新的纹理名称
        gl.glGenTextures(1, this.textures, 0);
        //绑定纹理句柄
        gl.glBindTexture(GL10.GL_TEXTURE_2D, this.textures[0]);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, 9729.0F);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, 9729.0F);
        gl.glLoadIdentity();
        ByteBuffer bb = ByteBuffer.allocateDirect(vertex_1.length * 4);
        bb.order(ByteOrder.nativeOrder());
        this.vertexBuffer = bb.asFloatBuffer();
        this.vertexBuffer.put(vertex_1);
        this.vertexBuffer.position(0);
        ByteBuffer coordbb = ByteBuffer.allocateDirect(coord.length * 4);
        coordbb.order(ByteOrder.nativeOrder());
        this.coordBuffer = coordbb.asFloatBuffer();
        this.coordBuffer.put(coord);
        this.coordBuffer.position(0);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//'聴'
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);//'聸'
        gl.glTranslatef(0.0F, 0.0F, -5.1F);
        gl.glVertexPointer(3, 5126, 0, this.vertexBuffer);
        gl.glTexCoordPointer(2, 5126, 0, this.coordBuffer);
        gl.glDrawArrays(5, 0, 4);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisable(GL10.GL_TEXTURE_2D);
        gl.glFinish();
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }

    }


    private Bitmap getBitmap() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher); // 间接调用
        return bitmap;
    }

}
