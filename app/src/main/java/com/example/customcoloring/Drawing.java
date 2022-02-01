package com.example.customcoloring;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * A SurfaceView that contains all our elements for drawing.
 *
 * @author Vincent Robinson
 * @version 1/31/2022
 */
public class Drawing extends SurfaceView {
    ArrayList<Drawable> mDrawables;
    int mCurrentDrawable;

    public Drawing(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        mDrawables = new ArrayList<>();

        mDrawables.add(new DrawableRectangle(
                "Background",
                0xFFFFFFFF,
                new RectF(0, 0, 0, 0)
        ));
        mDrawables.add(new DrawableRectangle(
                "Box",
                0xFF00AAFF,
                new RectF(100, 100, 500, 500)
        ));

        mCurrentDrawable = 0;
    }

    public Drawable getCurrent() {
        return mDrawables.get(mCurrentDrawable);
    }

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
