//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zsy.opengl.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLUtils;
import android.util.Log;

import com.example.gltest.GLJNILIB;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import serial.jni.DrawUtils;

public class MyRendererTest implements Renderer {

    private static float ScaleFactor = 1.0F;
    private static float unit = 0.41F;
    //    private static float revise_Y = 0.65F;
    private static float fHeight = 0.6F;

    public static float[] coord;

    public static float[] vertex_1;
    public static float[] vertex_2;
    public static float[] vertex_3;
    public static float[] vertex_aVR;
    public static float[] vertex_aVL;
    public static float[] vertex_aVF;
    public static float[] vertex_V1;
    public static float[] vertex_V2;
    public static float[] vertex_V3;
    public static float[] vertex_V4;
    public static float[] vertex_V5;
    public static float[] vertex_V6;


    private GLJNILIB gljni;
    private int glWidth;
    private int glHeight;
    private boolean IsQualcomm;
    private boolean IsTilingQCOM;
    private Boolean IsDrawFont = Boolean.valueOf(true);
    private Boolean IsPowerVR = Boolean.valueOf(false);
    private int displayMode = 0;
    private int checkedCount = 0;
    private int displayMode2x6 = 0;
    private boolean fontChanged = false;
    private float DX;
    private float CX;
    private float DX62;
    private float CX62;
    private final float unit12 = 0.4F;
    private float xunit = 0.014F;
    private float mSpeed = 0.014F;
    private float scale = 5.55E-4F;
    private float mScale = 5.55E-4F;
    private float scaleVx = 5.55E-4F;
    private float mScaleVx = 5.55E-4F;
    private final float revise_Y = 0.4F;
    private final float revise_YY = 0.35F;
    private short[] temp = new short[72];
    private short[] temp_Last = new short[12];
    private float[] rectScreen = new float[]{-10.5F, 6.0F, -9.0F, 6.0F, -9.0F, -6.0F, -10.5F, 6.0F, -9.0F, -6.0F, -10.5F, -6.0F};
    private float[] rect;
    private float DX621;
    private float CX621;
    private int RhythmIndex;
    private float[] lead;
    private int[] textures;
    private FloatBuffer vertexBuffer;
    private FloatBuffer coordBuffer;
    private String[] leadString;
    private int mCounter;
    private final int mRendererDelay;
    public static final int MESSAGE_GATHER_START = 28672;
    private float colorRED;
    private float colorGREEN;
    private float colorBLUE;
    private float colorALPHA;
    private float amend;
    private int[] leadTextures;
    private float[][] leadVertex;
    private FloatBuffer[] mVertexBuffer;
    private FloatBuffer[] mCoordBuffer;
    private ConcurrentLinkedQueue<Short> mEcgQueue;
    private boolean isEcgQueue;   //是否开始刷新显示数据
    private boolean IsClearScreen;//是否清空屏幕

