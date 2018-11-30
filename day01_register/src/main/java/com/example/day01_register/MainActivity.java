package com.example.day01_register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bean.UserBean;
import com.example.utils.NetUtils;

public class MainActivity extends AppCompatActivity {

    private EditText name, password;
    private Button register, login;
    private String url_register = "http://120.27.23.105/user/login?mobile=%s&password=%s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initData(String names,String passwords) {
        NetUtils.getInsanner().getRequery(String.format(url_register,names,passwords), UserBean.class, new NetUtils.CallBack<UserBean>() {
            @Override
            public void onsuccess(UserBean o) {
                if (o==null||!o.isSuccess()){
                    Toast.makeText(MainActivity.this, "匹配不成功", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "匹配成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,CommodityActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void initView() {
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        //注册
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //登录
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框是值;
                String names = name.getText().toString().trim();
                String passwords = password.getText().toString().trim();
                if (names.equals("")||passwords.equals("")){
                    Toast.makeText(MainActivity.this, "请输入账号密码", Toast.LENGTH_LONG).show();
               } else{
                    initData(names,passwords);

                }

            }

        });

    }
    //验证手机号的方法
    private boolean checkString (String s){
        return s.matches("[1][358]\\\\d{9}");

    }
    //验证密码
    private boolean checkpass(String s){
        return s.matches("^(?=.*?[A-Z])(?=.*?[0-9])[a-zA-Z0-9]{7,}$");
    }
}