package com.bwie.fragment;

import android.view.View;
import android.widget.TextView;

public class SignFragment extends BaseFragment {
    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected View getLayoutView() {
        TextView textView = new TextView(getActivity());
        textView.setText("未登录");
        return textView;
    }
}
