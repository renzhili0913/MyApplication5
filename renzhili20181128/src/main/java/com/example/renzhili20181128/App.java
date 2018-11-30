package com.example.renzhili20181128;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoader.getInstance().init(
                new ImageLoaderConfiguration.Builder(this)
                        .memoryCacheSize(50*1024*1024)
                        .memoryCacheSizePercentage(10)
                        .build()
        );
    }
}
