package com.example.mvp.view;

/**
 * @author Administrator
 */
public interface IView<T> {

     void success(T data);
     void fail(String str);
}
