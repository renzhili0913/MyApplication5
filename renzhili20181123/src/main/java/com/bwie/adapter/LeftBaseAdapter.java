package com.bwie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.renzhili.R;

public class LeftBaseAdapter extends BaseAdapter {
    private String[] meuns = new String[]{"首页","找人","未登录"};
    private Context context;

    public LeftBaseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return meuns.length;
    }

    @Override
    public String getItem(int position) {
        return meuns[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.left_text_iten,parent,false);
            holder=new ViewHolder(convertView);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.binddata(getItem(position));
        return convertView;
    }
    class ViewHolder{
        TextView textView;

        public ViewHolder(View convertView) {
            textView=convertView.findViewById(R.id.left_text);
            convertView.setTag(this);
        }

        public void binddata(String item) {
            textView.setText(item);
        }
    }
}
