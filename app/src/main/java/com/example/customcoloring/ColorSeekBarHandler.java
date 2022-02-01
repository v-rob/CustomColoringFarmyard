package com.example.customcoloring;

import android.widget.SeekBar;
import android.widget.TextView;

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

    private Drawing mDrawing;
    private int mColorToChange;

    private TextView mOrdinal;

    public ColorSeekBarHandler(Drawing drawing, int colorToChange, TextView ordinal) {
        mDrawing = drawing;
        mColorToChange = colorToChange;
        mOrdinal = ordinal;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        mOrdinal.setText("" + i);

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

        mDrawing.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { /* Unused */ }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { /* Unused */ }
}
