package com.example.week02;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.week02.bean.UserBean;
import com.example.week02.persenter.IPersenterImpl;
import com.example.week02.utils.NullUtils;
import com.example.week02.view.IView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {
    private EditText name,paw;
    private CheckBox remember_password,automatic_logon;
    private Button submission;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private IPersenterImpl iPersenter;
    private String url="http://www.xieast.com/api/user/login.php?username=%s&password=%s";
    private UserBean userBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iPersenter=new IPersenterImpl(this);
        initView();
    }

    private void initView() {
        preferences=getSharedPreferences("user",MODE_PRIVATE);
        editor=preferences.edit();
        //获取资源id
        name=findViewById(R.id.name);
        paw=findViewById(R.id.paw);
        remember_password=findViewById(R.id.remember_password);
        automatic_logon=findViewById(R.id.automatic_logon);
        submission=findViewById(R.id.submission);
        //取出记住密码状态
        boolean remember = preferences.getBoolean("remember", false);
        if (remember){
            String names = preferences.getString("names", null);
            String paws = preferences.getString("paws", null);
            name.setText(names);
            paw.setText(paws);
            remember_password.setChecked(true);
        }
        //取出自动登录状态
        boolean automatic = preferences.getBoolean("automatic", false);
        if (automatic){
           //登录跳转
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.putExtra("name",preferences.getString("name",null));
            startActivity(intent);
            finish();
        }
        //自动登录监听
        automatic_logon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    remember_password.setChecked(true);
                }else{
                    remember_password.setChecked(false);
                }
            }
        });
        //点击提交事件
        submission.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.submission:
                //获取输入框的值
                String names = name.getText().toString().trim();
                String paws = paw.getText().toString().trim();
                //判断记住密码是否选中
                if (remember_password.isChecked()){
                    editor.putString("names",names);
                    editor.putString("paws",paws);
                    editor.putBoolean("remember",true);
                    editor.commit();
                }else{
                    editor.clear();
                    editor.commit();
                }
                //判断自动登录是否选中
                if (automatic_logon.isChecked()){
                    editor.putBoolean("automatic",true);
                    editor.commit();
                }

                //判断是否为空
                if (NullUtils.getInsanner().isnull(names,paws)){
                    iPersenter.getRequeryData(String.format(url,names,paws),null,UserBean.class);
                }else{
                    Toast.makeText(MainActivity.this,"账号或密码不能为空",Toast.LENGTH_SHORT).show();
                }

                break;
                default:
                    break;
        }
    }


    @Override
    public void onSuccess(Object o) {
        //强转
        userBean = (UserBean) o;
        //记录
        editor.putString("name",userBean.getData().getName());
        editor.commit();
        Toast.makeText(MainActivity.this, userBean.getMsg(),Toast.LENGTH_SHORT).show();
        //登录成功跳转传值
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.putExtra("name", userBean.getData().getName());
        startActivity(intent);
        finish();
    }

    @Override
    public void onFail(String str) {
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPersenter.onDetach();
    }

}
