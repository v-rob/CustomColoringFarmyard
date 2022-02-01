package com.example.customcoloring;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Represents a single drawable element in a Drawing.
 *
 * @author Vincent Robinson
 * @version 1/31/2022
 */
public abstract class Drawable {
    private String mName;
    private Paint mPaint;
    private RectF mRect;

    public Drawable(String name, int color, RectF rect) {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);

        mName = name;
        mRect = rect;

        setColor(color);
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    public int getColor() {
        return mPaint.getColor();
    }

    public void setRed(int red) {
        mPaint.setColor(Color.rgb(red, getGreen(), getBlue()));
    }

    public int getRed() {
        return Color.red(mPaint.getColor());
    }

    public void setGreen(int green) {
        mPaint.setColor(Color.rgb(getRed(), green, getBlue()));
    }

    public int getGreen() {
        return Color.green(mPaint.getColor());
    }

    public void setBlue(int blue) {
        mPaint.setColor(Color.rgb(getRed(), getGreen(), blue));
    }

    public int getBlue() {
        return Color.blue(mPaint.getColor());
    }

    public void setRect(RectF rect) {
        mRect = rect;
    }

    public RectF getRect() {
        return mRect;
    }

    public abstract void onDraw(Canvas canvas);

    public abstract boolean isPointInside(float x, float y);

    protected Paint getPaint() {
        return mPaint;
    }
}
