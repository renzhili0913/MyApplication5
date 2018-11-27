package com.example.day1;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapter.ViewPagerAdapter;
import com.example.bean.UserBean;
import com.example.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TextView title,price,bargainPrice;
    private String url="http://www.zhaoapi.cn/product/getProductDetail?pid=1";
    private ViewPagerAdapter viewPagerAdapter;
    private List<String> image=new ArrayList<>();
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
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
                sub(o.getData().getImages());
                viewPagerAdapter.setList(image);
                title.setText(o.getData().getTitle());
                price.setText("原价："+o.getData().getPrice());
                //删除线
                price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                bargainPrice.setText("优惠价："+o.getData().getBargainPrice());

                int centent=viewPagerAdapter.getCount()/2;
                centent=centent-centent%(image.size());
                viewPager.setCurrentItem(centent);


                //轮播
                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,1000);
            }
        });
    }



    public void sub(String url){
        int i = url.indexOf("|");
        if (i>=0){
            String substring = url.substring(0, i);
            image.add(substring);
            sub(url.substring(i+1,url.length()));
        }else{
            image.add(url);
        }
    }
    private void initView() {
        viewPager=findViewById(R.id.viewpager);
        title=findViewById(R.id.title);
        price=findViewById(R.id.price);
        bargainPrice=findViewById(R.id.bargainPrice);
        //创建适配器
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);


    }
}
