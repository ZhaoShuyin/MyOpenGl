package com.zsy.opengl.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @Title SurfaceView demo
 * @date 2019/11/6
 * @autor Zsy
 */

public class SurfaceActivity extends Activity {

    private MySurfaceView mySurfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySurfaceView = new MySurfaceView(this);
        setContentView(mySurfaceView);
    }


    class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

        SurfaceHolder holder;//通过持有的句柄holder获取画布进行绘制

        public MySurfaceView(Context context) {
            super(context);
            holder = getHolder();
        }

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        }
    }


    private void refresh() {
        Canvas canvas = mySurfaceView.holder.lockCanvas();

        //通过画布 canvas 进行绘制

        mySurfaceView.holder.unlockCanvasAndPost(canvas);
    }

}
