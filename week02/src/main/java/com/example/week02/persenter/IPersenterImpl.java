package com.example.week02.persenter;

import com.example.week02.bean.NewBean;
import com.example.week02.bean.UserBean;
import com.example.week02.model.IModelImpl;
import com.example.week02.model.MyCallBack;
import com.example.week02.view.IView;

public class IPersenterImpl implements IPersenter {
    private IView iView;
    private IModelImpl iModel;
    public IPersenterImpl(IView iView) {
        this.iView = iView;
        iModel=new IModelImpl();
    }

    @Override
    public void getRequeryData(String format, String params,Class clazz) {
        iModel.getRequeryData(format, null, clazz, new MyCallBack() {
            @Override
            public void setData(Object o) {
                if ( o instanceof  UserBean) {
                    UserBean userBean = (UserBean)o;
                    if (userBean==null||!userBean.isSuccess()){
                        iView.onFail(userBean.getMsg());
                    }else{
                        iView.onSuccess(userBean);
                    }
                }else if (o instanceof NewBean){
                    NewBean newBean = (NewBean) o;
                    if (newBean==null||!newBean.isSuccess()){
                        iView.onFail(newBean.getMsg());
                    }else{
                        iView.onSuccess(newBean);
                    }
                }

            }
        });
    }
    public void onDetach(){
        if (iView!=null){
            iView=null;
        }
        if (iModel!=null){
            iModel=null;
        }
    }
}
