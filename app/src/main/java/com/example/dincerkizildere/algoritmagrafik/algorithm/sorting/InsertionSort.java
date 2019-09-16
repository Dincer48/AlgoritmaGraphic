package com.example.dincerkizildere.algoritmagrafik.algorithm.sorting;

import android.app.Activity;

import com.example.dincerkizildere.algoritmagrafik.LogFragment;
import com.example.dincerkizildere.algoritmagrafik.algorithm.Algorithm;
import com.example.dincerkizildere.algoritmagrafik.visualizer.SortingVisualizer;

public class InsertionSort extends SortAlgorithm {

    int[] array;

    public InsertionSort(SortingVisualizer visualizer, Activity activity, LogFragment logFragment) {
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    @Override
    public void run() {
        super.run();
    }

    private void sort() {
        logArray("Original array - " ,array);

        int n = array.length;
        for (int j = 1; j < n; j++) {
            int key = array[j];
            int i = j-1;
            while ( (i > -1) && ( array [i] > key ) ) {
                array [i+1] = array [i];
                highlightSwap(i, i + 1);
                addLog("Swapping " + array[i] + " and " + array[i + 1]);
                i--;
            }
            sleep();
            array[i+1] = key;
        }
        addLog("Array has been sorted");
        completed();
    }

    @Override
    public void onDataRecieved(Object data) {
        super.onDataRecieved(data);
        this.array = (int[]) data;
    }

    @Override
    public void onMessageReceived(String message) {
        super.onMessageReceived(message);
        if (message.equals(Algorithm.COMMAND_START_ALGORITHM)) {
            startExecution();
            sort();
        }
    }
}