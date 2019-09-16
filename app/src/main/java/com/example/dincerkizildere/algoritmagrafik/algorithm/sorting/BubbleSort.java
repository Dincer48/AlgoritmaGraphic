package com.example.dincerkizildere.algoritmagrafik.algorithm.sorting;

import android.app.Activity;

import com.example.dincerkizildere.algoritmagrafik.LogFragment;
import com.example.dincerkizildere.algoritmagrafik.algorithm.Algorithm;
import com.example.dincerkizildere.algoritmagrafik.algorithm.DataHandler;
import com.example.dincerkizildere.algoritmagrafik.visualizer.SortingVisualizer;

public class BubbleSort extends SortAlgorithm implements DataHandler {

    int[] array;

    public BubbleSort(SortingVisualizer visualizer, Activity activity, LogFragment logFragment) {
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    @Override
    public void run() {
        super.run();
    }

    private void sort() {
        logArray("Orjinal dizi - " ,array);

        for (int i = 0; i < array.length; i++) {
            addLog("Doing iteration - " + i);
            boolean swapped = false;
            for (int j = 0; j < array.length - 1; j++) {
                highlightTrace(j);
                sleep();
                if (array[j] > array[j + 1]) {
                    highlightSwap(j, j + 1);
                    addLog("Değişim " + array[j] + " ve " + array[j + 1]);
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                    sleep();
                }
            }
            if (!swapped) {
                break;
            }
            sleep();
        }
        addLog("Dizi Sıralandı");
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