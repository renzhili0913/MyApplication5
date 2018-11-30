package com.example.day01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class AutoView extends View {
    Paint mpaint;
    public AutoView(Context context) {
        super(context);
        init();
    }

    public AutoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        mpaint=new Paint();
        mpaint.setColor(Color.RED);
        mpaint.setStrokeWidth(10);
        mpaint.setStyle(Paint.Style.FILL);

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0,0,0,300,mpaint);
        canvas.drawLine(0,300,300,300,mpaint);
        canvas.drawRect(50,50,80,300,mpaint);
        mpaint.setColor(Color.BLUE);
        canvas.drawRect(100,100,130,300,mpaint);
        mpaint.setColor(Color.GREEN);
        canvas.drawRect(150,50,180,300,mpaint);
    }
}
