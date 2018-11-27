package com.example.day1127;


import android.content.Context;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;
import com.example.bean.UserBean;
import com.example.utils.NetUtils;
import com.youth.banner.Banner;

import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Banner banner;
    private TextView title,price,bargainPrice;
    private String url="http://www.zhaoapi.cn/product/getProductDetail?pid=1";
    private List<String> image=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner=findViewById(R.id.viewpager);
        title=findViewById(R.id.title);
        price=findViewById(R.id.price);
        bargainPrice=findViewById(R.id.bargainPrice);

        //设置banner样式（）
      // banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new ImageLoaderInterface<ImageView>() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
               // UserBean.DataBean benner = (UserBean.DataBean) path;

                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage((String)path,imageView);
            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return imageView;
            }
        });

        //加载数据
        initData();
    }
    //切割字符串
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
    private void initData() {
        NetUtils.getInsanner().getRequery(url, UserBean.class, new NetUtils.CallBack<UserBean>() {
            @Override
            public void onsuccess(UserBean o) {
              //  List<String>list = new ArrayList<>();
                String images = o.getData().getImages();
               /* String[] split = images.split("\\|");
                for (String s:split
                     ) {
                    list.add(s);
                }*/
                sub(o.getData().getImages());
                //设置图片集合
                banner.setImages(image);
                //banner设置方法全部调用完毕时最后调用
                banner.start();
                //textview赋值
                title.setText(o.getData().getTitle());
                price.setText("原价："+o.getData().getPrice());
                //删除线
                price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                bargainPrice.setText("优惠价："+o.getData().getBargainPrice());


            }
        });
    }



}
