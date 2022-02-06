package com.example.customcoloring;

import android.widget.SeekBar;
import android.widget.TextView;

/*
 * External Citation
 * Date: 1/31/2022
 * Problem: Needed a way to track SeekBar changes
 * Resource: Dr. Nuxoll
 * Solution: Implemented the class from the lecture code.
 */

/**
 * Handles color seekbar changes and updates the current element.
 *
 * @author Vincent Robinson
 * @version 1/31/2022
 */
public class ColorSeekBarHandler implements SeekBar.OnSeekBarChangeListener {
    public static final int CHANGE_RED = 0;
    public static final int CHANGE_GREEN = 1;
    public static final int CHANGE_BLUE = 2;

    /** The Drawing that this color SeekBar controls. */
    private Drawing mDrawing;

    /**
     * Which color component is controlled by this SeekBar, which is one of CHANGE_RED,
     * CHANGE_GREEN, or CHANGE_BLUE.
     */
    private int mColorToChange;

    /** The TextView that shows the value of this SeekBar. */
    private TextView mOrdinal;

    /**
     * Creates a new handler for a single color SeekBar.
     *
     * @param drawing The Drawing controlled by this color SeekBar.
     * @param colorToChange The color component to control, which shares one of the
     *                      same values as mColorToChange.
     * @param ordinal The TextView associated with this SeekBar that reflects the value.
     */
    public ColorSeekBarHandler(Drawing drawing, int colorToChange, TextView ordinal) {
        mDrawing = drawing;
        mColorToChange = colorToChange;
        mOrdinal = ordinal;
    }

    /**
     * Called when a color component SeekBar changes; it updates the color component of
     * the current Drawable and sets the current value to the associated value TextView.
     *
     * @param seekBar The SeekBar that was changed. Ignored.
     * @param i The new value of the SeekBar.
     * @param b Whether it was a user change. Ignored.
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        mOrdinal.setText("" + i);

        // Set the component of the color that this seekbar controls.
        Drawable current = mDrawing.getCurrent();
        switch (mColorToChange) {
            case CHANGE_RED:
                current.setRed(i);
                break;
            case CHANGE_GREEN:
                current.setGreen(i);
                break;
            case CHANGE_BLUE:
                current.setBlue(i);
                break;
        }

        // We need the Drawing to be redrawn.
        mDrawing.invalidate();
    }

    /** Unused method, but required to be implemented by OnSeekBarChangeListener. */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    /** Unused method, but required to be implemented by OnSeekBarChangeListener. */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
