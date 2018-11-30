package com.example.pillar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private List<Integer> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        list=new ArrayList<>();
        Intent intent=getIntent();
        int one = intent.getIntExtra("one", 0);
        int two = intent.getIntExtra("two", 0);
        int three = intent.getIntExtra("three", 0);
        list.add(one);
        list.add(two);
        list.add(three);
        TreeView treeView = findViewById(R.id.treeview);
        treeView.setdata(list);
    }
}
