package com.example.week02.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.week02.R;
import com.example.week02.bean.NewBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MyBaseAdapter extends BaseAdapter {
    private static final int COUNT_ITEM =2 ;
    private static final int THREE = 0;
    private static final int ONE = 1;
    private List<NewBean.DataBean> list;
    private Context context;

    public MyBaseAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<NewBean.DataBean> data) {
        list.clear();
        if (data!=null){
            list.addAll(data);
        }
        notifyDataSetChanged();
    }
    public void addList(List<NewBean.DataBean> data) {
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
        return list.get(position).isImage()?THREE:ONE;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public NewBean.DataBean getItem(int position) {
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
                    getItemViewType(position)==THREE?R.layout.three_item:R.layout.one_item,
                    parent,false);
            holder=new ViewHolder(convertView);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.bindData(getItem(position));
        return convertView;
    }
    class  ViewHolder{
        TextView title;
        ImageView thumbnail_pic_s,thumbnail_pic_s02,thumbnail_pic_s03;

        public ViewHolder(View convertView) {
            title=convertView.findViewById(R.id.title);
            thumbnail_pic_s=convertView.findViewById(R.id.thumbnail_pic_s);
            thumbnail_pic_s02=convertView.findViewById(R.id.thumbnail_pic_s02);
            thumbnail_pic_s03=convertView.findViewById(R.id.thumbnail_pic_s03);
            convertView.setTag(this);
        }

        public void bindData(NewBean.DataBean item) {
            title.setText(item.getTitle());
            ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s(),thumbnail_pic_s);
            if (thumbnail_pic_s02!=null&&thumbnail_pic_s03!=null){
                ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s02(),thumbnail_pic_s02);
                ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s03(),thumbnail_pic_s03);
            }
        }
    }
}
