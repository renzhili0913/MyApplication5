package com.example.week02.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.week02.MainActivity;
import com.example.week02.R;
import com.example.week02.adapter.MyBaseAdapter;
import com.example.week02.bean.NewBean;
import com.example.week02.persenter.IPersenterImpl;
import com.example.week02.view.IView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

public class DataFragment extends Fragment implements IView {
    private PullToRefreshListView listView;
    private IPersenterImpl iPersenter;
    private MyBaseAdapter myBaseAdapter;
    private int mpage;
    private String url="http://www.xieast.com/api/news/news.php";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_item,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iPersenter=new IPersenterImpl(this);
        mpage=1;
        //获取资源id
        listView=view.findViewById(R.id.listview);
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

    private void initData() {
        iPersenter.getRequeryData(url,null,NewBean.class);
    }

    @Override
    public void onSuccess(Object o) {
        NewBean newBean = (NewBean) o;
        Toast.makeText(getActivity(),newBean.getMsg(),Toast.LENGTH_SHORT).show();
        if (mpage==1){
            myBaseAdapter.setList(newBean.getData());
        }else {
            myBaseAdapter.addList(newBean.getData());
        }
        mpage++;
        listView.onRefreshComplete();
    }

    @Override
    public void onFail(String str) {
        Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iPersenter.onDetach();
    }
}
