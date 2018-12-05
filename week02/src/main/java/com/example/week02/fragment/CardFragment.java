package com.example.week02.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.week02.LoginActivity;
import com.example.week02.MainActivity;
import com.example.week02.R;
import com.example.week02.model.MyCallBack;
import com.google.zxing.qrcode.encoder.QRCode;

import java.lang.ref.WeakReference;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

import static android.content.Context.MODE_PRIVATE;

public class CardFragment extends Fragment {
    private ImageView imageView;
    private Button sign_out;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_item,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferences=getActivity().getSharedPreferences("user",MODE_PRIVATE);
        editor=preferences.edit();
        //获取资源id
        imageView=view.findViewById(R.id.image);
        sign_out=view.findViewById(R.id.sign_out);
        //获取值生成二维码
        ((LoginActivity)getActivity()).setonMyCallBack(new MyCallBack() {
            @Override
            public void setData(Object o) {
                String s= (String) o;
                QRTask qrTask = new QRTask(getActivity(),imageView);
                qrTask.execute(s);
                //Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();
            }
        });
       //点击退出登录
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                editor.putBoolean("automatic",false);
                editor.commit();
                getActivity().finish();
            }
        });
    }
    //创建静态内部类处理文本框获取到的字符串，并生成二维码赋值与imageview展示
    static class QRTask extends AsyncTask<String,Void,Bitmap> {
        //软引用类型
        private WeakReference<Context> mContent;
        private WeakReference<ImageView> mImageView;

        public QRTask(Context content,ImageView imageView ) {
            mContent=new WeakReference<>(content);
            mImageView=new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String str = strings[0];
            if (TextUtils.isEmpty(str)){
                return null;
            }
            //软应用只能通过get()获取到相应的方法，返回要生成的二维码的尺寸大小
            int size = mContent.get().getResources().getDimensionPixelOffset(R.dimen.qr_code_size);
            //返回生成的二维码图片
            return QRCodeEncoder.syncEncodeQRCode(str,size);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //生成的二维码不为空就直接赋值与imageview上
            if (bitmap!=null){
                mImageView.get().setImageBitmap(bitmap);
            }else{
                Toast.makeText(mContent.get(),"生成失败",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
