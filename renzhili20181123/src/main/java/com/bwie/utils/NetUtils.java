package com.bwie.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
public class NetUtils {
    private static  NetUtils insanner;
    private Gson gson;
    public NetUtils() {
        gson=new Gson();
    }

    public static NetUtils getInsanner() {
        if (insanner==null){
            insanner=new NetUtils();
        }
        return insanner;
    }
    //判断是否有网络
    public boolean isurl(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo!=null&&activeNetworkInfo.isAvailable();
    }
    public  interface  CallBack<T>{
        void onsuccess(T t);
    }
    @SuppressLint("StaticFieldLeak")
    public void Requery(String urldata, final Class clazz, final CallBack callBack){
        new AsyncTask<String,Void,Object>(){
            @Override
            protected Object doInBackground(String... strings) {
                return Requery(strings[0],clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
               callBack.onsuccess(o);
            }
        }.execute(urldata);
    }
    //json解析
    public <E> E Requery(String urldata,Class clazz){
        return (E) gson.fromJson(Requery(urldata),clazz);
    }
    //获取网络数据
    public String Requery(String urldata){
        String result="";
        try {
            URL url = new URL(urldata);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            if (urlConnection.getResponseCode()==200){
                result=getStram2String(urlConnection.getInputStream());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getStram2String(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        for (String tmp=br.readLine();tmp!=null;tmp=br.readLine()){
            builder.append(tmp);
        }
        return builder.toString();
    }
}
