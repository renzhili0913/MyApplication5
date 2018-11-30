package com.example.flowlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private Ylayout ylayout;
    private List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        editText=findViewById(R.id.edit_text);
        button=findViewById(R.id.button);
        ylayout=findViewById(R.id.ylayout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的值
                String trim = editText.getText().toString().trim();
                //添加到集合中
                list.add(trim);
                //设置数据到自定义view
                ylayout.setList(list);
            }
        });
    }
}
