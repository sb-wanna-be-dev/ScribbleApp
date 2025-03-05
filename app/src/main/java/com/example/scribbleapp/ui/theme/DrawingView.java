package com.example.scribbleapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawingView extends View {
    private Path currentPath;
    private Paint paintBrush;
    private List<PathPaint> paths;

    // Inner class to hold Path and Paint together
    private static class PathPaint {
        Path path;
        Paint paint;

        PathPaint(Path path, Paint paint) {
            this.path = path;
            this.paint = paint;
        }
    }

    public DrawingView(Context context) {
        this(context, null);
    }

    public DrawingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        currentPath = new Path();
        paths = new ArrayList<>();

        paintBrush = new Paint();
        paintBrush.setColor(Color.BLACK);
        paintBrush.setAntiAlias(true);
        paintBrush.setStrokeWidth(10f);
        paintBrush.setStyle(Paint.Style.STROKE);
        paintBrush.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw all previous paths
        for (PathPaint pathPaint : paths) {
            canvas.drawPath(pathPaint.path, pathPaint.paint);
        }

        // Draw current path
        canvas.drawPath(currentPath, paintBrush);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentPath.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                currentPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                paths.add(new PathPaint(currentPath, new Paint(paintBrush)));
                currentPath = new Path();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    public void changeColor(int color) {
        paintBrush.setColor(color);
    }

    public void changeBrushSize(float size) {
        paintBrush.setStrokeWidth(size);
    }

    public void clearCanvas() {
        paths.clear();
        currentPath.reset();
        invalidate();
    }
}