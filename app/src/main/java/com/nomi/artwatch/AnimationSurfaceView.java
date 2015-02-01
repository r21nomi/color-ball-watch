package com.nomi.artwatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class AnimationSurfaceView extends SurfaceView implements Runnable, SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder;
    private Thread thread;
    private int screen_width;
    private int screen_height;
    private ArrayList<Ball> ballList = new ArrayList<>();

    public AnimationSurfaceView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void run() {
        Canvas canvas = null;
        Paint paint   = new Paint();
        Paint bgPaint = new Paint();

        // Background
        bgPaint.setStyle(Style.FILL);
        bgPaint.setColor(Color.BLACK);

        // Ball
        paint.setStyle(Style.FILL);

        while(thread != null){
            try{
                canvas = surfaceHolder.lockCanvas();

                // canvasを塗りつぶす（リセット効果）
                canvas.drawRect(0, 0, screen_width, screen_height, bgPaint);

                for (int i = 0, len = ballList.size(); i < len; i++) {
                    Ball ball = ballList.get(i);

                    int new_cx = ball.posX + ball.velocity * ball.dirX;
                    int new_cy = ball.posY + ball.velocity * ball.dirY;

                    // 跳ね返りの計算
                    if (new_cx - ball.radius < 0 || new_cx + ball.radius > screen_width) {
                        ball.updateDirX();
                        ball.setColor();
                    }
                    if (new_cy - ball.radius < 0 || new_cy + ball.radius > screen_height) {
                        ball.updateDirY();
                        ball.setColor();
                    }

                    // 色を設定
                    paint.setColor(ball.color);
                    paint.setAlpha(ball.alpha);

                    // ボールの位置を更新
                    ball.setPosX(ball.velocity * ball.dirX);
                    ball.setPosY(ball.velocity * ball.dirY);

                    // 描画
                    canvas.drawCircle(ball.posX, ball.posY, ball.radius, paint);
                }

                surfaceHolder.unlockCanvasAndPost(canvas);
            } catch(Exception e){
                Log.d("Exception", e.getMessage());
            }
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        screen_width  = width;
        screen_height = height;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread = null;
    }

    /**
     * ボールの追加
     * @param radius
     * @param x
     * @param y
     * @param velocity
     */
    public void addBall(int radius, int x, int y, int velocity) {
        int dirArray[] = {1, -1};
        Random random  = new Random();
        Ball ball      = new Ball(radius, x, y, velocity, dirArray[random.nextInt(2)], dirArray[random.nextInt(2)]);
        ballList.add(ball);
    }
}