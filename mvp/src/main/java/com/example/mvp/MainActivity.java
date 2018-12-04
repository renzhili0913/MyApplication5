package com.example.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mvp.adapter.MyBaseAdapter;
import com.example.mvp.bean.UserBean;
import com.example.mvp.presanter.IPresanter;
import com.example.mvp.presanter.IPresanterImpl;
import com.example.mvp.view.IView;

import me.maxwin.view.XListView;

/**
 * Demo class
 * @author Administrator
 * @date 2018/12/04
 */
public class MainActivity extends AppCompatActivity implements IView {
    private XListView xListView;
    private IPresanterImpl iPresanter;
    private MyBaseAdapter myBaseAdapter;
    private String url="http://i.jandan.net/?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields&page=%d&custom_fields=thumb_c,views&dev=1";
    private int mpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresanter();
        initView();

    }

    private void initPresanter() {
        iPresanter=new IPresanterImpl(this);
    }

    private void initView() {
        mpage=1;
        //获取资源id
        xListView=findViewById(R.id.xlistview);
        //创建适配器
        myBaseAdapter = new MyBaseAdapter(this);
        xListView.setAdapter(myBaseAdapter);
        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                mpage=1;
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });
        initData();
    }

    private void initData() {
        iPresanter.startRequest(String.format(url,mpage),null);
    }

    @Override
    public void success(Object data) {
        UserBean  bean = (UserBean) data;
        if (mpage==1){
            myBaseAdapter.setList(bean.getPosts());
        }else {
            myBaseAdapter.addList(bean.getPosts());
        }
        mpage++;
        xListView.stopLoadMore();
        xListView.stopRefresh();
    }

    @Override
    public void fail(String str) {
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
    }
}
