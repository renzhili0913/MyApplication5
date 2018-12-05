package com.example.week02.model;

import com.example.week02.utils.NetUtils;

public class IModelImpl implements IModel {
    private  MyCallBack myCallBack;
    @Override
    public void getRequeryData(String url, String params, Class clazz, final MyCallBack myCallBack) {
        this.myCallBack=myCallBack;
        NetUtils.getInsanner().getRequery(url, clazz, new NetUtils.CallBack() {
            @Override
            public void onsuccess(Object o) {
                myCallBack.setData(o);
            }
        });

    }
}
