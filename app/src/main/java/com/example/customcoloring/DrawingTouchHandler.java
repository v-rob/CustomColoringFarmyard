package com.example.customcoloring;

import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/*
 * External Citation
 * Date: 1/31/2022
 * Problem: Needed a way to track SeekBar changes
 * Resource: https://developer.android.com/reference/android/view/View.OnTouchListener
 * Solution: Searched the API documentation for an appropriate handler class.
 */

/**
 * A listener for detecting when a Drawable is touched and setting it as current.
 *
 * @author Vincent Robinson
 * @version 1/31/2022
 */
public class DrawingTouchHandler implements View.OnTouchListener {
    /** The Drawing that is being targeted by this listener. */
    private Drawing mDrawing;

    /** The TextView containing the name of the current Drawable. */
    private TextView mDrawableName;

    /** The SeekBar controlling the red component of the current Drawable. */
    private SeekBar mRedSeekBar;
    /** The SeekBar controlling the green component of the current Drawable. */
    private SeekBar mGreenSeekBar;
    /** The SeekBar controlling the blue component of the current Drawable. */
    private SeekBar mBlueSeekBar;

    /** The TextView containing the value of the red SeekBar. */
    private TextView mRedOrdinal;
    /** The TextView containing the value of the green SeekBar. */
    private TextView mGreenOrdinal;
    /** The TextView containing the value of the blue SeekBar. */
    private TextView mBlueOrdinal;

    /**
     * Constructs a new handler for touching Drawables and updating the relevant elements.
     *
     * @param drawing The Drawing that is being targeted by this listener.
     * @param drawableName The TextView containing the name of the current Drawable.
     * @param redSeekBar The SeekBar controlling the red component of the current Drawable.
     * @param greenSeekBar The SeekBar controlling the green component of the current Drawable.
     * @param blueSeekBar The SeekBar controlling the blue component of the current Drawable.
     * @param redOrdinal The TextView containing the value of the red SeekBar.
     * @param greenOrdinal The TextView containing the value of the green SeekBar.
     * @param blueOrdinal The TextView containing the value of the blue SeekBar.
     */
    public DrawingTouchHandler(Drawing drawing, TextView drawableName,
            SeekBar redSeekBar, SeekBar greenSeekBar, SeekBar blueSeekBar,
            TextView redOrdinal, TextView greenOrdinal, TextView blueOrdinal) {
        mDrawing = drawing;
        mDrawableName = drawableName;

        mRedSeekBar = redSeekBar;
        mGreenSeekBar = greenSeekBar;
        mBlueSeekBar = blueSeekBar;

        mRedOrdinal = redOrdinal;
        mGreenOrdinal = greenOrdinal;
        mBlueOrdinal = blueOrdinal;

        updateWidgets();
    }

    /**
     * The method handling the touch events. It updates the current Drawable and then
     * updates the relevant SeekBars and TextViews.
     *
     * @param view The view that was touched, which is ignored in favor of mDrawing.
     * @param motionEvent Contains the position of the touch event.
     * @return True since the event is always handled.
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        mDrawing.updateCurrent(motionEvent.getX(), motionEvent.getY());
        updateWidgets();
        return true;
    }

    /**
     * Updates a single color SeekBar and its associated TextView to their new values.
     *
     * @param seekBar The SeekBar to update.
     * @param ordinal The TextView associated with the SeekBar.
     * @param component The new value of the color component.
     */
    private void updateSeekBar(SeekBar seekBar, TextView ordinal, int component) {
        seekBar.setProgress(component);
        ordinal.setText("" + component);
    }

    /**
     * Updates all the SeekBars and TextViews associated with this listener's Drawing.
     */
    private void updateWidgets() {
        Drawable current = mDrawing.getCurrent();

        // Set the value of the color seekbars, which will trigger changes in the numbers
        // next to the seekbars as well.
        updateSeekBar(mRedSeekBar, mRedOrdinal, current.getRed());
        updateSeekBar(mGreenSeekBar, mGreenOrdinal, current.getGreen());
        updateSeekBar(mBlueSeekBar, mBlueOrdinal, current.getBlue());

        // Set the name of the now-current drawable up in the top bar.
        mDrawableName.setText(current.getName());
    }
}
