package com.example.customcoloring;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * Represents a drawable polygon in a Drawing.
 *
 * @author Vincent Robinson
 * @version 1/31/2022
 */
public class DrawablePolygon extends Drawable {
    /** The path of the polygon to draw. */
    private Path mPath;

    /**
     * Creates a new polygon Drawable.
     *
     * @param name The name to give this polygon.
     * @param color The initial color of the polygon.
     * @param points An array of all the vertices in the polygon.
     */
    public DrawablePolygon(String name, int color, float[][] points) {
        // Set the rect to null as it will be set in setPoints()
        super(name, color, null);

        setPoints(points);
    }

    /**
     * Gets the Path describing this polygon.
     *
     * @return The Path describing the polygon.
     */
    public Path getPath() {
        return mPath;
    }

    /**
     * Sets all the vertices describing the polygon to be drawn.
     *
     * @param points An array of all the vertices in the polygon.
     */
    public void setPoints(float[][] points) {
        /*
         * External Citation
         * Date: 2/5/2022
         * Problem: Needed a way to draw a polygon
         * Resource: https://stackoverflow.com/questions/2047573/how-to-draw-filled-polygon
         * Solution: Used Path with the methods moveTo and lineTo from the accepted answer.
         */

        mPath = new Path();

        // Move to the position of the last point.
        float[] point = points[points.length - 1];
        mPath.moveTo(point[0], point[1]);

        // Set the initial rect to be a sizeless rectangle only including the last point.
        RectF rect = new RectF(point[0], point[1], point[0], point[1]);

        // Draw lines to all subsequent points. A line will be drawn to the last one
        // as well. Also compute the bounds of the bounding rect.
        for (int i = 0; i < points.length; i++) {
            point = points[i];
            mPath.lineTo(point[0], point[1]);

            // Increase the bounds of the bounding rectangle, if necessary
            if (point[0] < rect.left)
                rect.left = point[0];
            if (point[0] > rect.right)
                rect.right = point[0];

            if (point[1] < rect.top)
                rect.top = point[1];
            if (point[1] > rect.bottom)
                rect.bottom = point[1];
        }

        // Apply the bounding rectangle
        setRect(rect);
    }

    /**
     * Draws the polygon to the screen.
     *
     * @param canvas The Canvas to be drawn to.
     */
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawPath(mPath, getPaint());
    }
}
