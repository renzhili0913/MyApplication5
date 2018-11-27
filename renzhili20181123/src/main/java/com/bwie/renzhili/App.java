package com.bwie.renzhili;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
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
                        .defaultDisplayImageOptions(
                                new DisplayImageOptions.Builder()
                                        .cacheOnDisk(true)
                                        .cacheInMemory(true)
                                        .showImageOnFail(R.mipmap.ic_launcher)
                                        .showImageForEmptyUri(R.mipmap.ic_launcher)
                                        .showImageOnLoading(R.mipmap.ic_launcher)
                                        .bitmapConfig(Bitmap.Config.RGB_565)
                                        .build()
                        )
                        .build()
        );
    }
}
