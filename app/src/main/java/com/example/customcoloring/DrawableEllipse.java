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
    /**
     * Creates a new elliptical Drawable.
     *
     * @param name The name to give this ellipse.
     * @param color The initial color of the ellipse.
     * @param rect The bounds of the ellipse to draw.
     */
    public DrawableEllipse(String name, int color, RectF rect) {
        super(name, color, rect);
    }

    /**
     * Draws the ellipse to the screen.
     *
     * @param canvas The Canvas to be drawn to.
     */
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawArc(getRect(), 0, 360, true, getPaint());
    }
}
