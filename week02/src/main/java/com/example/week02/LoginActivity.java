package com.example.week02;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.week02.adapter.MyFragMentAdapter;
import com.example.week02.model.MyCallBack;

public class LoginActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyCallBack myCallBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_item);
        initView();
    }

    public void setonMyCallBack(MyCallBack myCallBack){
        this.myCallBack=myCallBack;
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if (myCallBack!=null) {
            myCallBack.setData(name);
        }
    }

    private void initView() {

        //获取资源id
        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);
        //创建适配器
        viewPager.setAdapter(new MyFragMentAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }


}
