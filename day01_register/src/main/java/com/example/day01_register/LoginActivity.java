package com.example.day01_register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bean.UserBean;
import com.example.utils.NetUtils;

public class LoginActivity extends AppCompatActivity {
    private EditText name, password;
    private Button register, login;
    private String url_login = "http://120.27.23.105/user/reg?mobile=%s&password=%s";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框是值;
                String names = name.getText().toString().trim();
                String passwords = password.getText().toString().trim();

                if (names.equals("")||passwords.equals("")){
                    Toast.makeText(LoginActivity.this, "请输入账号密码", Toast.LENGTH_LONG).show();
                } else{
                    initData(names,passwords);

                }
            }
        });
    }

    private void initData(final String names, String passwords) {

        NetUtils.getInsanner().getRequery(String.format(url_login, names, passwords), UserBean.class, new NetUtils.CallBack<UserBean>() {
            @Override
            public void onsuccess(UserBean o) {

             if(o.getMsg().equals("天呢！用户已注册")){
                    Toast.makeText(LoginActivity.this, "注册不成功,天呢！用户已注册", Toast.LENGTH_LONG).show();
                }else{
                 Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                 Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                 startActivity(intent);
             }
            }
        });
    }
}
