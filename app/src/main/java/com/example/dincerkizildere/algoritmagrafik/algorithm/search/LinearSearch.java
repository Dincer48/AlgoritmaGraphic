package com.example.dincerkizildere.algoritmagrafik.algorithm.search;

import android.app.Activity;

import com.example.dincerkizildere.algoritmagrafik.LogFragment;
import com.example.dincerkizildere.algoritmagrafik.algorithm.Algorithm;
import com.example.dincerkizildere.algoritmagrafik.algorithm.DataHandler;
import com.example.dincerkizildere.algoritmagrafik.visualizer.BinarySearchVisualizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LinearSearch extends Algorithm implements DataHandler {

    private BinarySearchVisualizer visualizer;
    private int[] array;

    private List<Integer> positions = new ArrayList<>();

    public LinearSearch(BinarySearchVisualizer visualizer, Activity activity, LogFragment logFragment) {
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    public void setData(final int[] array) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.setData(array);
            }
        });
        start();
        prepareHandler(this);
        sendData(array);
    }

    private void search() {

        logArray("Array - ", array);

        int rnd = new Random().nextInt(array.length);
        int data = array[rnd];
        addLog("Searching for - " + data);

        int n = array.length ;

        for (int i = 0 ; i<n ; i++) {

            highlight(0, i-1);
            highlightTrace(i);
            addLog("Searching at index - " + i);

            if (array[i] == data){
                addLog("Result - True");
                addLog("Value found at position - " + i);
                break;
            }

            addLog("Result - False");

            sleep();

        }

        completed();
    }

    private void highlight(int minIndex, int maxIndex) {
        positions.clear();
        for (int i = minIndex; i <= maxIndex; i++) {
            positions.add(i);
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlight(positions);
            }
        });
    }

    private void highlightTrace(final int pos) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlightTrace(pos);
            }
        });
    }

    @Override
    public void onMessageReceived(String message) {
        if (message.equals(Algorithm.COMMAND_START_ALGORITHM)) {
            startExecution();
            search();
        }
    }

    @Override
    public void onDataRecieved(Object data) {
        this.array = (int[]) data;
    }
}