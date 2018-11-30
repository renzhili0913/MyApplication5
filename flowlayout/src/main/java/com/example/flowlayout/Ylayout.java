package com.example.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Ylayout extends LinearLayout {
    private List<String> list;
    private Context context;
    private final int mMaxSize=22;
    public Ylayout(Context context) {
        super(context);
        this.context=context;
        list=new ArrayList<>();
        init();
    }

    public Ylayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        list=new ArrayList<>();
        init();
    }

    private void init() {
        // 设置最外层的LinearLayout为垂直布局
        setOrientation(VERTICAL);
    }

    public void setList(List<String> list) {
        this.list = list;
        showData();
    }

    private void showData() {
        //因为每一次都要新画，所以移除以前所有的布局
        removeAllViews();
        //优先向跟布局添加一条横向布局
        LinearLayout linearLayout_H= (LinearLayout) View.inflate(context,R.layout.h_item,null);
        addView(linearLayout_H);
        // 定义临时变量，用来计算最后一行已有的字符串长度
        int len = 0;
        for (String str:list
             ) {
            len+=str.length();
            if (len>mMaxSize){
                 linearLayout_H= (LinearLayout) View.inflate(context,R.layout.h_item,null);
                addView(linearLayout_H);
                len=str.length();
            }
            View view = View.inflate(context,R.layout.item_water_fall,null);
            TextView textView = view.findViewById(R.id.text_item);
            textView.setText(str);
            linearLayout_H.addView(view);

            // 设置权重，让每一行内所有控件相加充满整行，并合理分配
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)view.getLayoutParams();
            layoutParams.weight = 1;
            view.setLayoutParams(layoutParams);

        }

    }


}
