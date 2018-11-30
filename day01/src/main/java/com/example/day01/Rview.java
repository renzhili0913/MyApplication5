package com.example.day01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class Rview extends View {
    Paint mpaint;
    RectF rectF;
    float start=0;
    public Rview(Context context) {
        super(context);
        init();
    }

    public Rview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mpaint=new Paint();
        mpaint.setColor(Color.RED);
        mpaint.setStrokeWidth(5);
        mpaint.setStyle(Paint.Style.FILL);

        rectF = new RectF();
        rectF.left=100;
        rectF.top=100;
        rectF.right=400;
        rectF.bottom=400;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0;i<6;i++){
            if (i%2==0){
                mpaint.setColor(Color.GREEN);
            }else{
                mpaint.setColor(Color.BLUE);
            }
           canvas.drawArc(rectF,start,60,true,mpaint);
            start+=60;
        }
        mpaint.setColor(Color.BLACK);
        mpaint.setTextSize(20);
        canvas.drawText("一等奖",120,200,mpaint);
    }
}
