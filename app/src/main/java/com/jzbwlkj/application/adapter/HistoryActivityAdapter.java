package com.jzbwlkj.application.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.application.R;

import java.util.List;

/**
 * Created by admin on 2017/5/12.
 */

public class HistoryActivityAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    public HistoryActivityAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item_history_name, item);
    }
}
