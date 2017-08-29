package com.bwie.workday1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bwie.workday1.NewsBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 木子 on 2017/8/29.
 */

public class Adapter extends BaseAdapter {

    private Context context;
    private List<NewsBean> list = new ArrayList<>();

    public Adapter(Context context, List<NewsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.item, null);
            holder.iv = view.findViewById(R.id.iv);
            holder.tv = view.findViewById(R.id.tv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv.setText(list.get(i).title);
        ImageLoader.getInstance().displayImage(list.get(i).img, holder.iv);

        return view;
    }

    class ViewHolder {
        TextView tv;
        ImageView iv;
    }
}
