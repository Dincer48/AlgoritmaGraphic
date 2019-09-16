package com.example.dincerkizildere.algoritmagrafik.algorithm.tree;

import android.app.Activity;

import com.example.dincerkizildere.algoritmagrafik.DataUtils;
import com.example.dincerkizildere.algoritmagrafik.LogFragment;
import com.example.dincerkizildere.algoritmagrafik.algorithm.Algorithm;
import com.example.dincerkizildere.algoritmagrafik.algorithm.DataHandler;
import com.example.dincerkizildere.algoritmagrafik.algorithm.tree.bst.BinarySearchTree;
import com.example.dincerkizildere.algoritmagrafik.visualizer.ArrayVisualizer;
import com.example.dincerkizildere.algoritmagrafik.visualizer.BSTVisualizer;

public class BSTAlgorithm extends Algorithm implements DataHandler {

    public static final String START_BST_SEARCH = "start_bst_search";
    public static final String START_BST_INSERT = "start_bst_insert";

    private BSTVisualizer visualizer;
    private ArrayVisualizer arrayVisualizer;
    public BinarySearchTree b;

    public BSTAlgorithm(BSTVisualizer visualizer, Activity activity, LogFragment logFragment) {
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    @Override
    public void run() {
        super.run();
    }

    private void startBSTSearch() {
        int id = DataUtils.getRandomKeyFromBST();
        addLog("Aranıyor.. " + String.valueOf(id));
        BinarySearchTree.Node current = b.getRoot();
        addLog("Kökten başlayarak: " + current.data);
        highlightNode(current.data);
        sleep();
        while (current != null) {
            if (current.data == id) {
                addLog("Key " + String.valueOf(id) + " ikili arama ağacında bulundu");
                completed();
                break;
            } else if (current.data > id) {
                addLog("Giden " + current.data + " için " + current.left.data);
                highlightLine(current.data, current.left.data);
                current = current.left;
                highlightNode(current.data);
                sleep();
            } else {
                addLog("Giden " + current.data + " için " + current.right.data);
                highlightLine(current.data, current.right.data);
                current = current.right;
                highlightNode(current.data);
                sleep();
            }
        }

    }

    private void startBSTInsert() {
        int[] array = DataUtils.bst_array;
        BinarySearchTree tree = new BinarySearchTree();
        logArray("Eklenecek Öğeler - ",array);
        removeAllNodes();
        sleepFor(800);

        for (int i = 0; i < array.length; i++) {
            addLog("Ekleme " + array[i] + " ikili ağaçta.");
            tree.insert(array[i]);
            addNode(array[i]);
            highlightNode(array[i]);
            sleepFor(800);
        }

        highlightNode(-1);
        completed();
    }

    @Override
    public void onDataRecieved(Object data) {
        this.b = (BinarySearchTree) data;
    }

    @Override
    public void onMessageReceived(String message) {
        if (message.equals(START_BST_SEARCH)) {
            startExecution();
            startBSTSearch();
        } else if (message.equals(START_BST_INSERT)) {
            startExecution();
            startBSTInsert();
        }
    }

    public void setData(final BinarySearchTree b) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.setData(b);
                if (arrayVisualizer!=null) {
                    arrayVisualizer.setData(DataUtils.bst_array);
                }
            }
        });
        start();
        prepareHandler(this);
        sendData(b);
    }

    private void highlightNode(final int node) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlightNode(node);
                if (arrayVisualizer!=null) {
                    arrayVisualizer.highlightValue(node);
                }
            }
        });
    }

    private void highlightLine(final int start, final int end) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlightLine(start, end);
            }
        });
    }

    private void removeAllNodes() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.removeAllNodes();
            }
        });
    }

    private void addNode(final int n) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.addNode(n);
            }
        });
    }


    public void setArrayVisualizer(ArrayVisualizer arrayVisualizer) {
        this.arrayVisualizer = arrayVisualizer;
    }
}