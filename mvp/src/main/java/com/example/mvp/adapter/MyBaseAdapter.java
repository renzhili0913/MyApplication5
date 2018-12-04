package com.example.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvp.R;
import com.example.mvp.bean.UserBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
/**
 * Demo class
 *
 * @author keriezhang
 * @date 2018/12/04
 */
public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<UserBean.PostsBean> list;

    public MyBaseAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<UserBean.PostsBean> data) {
        list.clear();
        if (data!=null) {
            list.addAll(data);
        }
        notifyDataSetChanged();
    }
    public void addList(List<UserBean.PostsBean> data) {
        if (data!=null) {
            list.addAll(data);
        }
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public UserBean.PostsBean getItem(int position) {
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
            convertView=LayoutInflater.from(context).inflate(R.layout.data_item,parent,false);
            holder=new ViewHolder(convertView);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.bindData(getItem(position));
        return convertView;
    }
    class ViewHolder{
        TextView title;
        ImageView imageView;

        public ViewHolder(View convertView) {
            title=convertView.findViewById(R.id.title);
            imageView=convertView.findViewById(R.id.setThumb_c);
            convertView.setTag(this);
        }

        public void bindData(UserBean.PostsBean item) {
            title.setText(item.getTitle());
            ImageLoader.getInstance().displayImage(item.getCustom_fields().getThumb_c().get(0),imageView);
        }
    }
}
