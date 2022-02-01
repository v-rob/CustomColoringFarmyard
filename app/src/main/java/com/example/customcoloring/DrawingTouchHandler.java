package com.example.customcoloring;

import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class DrawingTouchHandler implements View.OnTouchListener {
    private Drawing mDrawing;

    private TextView mDrawableName;

    private SeekBar mRedSeekBar;
    private SeekBar mGreenSeekBar;
    private SeekBar mBlueSeekBar;

    private TextView mRedOrdinal;
    private TextView mGreenOrdinal;
    private TextView mBlueOrdinal;

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

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        mDrawing.updateCurrent(motionEvent.getX(), motionEvent.getY());
        updateWidgets();
        return true;
    }

    private void updateSeekBar(SeekBar seekBar, TextView ordinal, int color) {
        seekBar.setProgress(color);
        ordinal.setText("" + color);
    }

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
