package com.example.com418.m8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyViewClass extends View {
    int startX=-1,startY=-1,stopX=-1,stopY=-1;
    int curShape=LINE;
    static final int LINE=1, CIRCLE=2,RECTANGLE=3;
    public MyViewClass(Context context) {
        super(context);
    }

    public MyViewClass(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getX();
                startY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("hwang", "move:x=" + event.getX() + "  y=" + event.getY());
            case MotionEvent.ACTION_UP:
                stopX = (int) event.getX();
                stopY = (int) event.getY();
                this.invalidate();
                break;
        }
        return true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);

        switch (curShape) {
            case LINE:
                canvas.drawLine(startX, startY, stopX, stopY, paint);
                break;
            case CIRCLE:
                int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2)
                        + Math.pow(stopY - startY, 2));
                canvas.drawCircle(startX, startY, radius, paint);
                break;

            case RECTANGLE:
                canvas.drawRect(startX, startY, stopX, stopY, paint);
                break;

        }


    }

}

