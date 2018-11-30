package com.example.pillar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TreeView extends View {
    Paint mpaint;
    private List<Integer> list;
    public TreeView(Context context) {
        super(context);
        list=new ArrayList<>();
        init();
    }

    public TreeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
         mpaint = new Paint();
         mpaint.setColor(Color.RED);
         mpaint.setStrokeWidth(5);
         mpaint.setStyle(Paint.Style.FILL);
         invalidate();
    }
    public  void setdata(List<Integer> data){
        if(data!=null){
            list=data;
        }

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(50,50,50,350,mpaint);
        canvas.drawLine(50,350,350,350,mpaint);
        canvas.drawRect(80,350-(list.get(0)*300/100),100,350,mpaint);
        canvas.drawRect(120,350-(list.get(1)*300/100),140,350,mpaint);
        canvas.drawRect(160,350-(list.get(2)*300/100),180,350,mpaint);
    }
}