    public MyRendererTest() {
        this.rect = new float[]{-6.0F * this.xunit, 6.0F, 6.0F * this.xunit, 6.0F, 6.0F * this.xunit, -6.0F, -6.0F * this.xunit, 6.0F, 6.0F * this.xunit, -6.0F, -6.0F * this.xunit, -6.0F};
        this.RhythmIndex = 0;
        this.lead = new float[]{-3.0F * this.xunit, 0.0F, -2.0F * this.xunit, 0.0F, -this.xunit, 0.0F, 0.0F, 0.0F, this.xunit, 0.0F, 2.0F * this.xunit, 0.0F, 3.0F * this.xunit, 0.0F, -3.0F * this.xunit, 0.0F, -2.0F * this.xunit, 0.0F, -this.xunit, 0.0F, 0.0F, 0.0F, this.xunit, 0.0F, 2.0F * this.xunit, 0.0F, 3.0F * this.xunit, 0.0F, -3.0F * this.xunit, 0.0F, -2.0F * this.xunit, 0.0F, -this.xunit, 0.0F, 0.0F, 0.0F, this.xunit, 0.0F, 2.0F * this.xunit, 0.0F, 3.0F * this.xunit, 0.0F, -3.0F * this.xunit, 0.0F, -2.0F * this.xunit, 0.0F, -this.xunit, 0.0F, 0.0F, 0.0F, this.xunit, 0.0F, 2.0F * this.xunit, 0.0F, 3.0F * this.xunit, 0.0F, -3.0F * this.xunit, 0.0F, -2.0F * this.xunit, 0.0F, -this.xunit, 0.0F, 0.0F, 0.0F, this.xunit, 0.0F, 2.0F * this.xunit, 0.0F, 3.0F * this.xunit, 0.0F, -3.0F * this.xunit, 0.0F, -2.0F * this.xunit, 0.0F, -this.xunit, 0.0F, 0.0F, 0.0F, this.xunit, 0.0F, 2.0F * this.xunit, 0.0F, 3.0F * this.xunit, 0.0F, -3.0F * this.xunit, 0.0F, -2.0F * this.xunit, 0.0F, -this.xunit, 0.0F, 0.0F, 0.0F, this.xunit, 0.0F, 2.0F * this.xunit, 0.0F, 3.0F * this.xunit, 0.0F, -3.0F * this.xunit, 0.0F, -2.0F * this.xunit, 0.0F, -this.xunit, 0.0F, 0.0F, 0.0F, this.xunit, 0.0F, 2.0F * this.xunit, 0.0F, 3.0F * this.xunit, 0.0F, -3.0F * this.xunit, 0.0F, -2.0F * this.xunit, 0.0F, -this.xunit, 0.0F, 0.0F, 0.0F, this.xunit, 0.0F, 2.0F * this.xunit, 0.0F, 3.0F * this.xunit, 0.0F, -3.0F * this.xunit, 0.0F, -2.0F * this.xunit, 0.0F, -this.xunit, 0.0F, 0.0F, 0.0F, this.xunit, 0.0F, 2.0F * this.xunit, 0.0F, 3.0F * this.xunit, 0.0F, -3.0F * this.xunit, 0.0F, -2.0F * this.xunit, 0.0F, -this.xunit, 0.0F, 0.0F, 0.0F, this.xunit, 0.0F, 2.0F * this.xunit, 0.0F, 3.0F * this.xunit, 0.0F, -3.0F * this.xunit, 0.0F, -2.0F * this.xunit, 0.0F, -this.xunit, 0.0F, 0.0F, 0.0F, this.xunit, 0.0F, 2.0F * this.xunit, 0.0F, 3.0F * this.xunit, 0.0F};
        this.textures = new int[1];
        this.leadString = new String[]{"Ⅰ", "Ⅱ", "Ⅲ", "aVR", "aVL", "aVF", "V1", "V2", "V3", "V4", "V5", "V6"};
        this.mRendererDelay = 5;
        this.colorRED = 1.0F;
        this.colorGREEN = 1.0F;
        this.colorBLUE = 0.2F;
        this.colorALPHA = 1.0F;
        this.leadTextures = new int[12];
        this.leadVertex = new float[][]{vertex_1, vertex_2, vertex_3, vertex_aVR, vertex_aVL, vertex_aVF, vertex_V1, vertex_V2, vertex_V3, vertex_V4, vertex_V5, vertex_V6};
        this.IsClearScreen = false;

        coord = new float[]{0.0F, ScaleFactor * 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 0.0F};

        vertex_1 = new float[]{-10.5F, 11.0F * unit - revise_Y, 0.0F, -9.5F, 11.0F * unit - revise_Y, 0.0F, -10.5F, 11.0F * unit - revise_Y + fHeight, 0.0F, -9.5F, 11.0F * unit - revise_Y + fHeight, 0.0F};
        vertex_2 = new float[]{-10.5F, 9.0F * unit - revise_Y, 0.0F, -9.5F, 9.0F * unit - revise_Y, 0.0F, -10.5F, 9.0F * unit - revise_Y + fHeight, 0.0F, -9.5F, 9.0F * unit - revise_Y + fHeight, 0.0F};
        vertex_3 = new float[]{-10.5F, 7.0F * unit - revise_Y, 0.0F, -9.5F, 7.0F * unit - revise_Y, 0.0F, -10.5F, 7.0F * unit - revise_Y + fHeight, 0.0F, -9.5F, 7.0F * unit - revise_Y + fHeight, 0.0F};
        vertex_aVR = new float[]{-10.5F, 5.0F * unit - revise_Y, 0.0F, -9.5F, 5.0F * unit - revise_Y, 0.0F, -10.5F, 5.0F * unit - revise_Y + fHeight, 0.0F, -9.5F, 5.0F * unit - revise_Y + fHeight, 0.0F};
        vertex_aVL = new float[]{-10.5F, 3.0F * unit - revise_Y, 0.0F, -9.5F, 3.0F * unit - revise_Y, 0.0F, -10.5F, 3.0F * unit - revise_Y + fHeight, 0.0F, -9.5F, 3.0F * unit - revise_Y + fHeight, 0.0F};
        vertex_aVF = new float[]{-10.5F, unit - revise_Y, 0.0F, -9.5F, unit - revise_Y, 0.0F, -10.5F, unit - revise_Y + fHeight, 0.0F, -9.5F, unit - revise_Y + fHeight, 0.0F};
        vertex_V1 = new float[]{-10.5F, -unit - revise_Y, 0.0F, -9.5F, -unit - revise_Y, 0.0F, -10.5F, -unit - revise_Y + fHeight, 0.0F, -9.5F, -unit - revise_Y + fHeight, 0.0F};
        vertex_V2 = new float[]{-10.5F, -3.0F * unit - revise_Y, 0.0F, -9.5F, -3.0F * unit - revise_Y, 0.0F, -10.5F, -3.0F * unit - revise_Y + fHeight, 0.0F, -9.5F, -3.0F * unit - revise_Y + fHeight, 0.0F};
        vertex_V3 = new float[]{-10.5F, -5.0F * unit - revise_Y, 0.0F, -9.5F, -5.0F * unit - revise_Y, 0.0F, -10.5F, -5.0F * unit - revise_Y + fHeight, 0.0F, -9.5F, -5.0F * unit - revise_Y + fHeight, 0.0F};
        vertex_V4 = new float[]{-10.5F, -7.0F * unit - revise_Y, 0.0F, -9.5F, -7.0F * unit - revise_Y, 0.0F, -10.5F, -7.0F * unit - revise_Y + fHeight, 0.0F, -9.5F, -7.0F * unit - revise_Y + fHeight, 0.0F};
        vertex_V5 = new float[]{-10.5F, -9.0F * unit - revise_Y, 0.0F, -9.5F, -9.0F * unit - revise_Y, 0.0F, -10.5F, -9.0F * unit - revise_Y + fHeight, 0.0F, -9.5F, -9.0F * unit - revise_Y + fHeight, 0.0F};
        vertex_V6 = new float[]{-10.5F, -11.0F * unit - revise_Y, 0.0F, -9.5F, -11.0F * unit - revise_Y, 0.0F, -10.5F, -11.0F * unit - revise_Y + fHeight, 0.0F, -9.5F, -11.0F * unit - revise_Y + fHeight, 0.0F};

    }

