package com.example.customcoloring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * The main class that sets up elements, binds event handlers, and then lets the
 * program run via events in all the other classes.
 *
 * @author Vincent Robinson
 * @version 1/31/2022
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Binds all event listeners for all elements when they're all created.
     *
     * @param savedInstanceState I have no idea and have no use for it.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView drawableName = findViewById(R.id.drawableName);

        SeekBar redSeekBar = findViewById(R.id.redSeekBar);
        SeekBar greenSeekBar = findViewById(R.id.greenSeekBar);
        SeekBar blueSeekBar = findViewById(R.id.blueSeekBar);

        TextView redOrdinal = findViewById(R.id.redOrdinal);
        TextView greenOrdinal = findViewById(R.id.greenOrdinal);
        TextView blueOrdinal = findViewById(R.id.blueOrdinal);

        Drawing drawing = findViewById(R.id.drawing);
        View.OnTouchListener touchListener = new DrawingTouchHandler(drawing, drawableName,
                redSeekBar, greenSeekBar, blueSeekBar, redOrdinal, greenOrdinal, blueOrdinal);
        drawing.setOnTouchListener(touchListener);

        SeekBar.OnSeekBarChangeListener seekBarListener;

        seekBarListener = new ColorSeekBarHandler(drawing,
                ColorSeekBarHandler.CHANGE_RED, redOrdinal);
        redSeekBar.setOnSeekBarChangeListener(seekBarListener);

        seekBarListener = new ColorSeekBarHandler(drawing,
                ColorSeekBarHandler.CHANGE_GREEN, greenOrdinal);
        greenSeekBar.setOnSeekBarChangeListener(seekBarListener);

        seekBarListener = new ColorSeekBarHandler(drawing,
                ColorSeekBarHandler.CHANGE_BLUE, blueOrdinal);
        blueSeekBar.setOnSeekBarChangeListener(seekBarListener);
    }
}