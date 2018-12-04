package com.example.mvp.model;

import com.example.mvp.bean.UserBean;
import com.example.mvp.utils.NetUtils;

public class IModelImpl implements IModel {
    private MyCallBack myCallBack;

    @Override
    public void requestData(String url, String params, final MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
        NetUtils.getInsanner().getRequery(url, UserBean.class, new NetUtils.CallBack() {
            @Override
            public void onsuccess(Object o) {
                myCallBack.setData(o);
            }
        });
    }
}
