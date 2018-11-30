package com.example.day01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class RingView extends View {
    Paint mpaint;
    RectF rectF;
    public RingView(Context context) {
        super(context);
        init();
    }

    public RingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mpaint=new Paint();
        mpaint.setColor(Color.RED);
        mpaint.setStrokeWidth(5);
        mpaint.setStyle(Paint.Style.FILL);

        rectF = new RectF();
        rectF.left=190;
        rectF.top=50;
        rectF.right=310;
        rectF.bottom=250;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rectF,60,60,true,mpaint);
        canvas.drawCircle(250,250,60,mpaint);
        mpaint.setColor(Color.BLACK);
        mpaint.setTextSize(28);
        canvas.drawText("start",220,260,mpaint);
    }
}
