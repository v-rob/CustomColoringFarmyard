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
    /** The name of this Drawable, shown in the TextView at the top of the screen. */
    private String mName;

    /**
     * The Paint used when drawing this Drawable. It is always set to fill and stroke,
     * but the color may change throughout the lifetime of the Drawable.
     */
    private Paint mPaint;

    /*
     * External Citation
     * Date: 1/31/2022
     * Problem: Required a rectangle class.
     * Resource: https://developer.android.com/reference/android/graphics/RectF
     * Solution: Android has a class built-in with useful methods
     */

    /**
     * The bounding rectangle of this Drawable. In the case of rectangles and ellipses,
     * it defines where to draw it. In the case of polygons, it is only the outer bounds
     * of the polygon, used for isPointInside(), and has no bearing how it is drawn.
     */
    private RectF mRect;

    /**
     * Creates a new drawing with the specified name, color, and rectangle.
     *
     * @param name The name of the Drawable.
     * @param color The initial color of the Drawable.
     * @param rect The bounding rectangle; may be null as long as it is set with
     *             setRect() before any other functions requiring a rectangle are called.
     */
    public Drawable(String name, int color, RectF rect) {
        /*
         * External Citation
         * Date: 2/5/2022
         * Problem: Needed a way for the drawing functions to fill the inside AND the
         *          outer border.
         * Resource: https://developer.android.com/reference/android/graphics/Paint and
         *           https://developer.android.com/reference/android/graphics/Paint.Style
         * Solution: Use FILL_AND_STROKE and set the stroke width to 1.
         */
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(1.0f);

        mName = name;
        mRect = rect;

        setColor(color);
    }

    /**
     * Sets the name of the Drawable.
     *
     * @param name The new name of the Drawable.
     */
    public void setName(String name) {
        mName = name;
    }

    /**
     * Gets the name of the Drawable.
     *
     * @return The name of the Drawable.
     */
    public String getName() {
        return mName;
    }

    /**
     * Sets this Drawable's color.
     *
     * @param color The new color to give to the Drawable.
     */
    public void setColor(int color) {
        mPaint.setColor(color);
    }

    /**
     * Gets this Drawable's color.
     *
     * @return The color of the Drawable. Must be a valid color integer.
     */
    public int getColor() {
        return mPaint.getColor();
    }

    /*
     * External Citation
     * Date: 1/31/2022
     * Problem: Needed a way to extract and add color components to a color integer.
     * Resource: https://developer.android.com/reference/android/graphics/Color
     * Solution: Found the proper methods from the API documentation.
     */

    /**
     * Sets the red component of the color.
     *
     * @param red The new red component of the color. Must be between 0 and 255.
     */
    public void setRed(int red) {
        mPaint.setColor(Color.rgb(red, getGreen(), getBlue()));
    }

    /**
     * Gets the red component of the color.
     *
     * @return The red component of the color. Will be between 0 and 255.
     */
    public int getRed() {
        return Color.red(mPaint.getColor());
    }

    /**
     * Sets the green component of the color.
     *
     * @param green The new green component of the color. Must be between 0 and 255.
     */
    public void setGreen(int green) {
        mPaint.setColor(Color.rgb(getRed(), green, getBlue()));
    }

    /**
     * Gets the green component of the color.
     *
     * @return The green component of the color. Will be between 0 and 255.
     */
    public int getGreen() {
        return Color.green(mPaint.getColor());
    }

    /**
     * Sets the blue component of the color.
     *
     * @param blue The new blue component of the color. Must be between 0 and 255.
     */
    public void setBlue(int blue) {
        mPaint.setColor(Color.rgb(getRed(), getGreen(), blue));
    }

    /**
     * Gets the blue component of the color.
     *
     * @return The blue component of the color. Will be between 0 and 255.
     */
    public int getBlue() {
        return Color.blue(mPaint.getColor());
    }

    /**
     * Gets the Paint internally used by this Drawable for drawing.
     *
     * @return The internal Paint of this Drawable.
     */
    protected Paint getPaint() {
        return mPaint;
    }

    /**
     * Sets the bounding rectangle.
     *
     * @param rect The new bounding rectangle to set.
     */
    public void setRect(RectF rect) {
        mRect = rect;
    }

    /**
     * Gets the bounding rectangle.
     *
     * @return The bounding rectangle.
     */
    public RectF getRect() {
        return mRect;
    }

    /**
     * Checks whether a position is inside this Drawable. For simplicity's sake, the
     * point is only checked to see if it is inside the bounding rectangle, so only
     * approximate detection is done for ellipses and polygons.
     *
     * @param x The X position of the point to check.
     * @param y The Y position of the point to check.
     * @return True if the point is inside, false if not.
     */
    public boolean isPointInside(float x, float y) {
        // Approximate by checking if the point is inside the bounding rectangle.
        return x >= mRect.left && x <= mRect.right &&
                y >= mRect.top && y <= mRect.bottom;
    }

    /**
     * Called from Drawing to draw each Drawable. Subclasses of Drawable are required
     * to implement this method.
     *
     * @param canvas The Canvas to be drawn to.
     */
    public abstract void onDraw(Canvas canvas);
}
