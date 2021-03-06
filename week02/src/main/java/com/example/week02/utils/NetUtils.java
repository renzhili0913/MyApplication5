package com.example.week02.utils;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
/**
 * Demo class
 *
 * @author keriezhang
 * @date 2016/10/31
 */
public class NetUtils {
    private static final int REQUEST_SUCCESS =200 ;
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

    public interface CallBack<T>{
        /**
         * onsuccess 请求成功的方法
         *
         */
        void onsuccess(T t);
    }
    @SuppressLint("StaticFieldLeak")
    public void getRequery(final String urldata, final Class clazz, final CallBack callBack){
        new AsyncTask<String,Void,Object>(){
            @Override
            protected Object doInBackground(String... strings) {
                return getRequery(strings[0],clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
               callBack.onsuccess(o);
            }
        }.execute(urldata);
    }
    public <E> E getRequery(String urldata,Class clazz){
        return (E) gson.fromJson(getRequery(urldata),clazz);
    }
    /**
     * 请求网络数据
     * */
    public String getRequery(String urldata){
        String reuslt="";
        try {
            URL url = new URL(urldata);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            if (urlConnection.getResponseCode()==REQUEST_SUCCESS){
                reuslt=getStram2String(urlConnection.getInputStream());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return reuslt;
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
