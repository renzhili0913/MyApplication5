package com.bwie.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bwie.fragment.HomeFragment;
import com.bwie.fragment.LookFragment;
import com.bwie.fragment.SignFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {
    private String[] meuns = new String[]{"首页","找人","未登录"};
    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return  new HomeFragment();
            case 1:
                return  new LookFragment();
            case 2:
                return  new SignFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return meuns.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return meuns[position];
    }
}
