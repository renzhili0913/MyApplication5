package com.example.week02.view;

public interface IView <T>{
    void onSuccess(T t);
    void onFail(String str);
}
