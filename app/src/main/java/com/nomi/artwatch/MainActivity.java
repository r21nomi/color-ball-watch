package com.nomi.artwatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {

    private static final int BALL_RADIUS = 40;
    private AnimationSurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView = new AnimationSurfaceView(this);
        setContentView(surfaceView);

        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                surfaceView.addBall(BALL_RADIUS, (int)event.getX(), (int)event.getY(), (int)event.getX()/10);
                return false;
            }
        });
    }
}
