package com.example.dincerkizildere.algoritmagrafik.visualizer.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.example.dincerkizildere.algoritmagrafik.algorithm.graph.WeightedGraph2;
import com.example.dincerkizildere.algoritmagrafik.visualizer.AlgorithmVisualizer;

import java.util.HashMap;
import java.util.Map;

public class WeightedGraphVisualizer2 extends AlgorithmVisualizer {

    private Paint circlePaint;
    private Paint textPaint;
    private Paint weightPaint;
    private Paint linePaint;
    private Paint circleHighlightPaint;
    private Paint lineHighlightPaint;
    private Rect bounds;

    private int highlightNode = -1;
    private int highlightLineStart = -1, highlightLineEnd = -1;
    private Map<Integer, Point> pointMap = new HashMap<>();

    private WeightedGraph2 graph;

    public WeightedGraphVisualizer2(Context context) {
        super(context);
        initialise();
    }

    public WeightedGraphVisualizer2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    private void initialise() {
        circlePaint = new Paint();
        textPaint = new Paint();

        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(getDimensionInPixelFromSP(15));
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);

        bounds = new Rect();
        textPaint.getTextBounds("0", 0, 1, bounds);

        weightPaint = new Paint(textPaint);
        weightPaint.setColor(Color.BLUE);
        weightPaint.setTextSize(getDimensionInPixelFromSP(14));

        circlePaint.setColor(Color.RED);
        circlePaint.setAntiAlias(true);

        linePaint = new Paint();
        linePaint.setStrokeWidth(5);
        linePaint.setColor(Color.BLACK);

        circleHighlightPaint = new Paint(circlePaint);
        circleHighlightPaint.setColor(Color.BLUE);

        lineHighlightPaint = new Paint(linePaint);
        lineHighlightPaint.setColor(Color.BLUE);
        lineHighlightPaint.setStrokeWidth(10);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getDimensionInPixel(280));
    }

    public void setData(WeightedGraph2 graph) {
        this.graph = graph;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (graph != null)
            drawGraph(canvas);
    }

    private void drawGraph(Canvas canvas) {

        int[][] vertices = {
                {0, 1, 2, 3, 4},
                {getWidth() - getDimensionInPixel(50), getWidth() / 2 + getDimensionInPixel(50), getWidth() / 2 - getDimensionInPixel(50), getDimensionInPixel(40), getWidth() / 2},
                {getHeight() / 2 + getDimensionInPixel(20), getDimensionInPixel(20), getDimensionInPixel(20), getHeight() / 2 + getDimensionInPixel(20), getHeight() - getDimensionInPixel(30)}
        };

        for (int i = 0; i < graph.size(); i++) {
            int node = vertices[0][i];
            Point p = new Point(vertices[1][i], vertices[2][i]);
            addNode(p, node);
        }

        drawNodes(canvas);
    }

    private void addNode(Point point, int i) {
        pointMap.put(i, point);
    }

    private void drawNodes(Canvas canvas) {
        for (Map.Entry<Integer, Point> entry : pointMap.entrySet()) {
            Integer key = entry.getKey();

            for (int i : graph.neighbors(key)) {
                drawNodeLine(canvas, key, i, (int) graph.getWeight(key, i));
            }

        }
        for (Map.Entry<Integer, Point> entry : pointMap.entrySet()) {
            Integer key = entry.getKey();
            Point value = entry.getValue();
            drawCircleTextNode(canvas, value, key);
        }
    }

    private void drawCircleTextNode(Canvas canvas, Point p, int number) {
        String text = String.valueOf(number);

        if (highlightNode == number) {
            canvas.drawCircle(p.x, p.y, getDimensionInPixel(15), circleHighlightPaint);
        } else {
            canvas.drawCircle(p.x, p.y, getDimensionInPixel(15), circlePaint);
        }
        int yOffset = bounds.height() / 2;

        canvas.drawText(text, p.x, p.y + yOffset, textPaint);

    }


    private void drawNodeLine(Canvas canvas, int s, int e, int weight) {

        Point start = pointMap.get(s);
        Point end = pointMap.get(e);

        int midx = (start.x + end.x) / 2;
        int midy = (start.y + end.y) / 2;

        boolean highlight = (highlightLineStart == s && highlightLineEnd == e);
        if (highlight) {
            canvas.drawLine(start.x, start.y, end.x, end.y, lineHighlightPaint);
        } else {
            canvas.drawLine(start.x, start.y, end.x, end.y, linePaint);
        }
        canvas.drawText(String.valueOf(weight), midx, midy, weightPaint);

    }

    public void highlightNode(int node) {
        this.highlightNode = node;
        invalidate();
    }

    public void highlightLine(int start, int end) {
        this.highlightLineStart = start;
        this.highlightLineEnd = end;
        invalidate();
    }


    @Override
    public void onCompleted() {
        this.highlightLineStart = -1;
        this.highlightLineEnd = -1;
        this.highlightNode = -1;
        invalidate();
    }

}
