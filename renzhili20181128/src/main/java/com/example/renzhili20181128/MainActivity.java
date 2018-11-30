package com.example.renzhili20181128;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.adapter.ViewPageAdapter;
import com.example.bean.UserBean;
import com.example.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TextView title,price,bargainPrice;
    private String url="http://www.zhaoapi.cn/product/getProductDetail?pid=1";
    private ViewPageAdapter viewPageAdapter;
    private List<String> list = new ArrayList<>();
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            handler.sendEmptyMessageDelayed(0,1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        NetUtils.getInsanner().getRequery(url, UserBean.class, new NetUtils.CallBack<UserBean>() {
            @Override
            public void onsuccess(UserBean o) {
                String images = o.getData().getImages();
                sub(images);
                viewPageAdapter.setList(list);

               int centent= viewPageAdapter.getCount()/2;
                centent=centent-centent%list.size();
                viewPager.setCurrentItem(centent);


                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,1000);


                title.setText(o.getData().getTitle());
                price.setText("原价："+o.getData().getPrice());
                price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                bargainPrice.setText("优惠价："+o.getData().getBargainPrice());

            }
        });
    }

    private void initView() {
        viewPager=findViewById(R.id.viewpager);
        title=findViewById(R.id.title);
        price=findViewById(R.id.price);
        bargainPrice=findViewById(R.id.bargainPrice);
        //创建适配器
        viewPageAdapter = new ViewPageAdapter(this);
        viewPager.setAdapter(viewPageAdapter);


    }
    public void sub(String url){
        int i = url.indexOf("|");
        if (i>=0){
            String substring = url.substring(0, i);
            list.add(substring);
            sub(url.substring(i+1,url.length()));
        }else {
            list.add(url);
        }
    }
}
