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
    /**
     * Creates a new rectangular Drawable.
     *
     * @param name The name to give this rectangle.
     * @param color The initial color of the rectangle.
     * @param rect The bounds of the rectangle to draw.
     */
    public DrawableRectangle(String name, int color, RectF rect) {
        super(name, color, rect);
    }

    /**
     * Draws the rectangle to the screen.
     *
     * @param canvas The Canvas to be drawn to.
     */
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(getRect(), getPaint());
    }
}
