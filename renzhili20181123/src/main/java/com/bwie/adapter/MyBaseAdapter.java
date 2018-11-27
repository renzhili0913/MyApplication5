package com.bwie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.bean.UserBean;
import com.bwie.renzhili.R;
import com.nostra13.universalimageloader.core.ImageLoader;


import java.util.ArrayList;
import java.util.List;

public class MyBaseAdapter extends BaseAdapter {
    private static final int COUNT_ITEM = 2;
    private static final int ONE_ITEM = 0;
    private static final int THREE_ITEM = 1;
    private List<UserBean.DataBean> list;
    private Context context;

    public MyBaseAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    //下拉刷新
    public void setList(List<UserBean.DataBean> data){
        list.clear();
        if (data!=null){
            list.addAll(data);
        }
        notifyDataSetChanged();
    }
    //上拉加载
    public void addList(List<UserBean.DataBean> data){
        if (data!=null){
            list.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getViewTypeCount() {
        return COUNT_ITEM;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2==0?ONE_ITEM:THREE_ITEM;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public UserBean.DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(
                    getItemViewType(position)==ONE_ITEM? R.layout.ont_image:R.layout.three_item
                    ,parent,false);
            holder=new ViewHolder(convertView);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.bindData(getItem(position));
        return convertView;
    }
    class ViewHolder{
        TextView textView;
        ImageView image,image1,image2;

        public ViewHolder(View convertView) {
            textView=convertView.findViewById(R.id.title);
            image=convertView.findViewById(R.id.image);
            image1=convertView.findViewById(R.id.image1);
            image2=convertView.findViewById(R.id.image2);
            convertView.setTag(this);
        }

        public void bindData(UserBean.DataBean item) {
            textView.setText(item.getTitle());
            ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s(),image);
            if (image1!=null&&image2!=null){
                ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s02(),image1);
                ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s03(),image2);
            }
        }
    }
}
