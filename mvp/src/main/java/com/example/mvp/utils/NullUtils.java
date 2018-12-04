package com.example.mvp.utils;

public class NullUtils {
    private static NullUtils insanner;

    public NullUtils() {
    }

    public static NullUtils getInsanner() {
        if (insanner==null){
            insanner=new NullUtils();
        }
        return insanner;
    }

    //字符串非空判断
    public boolean isnull(String names, String passwords) {
        return !names.isEmpty()&&!passwords.isEmpty();
    }
}
