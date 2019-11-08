package com.zsy.opengl.render10.font;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.util.Log;

import com.zsy.opengl.MyApplication;
import com.zsy.opengl.R;

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

    private static float ScaleFactor = 1.0F;
    private static float unit = 0.41F;
    private static float revise_Y = 0.65F;
    private static float fHeight = 0.6F;
    public static float[] vertex_1;
    public static float[] coord;

    private int glWidth;
    private int glHeight;
    private boolean IsQualcomm;
    private boolean IsTilingQCOM;
    private Boolean IsDrawFont = Boolean.valueOf(true);
    private Boolean IsPowerVR = Boolean.valueOf(false);

    private float amend;
    private int mCounter;

    private int[] textures;
    private FloatBuffer vertexBuffer;
    private FloatBuffer coordBuffer;


    public FontRender() {
        this.textures = new int[1];
        vertex_1 = new float[]{-10.5F, 11.0F * unit - revise_Y, 0.0F, -9.5F, 11.0F * unit - revise_Y, 0.0F, -10.5F, 11.0F * unit - revise_Y + fHeight, 0.0F, -9.5F, 11.0F * unit - revise_Y + fHeight, 0.0F};
        coord = new float[]{0.0F, ScaleFactor * 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 0.0F};
    }


    public void onSet(GL10 gl, int width, int height) {
        gl.glClear(GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_COLOR_BUFFER_BIT);//16640
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);//3152 ,4353
        gl.glShadeModel(GL10.GL_SMOOTH);//7425
        gl.glClearColor(0.0F, 0.5F, 0.5F, 1.0F);
        gl.glClearDepthf(1.0F);
        gl.glLineWidth(1.75F);
        //启用深度测试
        gl.glEnable(GL10.GL_DEPTH_TEST);//2929
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glEnable(GL10.GL_LINE_SMOOTH);//2848
        gl.glHint(GL10.GL_LINE_SMOOTH_HINT, GL10.GL_NICEST);//3154,4354
        String vendor = gl.glGetString(7936);
        if (vendor.equals("Qualcomm")) {
            this.IsQualcomm = true;
            String renderer = gl.glGetString(7937);
            if (!renderer.equals("Adreno (TM) 305") && !renderer.equals("Adreno (TM) 306") && !renderer.equals("Adreno (TM) 320") && !renderer.equals("Adreno (TM) 330") && !renderer.equals("Adreno (TM) 302") && !renderer.equals("Adreno (TM) 304") && !renderer.equals("Adreno (TM) 308")) {
                if (renderer.equals("Adreno (TM) 405") || renderer.equals("Adreno (TM) 406") || renderer.equals("Adreno (TM) 418") || renderer.equals("Adreno (TM) 420") || renderer.equals("Adreno (TM) 430") || renderer.equals("Adreno (TM) 505") || renderer.equals("Adreno (TM) 506") || renderer.equals("Adreno (TM) 510") || renderer.equals("Adreno (TM) 530") || renderer.equals("Adreno (TM) 540")) {
                    this.IsTilingQCOM = false;
                }
            } else {
                this.IsTilingQCOM = true;
            }
        } else {
            this.IsQualcomm = false;
        }

        if (!vendor.equals("ARM") && !vendor.equals("NVIDIA Corporation") && !vendor.equals("Qualcomm") && !vendor.equals("Intel") && !vendor.contains("Nexell")) {
            if (vendor.equals("Imagination Technologies")) {
//                GLJNILIB.setPreserveAttrib();
                this.IsPowerVR = Boolean.valueOf(true);
            }
        } else {
//            GLJNILIB.setPreserveAttrib();
        }

        this.glWidth = width;
        this.glHeight = height;
        float ratio = (float) height / (float) width;
        if (ratio < 1.0F) {
            this.amend = 1.708F;
            this.amend = this.amend * 1340.0F * (float) width / (2048.0F * (float) height);
        } else {
            this.amend = 1.0F;
            this.amend = this.amend * 1852.0F * (float) width / (1536.0F * (float) height);
        }

        ratio = 2.05F;
        this.IsDrawFont = Boolean.valueOf(true);
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);//5889
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio, ratio, -1.0F, 1.0F, 1.0F, 10.0F);
        gl.glMatrixMode(GL10.GL_MODELVIEW);//5888
        gl.glLoadIdentity();
        this.mCounter = 0;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        //gl10.glClear(GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_COLOR_BUFFER_BIT);//16640
        //
        gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);//3152 ,4353
        //启动阴影平滑
        gl10.glShadeModel(GL10.GL_SMOOTH);//7425
        gl10.glClearColor(0.0f, 0.5f, 0.5f, 0.0f);
        gl10.glClearDepthf(1.0F);
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
//        gl10.glViewport(0, 0, width, height);
        onSet(gl10, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        DrawFont(gl10, vertex_1, "Hello World", 0);
        //fontTest(gl10);
    }



    public void DrawFont(GL10 gl, float[] vertex, String leadString, int index) {
        Bitmap bitmap = initFontBitmap(leadString, index);
        gl.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        //启用纹理，当前活动纹理单元为二维纹理
        gl.glEnable(GL10.GL_TEXTURE_2D);
        //创建纹理
        gl.glGenTextures(1, this.textures, 0);
        //绑定纹理
        gl.glBindTexture(GL10.GL_TEXTURE_2D, this.textures[0]);

        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

        //GL_TEXTURE_MAG_FILTER(10240) GL_TEXTURE_MIN_FILTER(10241) GL_LINEAR(9729)
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);

        gl.glLoadIdentity();
        ByteBuffer bb = ByteBuffer.allocateDirect(vertex.length * 4);
        bb.order(ByteOrder.nativeOrder());
        this.vertexBuffer = bb.asFloatBuffer();
        this.vertexBuffer.put(vertex);
        this.vertexBuffer.position(0);
        ByteBuffer coordbb = ByteBuffer.allocateDirect(coord.length * 4);
        coordbb.order(ByteOrder.nativeOrder());
        this.coordBuffer = coordbb.asFloatBuffer();
        this.coordBuffer.put(coord);
        this.coordBuffer.position(0);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//'聴'
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);//'聸'
        gl.glTranslatef(0.0F, 0.0F, -5.1F);
        //GL10.GL_FLOAT(5126)
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, this.vertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, this.coordBuffer);
        gl.glDrawArrays(5, 0, 4);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);//'聴'//GL_TEXTURE_COORD_ARRAY
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisable(GL10.GL_TEXTURE_2D);
        gl.glFinish();
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }

    }

    public static Bitmap initFontBitmap(String font, int index) {
        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(0);
        Paint p = new Paint();
        Typeface typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD);
        p.setAntiAlias(true);
        p.setColor(0xffff0000);
        p.setTypeface(typeface);
        p.setTextSize(30.0F);
        canvas.drawText(font, 2.0F, 35.0F, p);
        return bitmap;
    }



    public void fontTest(GL10 gl) {
        Bitmap bitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        bitmap.eraseColor(0);

        // get a background image from resources
        // note the image format must match the bitmap format
        Drawable background = MyApplication.instance.getResources().getDrawable(R.mipmap.ic_launcher);
        background.setBounds(0, 0, 256, 256);
        background.draw(canvas); // draw the background to our bitmap

        Paint textPaint = new Paint();
        textPaint.setTextSize(32);
        textPaint.setAntiAlias(true);
        textPaint.setARGB(0xff, 0xff, 0x00, 0x00);
        canvas.drawText("Hello World", 16, 112, textPaint);

        //创建一个纹理指针
        gl.glGenTextures(1, textures, 0);
        //绑定纹理到数组
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

        //创建最近的过滤纹理
        gl.glTexParameterf(
                GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_MAG_FILTER,
                GL10.GL_LINEAR);
        gl.glTexParameterf(
                GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_MIN_FILTER,
                GL10.GL_NEAREST);


        //不同的纹理参数, e.g. GL10.GL_CLAMP_TO_EDGE
       /* gl.glTexParameterf(
                GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_WRAP_S,
                GL10.GL_REPEAT);
        gl.glTexParameterf(
                GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_WRAP_T,
                GL10.GL_REPEAT);*/


        //使用 GLUtils 从bitmap设置一个2维纹理图片
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

        bitmap.recycle();
    }
}
