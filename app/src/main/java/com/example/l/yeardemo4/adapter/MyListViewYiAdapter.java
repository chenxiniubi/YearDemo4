package com.example.l.yeardemo4.adapter;

import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.l.yeardemo4.R;
import com.example.l.yeardemo4.bean.Meau;

import java.util.ArrayList;
import java.util.List;

public class MyListViewYiAdapter extends BaseAdapter{
    private Context context;
    private List<Meau.DataBean> list = new ArrayList<>();

    public MyListViewYiAdapter(Context context, List<Meau.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = View.inflate(context, R.layout.listview_yi_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.text_name = convertView.findViewById(R.id.text_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.text_name.setText(list.get(position).getSellerName());
        return convertView;
    }

    class ViewHolder{
        TextView text_name;
    }
}
