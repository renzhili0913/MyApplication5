package com.bwie.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.bwie.bean.UserBean;
import com.bwie.database.MySqlite;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
public class UserDao {
    private  static UserDao insanner;
    private final SQLiteDatabase sb;

    public UserDao(Context context) {
        sb = new MySqlite(context).getWritableDatabase();

    }

    public static UserDao getInsanner(Context context) {
        if (insanner==null){
            insanner=new UserDao(context);
        }
        return insanner;
    }
//清除所有
    public void deleteAll() {
        sb.delete("texts",null,null);
    }
    //添加
    public void addAll(List<UserBean.DataBean> data) {
        try{
            sb.beginTransaction();
            for (UserBean.DataBean bean: data
                 ) {
                add(bean);
            }
            sb.setTransactionSuccessful();
        }finally {
            sb.endTransaction();
        }
    }

    private void add(UserBean.DataBean bean) {
        ContentValues values=new ContentValues();
        values.put("title",bean.getTitle());
        values.put("thumbnail_pic_s",bean.getThumbnail_pic_s());
        values.put("thumbnail_pic_s2",bean.getThumbnail_pic_s02());
        values.put("thumbnail_pic_s3",bean.getThumbnail_pic_s03());
        sb.insert("texts",null,values);
    }

//查询
    @SuppressLint("Recycle")
    public List<UserBean.DataBean>  select() {
        List<UserBean.DataBean> list  = new ArrayList<>();
        Cursor cursor = sb.query("texts", null, null, null, null, null, null);
        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String thumbnail_pic_s = cursor.getString(cursor.getColumnIndex("thumbnail_pic_s"));
            String thumbnail_pic_s2 = cursor.getString(cursor.getColumnIndex("thumbnail_pic_s2"));
            String thumbnail_pic_s3 = cursor.getString(cursor.getColumnIndex("thumbnail_pic_s3"));
            list.add(new UserBean.DataBean(title,thumbnail_pic_s,thumbnail_pic_s2,thumbnail_pic_s3));
        }
        return list;
    }
}
