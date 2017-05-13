package com.jzbwlkj.application.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.application.R;
import com.jzbwlkj.application.bean.NumberBean;

import java.util.List;

/**
 * Created by admin on 2017/5/11.
 */

public class HistoryAdapter extends BaseQuickAdapter<NumberBean.DataBean, BaseViewHolder> {

    public HistoryAdapter(@LayoutRes int layoutResId, @Nullable List<NumberBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NumberBean.DataBean item) {
        helper.setText(R.id.tv_item_history_name, item.getName());
    }
}
