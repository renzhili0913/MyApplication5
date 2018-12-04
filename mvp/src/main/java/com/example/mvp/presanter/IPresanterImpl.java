package com.example.mvp.presanter;

import com.example.mvp.bean.UserBean;
import com.example.mvp.model.IModelImpl;
import com.example.mvp.model.MyCallBack;
import com.example.mvp.view.IView;

public class IPresanterImpl implements IPresanter {
    private IView iView;
    private IModelImpl iModel;

    public IPresanterImpl(IView iView) {
        this.iView = iView;
        iModel=new IModelImpl();
    }

    @Override
    public void startRequest(String url, String params) {
        iModel.requestData(url, params, new MyCallBack<UserBean>() {
            @Override
            public void setData(UserBean o) {
                if (o==null||!o.isSuccess()){
                    iView.fail(o.getStatus());
                }else{
                    iView.success(o);
                }
            }
        });
    }
}