    public void DrawFont(GL10 gl, float[] vertex, String leadString, int index) {
        Bitmap bitmap = initFontBitmap(leadString, index);
        gl.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glGenTextures(1, this.textures, 0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, this.textures[0]);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
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
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glTranslatef(0.0F, 0.0F, -5.1F);
        gl.glVertexPointer(3, 5126, 0, this.vertexBuffer);
        gl.glTexCoordPointer(2, 5126, 0, this.coordBuffer);
        gl.glDrawArrays(5, 0, 4);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL10.GL_TEXTURE_2D);
        gl.glFinish();
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }

    }

    /**
     * 帧刷新
     */
    @Override
    public void onDrawFrame(GL10 gl) {
        if (GLView.isGather) {
            switch (this.displayMode) {
                case 0:
                    if (this.IsDrawFont.booleanValue() || this.IsClearScreen) {
                        gl.glClear(GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_COLOR_BUFFER_BIT);
                        this.DX = -9.0F;
                        this.CX = -8.6F;
                        this.DrawFont(gl, vertex_1, this.leadString[0], 0);
                        this.DrawFont(gl, vertex_2, this.leadString[1], 1);
                        this.DrawFont(gl, vertex_3, this.leadString[2], 2);
                        this.DrawFont(gl, vertex_aVR, this.leadString[3], 3);
                        this.DrawFont(gl, vertex_aVL, this.leadString[4], 4);
                        this.DrawFont(gl, vertex_aVF, this.leadString[5], 5);
                        this.DrawFont(gl, vertex_V1, this.leadString[6], 6);
                        this.DrawFont(gl, vertex_V2, this.leadString[7], 7);
                        this.DrawFont(gl, vertex_V3, this.leadString[8], 8);
                        this.DrawFont(gl, vertex_V4, this.leadString[9], 9);
                        this.DrawFont(gl, vertex_V5, this.leadString[10], 10);
                        this.DrawFont(gl, vertex_V6, this.leadString[11], 11);
                        this.IsDrawFont = Boolean.valueOf(false);
                        this.IsClearScreen = false;
                    }

                    this.UpdateEcgData();
                    if (this.mCounter > 5) {
                        this.ClearRect(gl);//清空待绘制区域
                        this.DrawWave(gl); //绘制波形
                    } else if (this.mCounter == 5) {
                        Log.e("MyRenderer", "" + this.mCounter);
                        if (GLView.msg != null) {
                            GLView.msg.obtainMessage(28672).sendToTarget();
                        }
                    }
                    break;
            }

            if (this.IsTilingQCOM) {
                GLJNILIB.setEndQcom();
            }
        }
    }

    /**
     * 初步计算 temp , lead 数据
     */
    public void UpdateEcgData() {
        if (GLView.isGather) {
            //取出12个数据到 temp
            this.getEcgData();
            ++this.mCounter;
        }
        this.lead[1] = (float) this.temp_Last[0] * this.scale * this.amend + 4.4F - 0.35F;
        this.lead[3] = (float) this.temp[0] * this.scale * this.amend + 4.4F - 0.35F;
        this.lead[5] = (float) this.temp[12] * this.scale * this.amend + 4.4F - 0.35F;
        this.lead[7] = (float) this.temp[24] * this.scale * this.amend + 4.4F - 0.35F;
        this.lead[9] = (float) this.temp[36] * this.scale * this.amend + 4.4F - 0.35F;
        this.lead[11] = (float) this.temp[48] * this.scale * this.amend + 4.4F - 0.35F;
        this.lead[13] = (float) this.temp[60] * this.scale * this.amend + 4.4F - 0.35F;
        this.lead[15] = (float) this.temp_Last[1] * this.scale * this.amend + 3.6000001F - 0.35F;
        this.lead[17] = (float) this.temp[1] * this.scale * this.amend + 3.6000001F - 0.35F;
        this.lead[19] = (float) this.temp[13] * this.scale * this.amend + 3.6000001F - 0.35F;
        this.lead[21] = (float) this.temp[25] * this.scale * this.amend + 3.6000001F - 0.35F;
        this.lead[23] = (float) this.temp[37] * this.scale * this.amend + 3.6000001F - 0.35F;
        this.lead[25] = (float) this.temp[49] * this.scale * this.amend + 3.6000001F - 0.35F;
        this.lead[27] = (float) this.temp[61] * this.scale * this.amend + 3.6000001F - 0.35F;
        this.lead[29] = (float) this.temp_Last[2] * this.scale * this.amend + 2.8F - 0.35F;
        this.lead[31] = (float) this.temp[2] * this.scale * this.amend + 2.8F - 0.35F;
        this.lead[33] = (float) this.temp[14] * this.scale * this.amend + 2.8F - 0.35F;
        this.lead[35] = (float) this.temp[26] * this.scale * this.amend + 2.8F - 0.35F;
        this.lead[37] = (float) this.temp[38] * this.scale * this.amend + 2.8F - 0.35F;
        this.lead[39] = (float) this.temp[50] * this.scale * this.amend + 2.8F - 0.35F;
        this.lead[41] = (float) this.temp[62] * this.scale * this.amend + 2.8F - 0.35F;
        this.lead[43] = (float) this.temp_Last[3] * this.scale * this.amend + 2.0F - 0.35F;
        this.lead[45] = (float) this.temp[3] * this.scale * this.amend + 2.0F - 0.35F;
        this.lead[47] = (float) this.temp[15] * this.scale * this.amend + 2.0F - 0.35F;
        this.lead[49] = (float) this.temp[27] * this.scale * this.amend + 2.0F - 0.35F;
        this.lead[51] = (float) this.temp[39] * this.scale * this.amend + 2.0F - 0.35F;
        this.lead[53] = (float) this.temp[51] * this.scale * this.amend + 2.0F - 0.35F;
        this.lead[55] = (float) this.temp[63] * this.scale * this.amend + 2.0F - 0.35F;
        this.lead[57] = (float) this.temp_Last[4] * this.scale * this.amend + 1.2F - 0.35F;
        this.lead[59] = (float) this.temp[4] * this.scale * this.amend + 1.2F - 0.35F;
        this.lead[61] = (float) this.temp[16] * this.scale * this.amend + 1.2F - 0.35F;
        this.lead[63] = (float) this.temp[28] * this.scale * this.amend + 1.2F - 0.35F;
        this.lead[65] = (float) this.temp[40] * this.scale * this.amend + 1.2F - 0.35F;
        this.lead[67] = (float) this.temp[52] * this.scale * this.amend + 1.2F - 0.35F;
        this.lead[69] = (float) this.temp[64] * this.scale * this.amend + 1.2F - 0.35F;
        this.lead[71] = (float) this.temp_Last[5] * this.scale * this.amend + 0.4F - 0.35F;
        this.lead[73] = (float) this.temp[5] * this.scale * this.amend + 0.4F - 0.35F;
        this.lead[75] = (float) this.temp[17] * this.scale * this.amend + 0.4F - 0.35F;
        this.lead[77] = (float) this.temp[29] * this.scale * this.amend + 0.4F - 0.35F;
        this.lead[79] = (float) this.temp[41] * this.scale * this.amend + 0.4F - 0.35F;
        this.lead[81] = (float) this.temp[53] * this.scale * this.amend + 0.4F - 0.35F;
        this.lead[83] = (float) this.temp[65] * this.scale * this.amend + 0.4F - 0.35F;
        this.lead[85] = (float) this.temp_Last[6] * this.scaleVx * this.amend - 0.4F - 0.35F;
        this.lead[87] = (float) this.temp[6] * this.scaleVx * this.amend - 0.4F - 0.35F;
        this.lead[89] = (float) this.temp[18] * this.scaleVx * this.amend - 0.4F - 0.35F;
        this.lead[91] = (float) this.temp[30] * this.scaleVx * this.amend - 0.4F - 0.35F;
        this.lead[93] = (float) this.temp[42] * this.scaleVx * this.amend - 0.4F - 0.35F;
        this.lead[95] = (float) this.temp[54] * this.scaleVx * this.amend - 0.4F - 0.35F;
        this.lead[97] = (float) this.temp[66] * this.scaleVx * this.amend - 0.4F - 0.35F;
        this.lead[99] = (float) this.temp_Last[7] * this.scaleVx * this.amend - 1.2F - 0.35F;
        this.lead[101] = (float) this.temp[7] * this.scaleVx * this.amend - 1.2F - 0.35F;
        this.lead[103] = (float) this.temp[19] * this.scaleVx * this.amend - 1.2F - 0.35F;
        this.lead[105] = (float) this.temp[31] * this.scaleVx * this.amend - 1.2F - 0.35F;
        this.lead[107] = (float) this.temp[43] * this.scaleVx * this.amend - 1.2F - 0.35F;
        this.lead[109] = (float) this.temp[55] * this.scaleVx * this.amend - 1.2F - 0.35F;
        this.lead[111] = (float) this.temp[67] * this.scaleVx * this.amend - 1.2F - 0.35F;
        this.lead[113] = (float) this.temp_Last[8] * this.scaleVx * this.amend - 2.0F - 0.35F;
        this.lead[115] = (float) this.temp[8] * this.scaleVx * this.amend - 2.0F - 0.35F;
        this.lead[117] = (float) this.temp[20] * this.scaleVx * this.amend - 2.0F - 0.35F;
        this.lead[119] = (float) this.temp[32] * this.scaleVx * this.amend - 2.0F - 0.35F;
        this.lead[121] = (float) this.temp[44] * this.scaleVx * this.amend - 2.0F - 0.35F;
        this.lead[123] = (float) this.temp[56] * this.scaleVx * this.amend - 2.0F - 0.35F;
        this.lead[125] = (float) this.temp[68] * this.scaleVx * this.amend - 2.0F - 0.35F;
        this.lead[127] = (float) this.temp_Last[9] * this.scaleVx * this.amend - 2.8F - 0.35F;
        this.lead[129] = (float) this.temp[9] * this.scaleVx * this.amend - 2.8F - 0.35F;
        this.lead[131] = (float) this.temp[21] * this.scaleVx * this.amend - 2.8F - 0.35F;
        this.lead[133] = (float) this.temp[33] * this.scaleVx * this.amend - 2.8F - 0.35F;
        this.lead[135] = (float) this.temp[45] * this.scaleVx * this.amend - 2.8F - 0.35F;
        this.lead[137] = (float) this.temp[57] * this.scaleVx * this.amend - 2.8F - 0.35F;
        this.lead[139] = (float) this.temp[69] * this.scaleVx * this.amend - 2.8F - 0.35F;
        this.lead[141] = (float) this.temp_Last[10] * this.scaleVx * this.amend - 3.6000001F - 0.35F;
        this.lead[143] = (float) this.temp[10] * this.scaleVx * this.amend - 3.6000001F - 0.35F;
        this.lead[145] = (float) this.temp[22] * this.scaleVx * this.amend - 3.6000001F - 0.35F;
        this.lead[147] = (float) this.temp[34] * this.scaleVx * this.amend - 3.6000001F - 0.35F;
        this.lead[149] = (float) this.temp[46] * this.scaleVx * this.amend - 3.6000001F - 0.35F;
        this.lead[151] = (float) this.temp[58] * this.scaleVx * this.amend - 3.6000001F - 0.35F;
        this.lead[153] = (float) this.temp[70] * this.scaleVx * this.amend - 3.6000001F - 0.35F;
        this.lead[155] = (float) this.temp_Last[11] * this.scaleVx * this.amend - 4.4F - 0.35F;
        this.lead[157] = (float) this.temp[11] * this.scaleVx * this.amend - 4.4F - 0.35F;
        this.lead[159] = (float) this.temp[23] * this.scaleVx * this.amend - 4.4F - 0.35F;
        this.lead[161] = (float) this.temp[35] * this.scaleVx * this.amend - 4.4F - 0.35F;
        this.lead[163] = (float) this.temp[47] * this.scaleVx * this.amend - 4.4F - 0.35F;
        this.lead[165] = (float) this.temp[59] * this.scaleVx * this.amend - 4.4F - 0.35F;
        this.lead[167] = (float) this.temp[71] * this.scaleVx * this.amend - 4.4F - 0.35F;
        this.temp_Last[0] = this.temp[60];
        this.temp_Last[1] = this.temp[61];
        this.temp_Last[2] = this.temp[62];
        this.temp_Last[3] = this.temp[63];
        this.temp_Last[4] = this.temp[64];
        this.temp_Last[5] = this.temp[65];
        this.temp_Last[6] = this.temp[66];
        this.temp_Last[7] = this.temp[67];
        this.temp_Last[8] = this.temp[68];
        this.temp_Last[9] = this.temp[69];
        this.temp_Last[10] = this.temp[70];
        this.temp_Last[11] = this.temp[71];
        if (this.mSpeed != this.xunit) {
            this.XUpdate();
        }
    }

    /**
     * 取出12个数据到temp
     */
    private void getEcgData() {
        int index = 0;
        while (index < 6 && this.isEcgQueue) {
            Short aShort = Short.valueOf(index + "");
            for (int i = 0; i < 12; i++) {
                this.temp[12 * index + i] = aShort.shortValue();
            }
           /* this.temp[12 * index + i] = val.shortValue();
            if (this.mEcgQueue != null && !this.mEcgQueue.isEmpty()) {
                for (int i = 0; i < 12 && this.isEcgQueue; ++i) {
                    Short val = (Short) this.mEcgQueue.poll();
                    if (val != null) {
                        this.temp[12 * index + i] = val.shortValue();
                    } else if (i > 0) {
                        --i;
                    }
                }
                ++index;
            } else if (this.mEcgQueue == null) {
                break;
            }*/
        }

    }

    /**
     * 刷新渲染数据
     */
    public void XUpdate() {
        this.xunit = this.mSpeed;

        for (int i = 0; i < 168; i += 14) {
            this.lead[i] = -3.0F * this.xunit;
            this.lead[2 + i] = -2.0F * this.xunit;
            this.lead[4 + i] = -this.xunit;
            this.lead[8 + i] = this.xunit;
            this.lead[10 + i] = 2.0F * this.xunit;
            this.lead[12 + i] = 3.0F * this.xunit;
        }

        this.rect[0] = -6.0F * this.xunit;
        this.rect[2] = 6.0F * this.xunit;
        this.rect[4] = 6.0F * this.xunit;
        this.rect[6] = -6.0F * this.xunit;
        this.rect[8] = 6.0F * this.xunit;
        this.rect[10] = -6.0F * this.xunit;
    }

    /**
     * 清空待绘制部分区域
     */
    public void ClearRect(GL10 gl) {
        gl.glLoadIdentity();
        gl.glTranslatef(this.CX, 0.0F, -5.0F);
        gl.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
        gl.glColor4f(0.0F, 0.0F, 0.0F, 0.0F);
        FloatBuffer verBuffer = makeFloatBuffer(this.rect);
        gl.glVertexPointer(2, 5126, 0, verBuffer);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDrawArrays(4, 0, 3);
        gl.glDrawArrays(4, 3, 3);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glFinish();
        this.CX += 6.0F * this.xunit;
        if (this.CX >= 10.12F) {
            this.CX = -9.0F;
        }
    }

    /**
     * 绘制波形
     */
    public void DrawWave(GL10 gl) {
        gl.glLoadIdentity();
        gl.glTranslatef(this.DX, 0.0F, -5.0F);
        gl.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
        gl.glColor4f(this.colorRED, this.colorGREEN, this.colorBLUE, this.colorALPHA);
        FloatBuffer verBuffer = makeFloatBuffer(this.lead);
        //单元长度 2 ,
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, verBuffer);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //以直线模式绘制
        //gl.glDrawArrays(GLES20.GL_LINE_STRIP, 0, 7);
        gl.glDrawArrays(3, 0, 7); //渲染第一组(四个)直线
        gl.glDrawArrays(3, 7, 7); //渲染第二组(四个)直线
        gl.glDrawArrays(3, 14, 7);//渲染第三组(四个)直线
        gl.glDrawArrays(3, 21, 7);
        gl.glDrawArrays(3, 28, 7);
        gl.glDrawArrays(3, 35, 7);
        gl.glDrawArrays(3, 42, 7);
        gl.glDrawArrays(3, 49, 7);
        gl.glDrawArrays(3, 56, 7);
        gl.glDrawArrays(3, 63, 7);
        gl.glDrawArrays(3, 70, 7);
        gl.glDrawArrays(3, 77, 7);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);//GL10.GL_VERTEX_ARRAY
        gl.glFinish();
        this.DX += 6.0F * this.xunit;
        if (this.DX >= 10.12F) {
            this.DX = -9.0F;
        }
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
//        this.onSet(gl);
        String vendor = gl.glGetString(GL10.GL_VENDOR);
        if (vendor.equals("Qualcomm")) {
            this.IsQualcomm = true;
            String renderer = gl.glGetString(GL10.GL_RENDERER);
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
                GLJNILIB.setPreserveAttrib();
                this.IsPowerVR = Boolean.valueOf(true);
            }
        } else {
            GLJNILIB.setPreserveAttrib();
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
        Log.e("w+h", width + "+" + height);
        this.DX = -9.0F;
        this.CX = -10.5F;
        this.DX62 = 1.3F;
        this.CX62 = 1.7F;
        this.DX621 = -9.0F;
        this.CX621 = -8.6F;
        this.IsDrawFont = Boolean.valueOf(true);
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio, ratio, -1.0F, 1.0F, 1.0F, 10.0F);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        this.mCounter = 0;
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        this.IsDrawFont = Boolean.valueOf(true);
//        gl.glClear(GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_COLOR_BUFFER_BIT);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
        gl.glClearDepthf(1.0F);
        gl.glLineWidth(1.75F);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glEnable(GL10.GL_LINE_SMOOTH);
        gl.glHint(GL10.GL_LINE_SMOOTH_HINT, GL10.GL_NICEST);
    }

    public void onSet(GL10 gl) {
        gl.glClear(GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_COLOR_BUFFER_BIT);
        //target , mode
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
        gl.glClearDepthf(1.0F);
        gl.glLineWidth(1.75F);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glEnable(GL10.GL_LINE_SMOOTH);
        gl.glHint(GL10.GL_LINE_SMOOTH_HINT, GL10.GL_NICEST);
    }


    public static FloatBuffer makeFloatBuffer(float[] arr) {
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(arr);
        fb.position(0);
        return fb;
    }

    public static Bitmap initFontBitmap(String font, int index) {
        Bitmap bitmap = Bitmap.createBitmap(64, 64, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(0);
        Paint p = new Paint();
        Typeface typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD);
        p.setAntiAlias(true);
        if (index % 3 == 0) {
            p.setColor(Color.argb(255, 144, 206, 245));
        } else {
            p.setColor(-16711936);
        }

        p.setTypeface(typeface);
        p.setTextSize(30.0F);
        canvas.drawText(font, 2.0F, 35.0F, p);
        return bitmap;
    }

}
