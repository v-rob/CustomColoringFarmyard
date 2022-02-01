package com.example.customcoloring;

import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Represents a drawable rectangle in a Drawing.
 *
 * @author Vincent Robinson
 * @version 1/31/2022
 */
public class DrawableRectangle extends Drawable {
    public DrawableRectangle(String name, int color, RectF rect) {
        super(name, color, rect);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(getRect(), getPaint());
    }

    @Override
    public boolean isPointInside(float x, float y) {
        RectF rect = getRect();
        return x >= rect.left && x <= rect.right &&
                y >= rect.top && y <= rect.bottom;
    }
}
