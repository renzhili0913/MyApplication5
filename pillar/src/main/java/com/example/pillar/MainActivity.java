package com.example.pillar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edit_ont,edit_two,edit_three;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        edit_ont=findViewById(R.id.edit_one);
        edit_two=findViewById(R.id.edit_two);
        edit_three=findViewById(R.id.edit_three);
        button=findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int one = Integer.parseInt(edit_ont.getText().toString().trim());
                int two = Integer.parseInt(edit_two.getText().toString().trim());
                int three = Integer.parseInt(edit_three.getText().toString().trim());
                if (one>=0&&one<=100&&two>=0&&two<=100&&three>=0&&three<=100){
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    intent.putExtra("one",one);
                    intent.putExtra("two",two);
                    intent.putExtra("three",three);
                    startActivity(intent);
                }else if(one>100||two>100||three>0){
                    edit_ont.setText(0+"");
                    edit_two.setText(0+"");
                    edit_three.setText(0+"");
                }

            }
        });
    }
}
