package com.example.dincerkizildere.algoritmagrafik.visualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class Array2DVisualizer extends AlgorithmVisualizer {

    private int[][] array;

    public Array2DVisualizer(Context context) {
        super(context);
        initialise();
    }

    public Array2DVisualizer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    private void initialise() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setData(int[][] data) {
        this.array = data;
        invalidate();
    }

    @Override
    public void onCompleted() {

    }
}