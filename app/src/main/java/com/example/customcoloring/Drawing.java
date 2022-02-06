package com.example.customcoloring;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.ArrayList;

/*
 * External Citation
 * Date: 1/31/2022
 * Problem: Required a way to draw to an element
 * Resource: Dr. Nuxoll
 * Solution: Implemented SurfaceView and used the appropriate methods covered in
 *           the lecture.
 */

/**
 * A SurfaceView that contains all our elements for drawing.
 *
 * @author Vincent Robinson
 * @version 1/31/2022
 */
public class Drawing extends SurfaceView {
    /**
     * The list of all drawable elements: polygons, circles, and rectangles. When two
     * elements overlap, the later ones in the list are drawn last and tested first
     * for being touched.
     *
     * It must always have at least one Drawable, and the first Drawable must be a
     * DrawableRectangle, which will always have the size set as the size of the Drawing.
     */
    private ArrayList<Drawable> mDrawables;

    /**
     * The index of the current element in mDrawables that is having its colors modified.
     */
    private int mCurrentDrawable;

    /**
     * Constructor for the XML Drawing element. It creates the default set of Drawables,
     * setting the current drawable to the first one.
     *
     * @param context I don't know and don't care. Passed to the super constructor.
     * @param attrs Ditto.
     */
    public Drawing(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        mDrawables = new ArrayList<>();

        // Add all the Drawables here. If we were building a more extensible program,
        // e.g. allowing the user to insert and modify shapes, this would be put in a
        // more appropriate method. However, as it is, the constructor is probably fine.

        // The first element must always be a rectangle representing the background.
        mDrawables.add(new DrawableRectangle(
                "I see skies of blue",
                0xffbeeeed,
                new RectF(0, 0, 0, 0)
        ));

        // Barn
        mDrawables.add(new DrawablePolygon(
                "The front of Moo Moo Land",
                0xffbc0000,
                new float[][]{
                        {160, 650},
                        {160, 450},
                        {270, 386},
                        {382, 450},
                        {382, 650}
                }
        ));
        mDrawables.add(new DrawableRectangle(
                "You couldn't hit the broad side of a barn!",
                0xffd43737,
                new RectF(383, 450, 886, 650)
        ));
        mDrawables.add(new DrawablePolygon(
                "Pigeon City",
                0xff683300,
                new float[][]{
                        {271, 386},
                        {382, 449},
                        {886, 449},
                        {775, 386}
                }
        ));

        // Silo
        mDrawables.add(new DrawableRectangle(
                "Silo School of Engineering",
                0xff9e9893,
                new RectF(999, 304, 1173, 650)
        ));
        mDrawables.add(new DrawablePolygon(
                "This is actually the tip of a rocket hidden inside the silo",
                0xffa37951,
                new float[][]{
                        {990, 303},
                        {1086, 248},
                        {1182, 303}
                }
        ));

        // House
        mDrawables.add(new DrawableRectangle(
                "Bjarne Stroustrup's house (C++ rules, Java drools!)",
                0xff9d1b6e,
                new RectF(1421, 493, 1627, 600)
        ));
        mDrawables.add(new DrawablePolygon(
                "Math.ceil(), House.roof(), whatever",
                0xff683300,
                new float[][]{
                        {1407, 492},
                        {1444, 455},
                        {1604, 455},
                        {1641, 492}
                }
        ));

        // Sun
        mDrawables.add(new DrawableEllipse(
                "Sunny Boy",
                0xffffd200,
                new RectF(1686, 117, 1843, 274)
        ));

        // Grass
        mDrawables.add(new DrawablePolygon(
                "Roses are red / Grass is green / Java is lame / And Android's disgusting",
                0xff0c8800,
                new float[][]{
                        {0, 656},
                        {349, 615},
                        {948, 645},
                        {1308, 617},
                        {1649, 570},
                        {1999, 654},
                        {3000, 640},
                        {2000, 2000},
                        {0, 2000}
                }
        ));

        // The first drawable is set to the background
        mCurrentDrawable = 0;
    }

    /**
     * Gets the current Drawable, the one having its colors modified. Note that this
     * is the drawable itself, not the index, which is only stored internally.
     *
     * @return The current Drawable.
     */
    public Drawable getCurrent() {
        return mDrawables.get(mCurrentDrawable);
    }

    /**
     * Updates the current Drawable by searching through the list of Drawables backwards
     * and seeing if the point provided is inside each element.
     *
     * @param x The X position to find inside a Drawable
     * @param y The Y position to find inside a Drawable
     */
    public void updateCurrent(float x, float y) {
        // Default the touched element to the base element in case no others are touched.
        mCurrentDrawable = 0;

        // Iterate in reverse over the elements to find the topmost one that was touched.
        for (int i = mDrawables.size() - 1; i >= 0; i--) {
            Drawable drawable = mDrawables.get(i);
            if (drawable.isPointInside(x, y)) {
                mCurrentDrawable = i;
                break;
            }
        }
    }

    /**
     * Redraws the drawing by calling the onDraw of all the Drawables. It also updates
     * the size of the background Drawable to the size of the Drawing.
     *
     * @param canvas The canvas to draw to.
     */
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Set size of the base element to the size of the canvas
        RectF baseRect = mDrawables.get(0).getRect();
        baseRect.right = getWidth();
        baseRect.bottom = getHeight();

        // Draw elements in order of first on the bottom, last on top.
        for (int i = 0; i < mDrawables.size(); i++) {
            mDrawables.get(i).onDraw(canvas);
        }
    }
}
