package com.bwie.renzhili;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.bwie.adapter.HomePagerAdapter;
import com.bwie.fragment.LeftFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取资源id
        viewPager=findViewById(R.id.viewpager);
        tabLayout=findViewById(R.id.tablayout);
        drawerLayout=findViewById(R.id.drawerlayout);
        //创建适配器
        viewPager.setAdapter(new HomePagerAdapter(getSupportFragmentManager()));
        //绑定tablayout
        tabLayout.setupWithViewPager(viewPager);
        //侧拉点击按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.opan,R.string.colse);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
        //创建侧拉页
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.framelayout,new LeftFragment())
                    .commit();
        }
        //drawerLayout监听
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showpage(int position){
        viewPager.setCurrentItem(position);
        drawerLayout.closeDrawer(Gravity.START);
    }
}
