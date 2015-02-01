package com.nomi.artwatch;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Ryota Niinomi on 15/02/01.
 */
public class Ball {

    private static final int ALPHA = 120;
    public int radius;
    public int posX;
    public int posY;
    public int velocity;
    public int dirX;
    public int dirY;
    public int color;
    public int alpha = ALPHA;

    public Ball(int r, int x, int y, int v, int _dirX, int _dirY) {
        Random random = new Random();
        radius   = r;
        posX     = x;
        posY     = y;
        velocity = v;
        dirX     = _dirX;
        dirY     = _dirY;
        color    = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    public void setPosX(int x) {
        posX += x;
    }

    public void setPosY(int y) {
        posY += y;
    }

    public void updateDirX() {
        dirX *= -1;
    }

    public void updateDirY() {
        dirY *= -1;
    }

    public void setColor() {
        Random random = new Random();
        color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
}
