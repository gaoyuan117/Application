package com.jzbwlkj.application.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jzbwlkj.application.R;
import com.jzbwlkj.application.bean.NumberBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017/5/10.
 */

public class HomeAdapter extends BaseAdapter {
    private Context context;
    private List<NumberBean.DataBean> mList;

    public HomeAdapter(Context context, List<NumberBean.DataBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_home, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String name = mList.get(position).getName();
        if (!TextUtils.isEmpty(name)) {
            viewHolder.name.setText(name);
        }else {
            mList.remove(position);
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_item_home_name)
        TextView name;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
