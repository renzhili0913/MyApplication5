package com.example.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adapter.HeadBaseAdapter;
import com.example.bean.UserBean;
import com.example.renzhili20181129.R;
import com.example.utils.NetUtils;

import me.maxwin.view.XListView;

public class HeadlinesFragment extends Fragment {
    private XListView xListView;
    private int mpage;
    private HeadBaseAdapter headBaseAdapter;
    private  String url="http://www.xieast.com/api/news/news.php?page=%d";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.head_item,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mpage=1;
        xListView=view.findViewById(R.id.listview);
        //创建适配
        headBaseAdapter = new HeadBaseAdapter(getActivity());
        xListView.setAdapter(headBaseAdapter);
        xListView.setPullLoadEnable(true);
        xListView.setPullRefreshEnable(true);
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
        NetUtils.getInsanner().getRequery(String.format(url, mpage), UserBean.class, new NetUtils.CallBack<UserBean>() {
            @Override
            public void onsuccess(UserBean o) {
                if (o==null||!o.isSuccess()){
                    Toast.makeText(getActivity(),"请求失败",Toast.LENGTH_SHORT).show();
                    xListView.stopRefresh();
                    xListView.stopLoadMore();
                    return;
                }
                if (mpage==1){
                    headBaseAdapter.setList(o.getData());
                }else{
                    headBaseAdapter.addList(o.getData());
                }
                mpage++;
                xListView.stopRefresh();
                xListView.stopLoadMore();
            }
        });
    }
}
