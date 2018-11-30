package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.UserBean;
import com.example.renzhili20181129.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HeadBaseAdapter extends BaseAdapter {
    private static final int COUNT_ITEM =2 ;
    private static final int EVEN_ITEM =0 ;
    private static final int ODD_ITEM = 1;
    private Context context;
    private List<UserBean.DataBean> list;

    public HeadBaseAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<UserBean.DataBean> data) {
        list.clear();
        if (data!=null){
            list.addAll(data);
        }
        notifyDataSetChanged();
    }
    public void addList(List<UserBean.DataBean> data) {
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
        return list.get(position).showImage()?EVEN_ITEM:ODD_ITEM;
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
                    getItemViewType(position)==EVEN_ITEM? R.layout.three_item:R.layout.ont_item
                    ,parent,false);
            holder=new ViewHolder(convertView);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.bindData(getItem(position));
        return convertView;

    }
    class ViewHolder{
        TextView title;
        ImageView thumbnail_pic_s,thumbnail_pic_s02,thumbnail_pic_s03;

        public ViewHolder(View convertView) {
            title=convertView.findViewById(R.id.title);
            thumbnail_pic_s=convertView.findViewById(R.id.thumbnail_pic_s);
            thumbnail_pic_s02=convertView.findViewById(R.id.thumbnail_pic_s02);
            thumbnail_pic_s03=convertView.findViewById(R.id.thumbnail_pic_s03);
            convertView.setTag(this);
        }

        public void bindData(UserBean.DataBean item) {
            title.setText(item.getTitle());
            ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s(),thumbnail_pic_s);
            if (thumbnail_pic_s02!=null||thumbnail_pic_s03!=null){
                ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s02(),thumbnail_pic_s02);
                ImageLoader.getInstance().displayImage(item.getThumbnail_pic_s03(),thumbnail_pic_s03);
            }
        }
    }
}
