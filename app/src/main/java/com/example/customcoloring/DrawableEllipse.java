package com.example.customcoloring;

import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Represents a drawable ellipse in a Drawing.
 *
 * @author Vincent Robinson
 * @version 1/31/2022
 */
public class DrawableEllipse extends Drawable {
    public DrawableEllipse(String name, int color, RectF rect) {
        super(name, color, rect);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawArc(getRect(), 0, 360, true, getPaint());
    }

    @Override
    public boolean isPointInside(float x, float y) {
        RectF rect = getRect();
        // Find whether the point is inside the ellipse via the checking whether plugging
        // the point into the general equation for an ellipse (x-h)^2/w + (y-k)^2/h
        // yields a value less than or equal to one.
        return Math.pow(x - rect.centerX(), 2) / rect.width() +
                Math.pow(y - rect.centerY(), 2) / rect.height() <= 1;
    }
}
