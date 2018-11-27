package com.bwie.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.bwie.adapter.MyBaseAdapter;
import com.bwie.bean.UserBean;
import com.bwie.dao.UserDao;
import com.bwie.renzhili.R;
import com.bwie.utils.NetUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;



public class HomeFragment extends BaseFragment {
    private PullToRefreshListView listView;
    private MyBaseAdapter myBaseAdapter;
    private String uri="http://www.xieast.com/api/news/news.php?page=%d";
    private int mpage;

    @Override
    protected void initView(View view) {
        mpage=1;
    //获取资源id
        listView=view.findViewById(R.id.home_list);
        //创建适配器
        myBaseAdapter = new MyBaseAdapter(getActivity());
        listView.setAdapter(myBaseAdapter);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                mpage=1;
                initData();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                initData();

            }
        });
        initData();

    }

    @Override
    protected void initData() {
        NetUtils.getInsanner().Requery(String.format(uri, mpage), UserBean.class, new NetUtils.CallBack<UserBean>() {
            @Override
            public void onsuccess(UserBean o) {
                if (!NetUtils.getInsanner().isurl(getActivity())){
                    Toast.makeText(getActivity(),"当前无网络",Toast.LENGTH_SHORT).show();
                    //从数据库查询
                    if (mpage==1){
                        myBaseAdapter.setList(UserDao.getInsanner(getActivity()).select());
                    }else{
                        myBaseAdapter.addList(UserDao.getInsanner(getActivity()).select());
                    }
                    mpage++;
                    listView.onRefreshComplete();
                    return;
                }else{
                    if (o==null||!o.isSuccess()){
                        Toast.makeText(getActivity(),"请求失败",Toast.LENGTH_SHORT).show();
                        listView.onRefreshComplete();
                        return;
                    }
                    //清除数据库所有
                    UserDao.getInsanner(getActivity()).deleteAll();
                    //添加数据库
                    UserDao.getInsanner(getActivity()).addAll(o.getData());
                    if (mpage==1){
                        myBaseAdapter.setList(o.getData());
                    }else{
                        myBaseAdapter.addList(o.getData());
                    }
                    mpage++;
                    listView.onRefreshComplete();
                }
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.home_item;
    }
}
