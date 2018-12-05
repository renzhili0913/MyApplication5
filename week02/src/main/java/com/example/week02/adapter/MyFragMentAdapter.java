package com.example.week02.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.week02.fragment.CardFragment;
import com.example.week02.fragment.DataFragment;

public class MyFragMentAdapter extends FragmentPagerAdapter {
    private String[] meuns = new String[]{"我的数据","我的名片"};
    public MyFragMentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return  new DataFragment();
            case 1:
                return new CardFragment();
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
