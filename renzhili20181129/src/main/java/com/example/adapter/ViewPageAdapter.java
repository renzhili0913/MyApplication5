package com.example.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.fragment.HeadlinesFragment;
import com.example.fragment.MyFragment;

public class ViewPageAdapter extends FragmentPagerAdapter {
    private String[] mueun = new String[]{"头条","推荐","关注","娱乐","体育","财经","音乐","军事","综艺"};
    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new HeadlinesFragment();
            default:
              return  new MyFragment();
        }

    }

    @Override
    public int getCount() {
        return mueun.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mueun[position];
    }
}
