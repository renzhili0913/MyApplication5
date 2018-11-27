package com.bwie.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bwie.adapter.LeftBaseAdapter;
import com.bwie.renzhili.MainActivity;
import com.bwie.renzhili.R;

public class LeftFragment extends BaseFragment {
    private ListView listView;
    @Override
    protected void initView(View view) {
        listView=view.findViewById(R.id.left_listview);
        //创建适配器
        listView.setAdapter(new LeftBaseAdapter(getActivity()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)getActivity()).showpage(position);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.left_item;
    }
}
