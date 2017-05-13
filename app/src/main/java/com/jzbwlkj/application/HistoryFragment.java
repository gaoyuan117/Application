package com.jzbwlkj.application;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jzbwlkj.application.adapter.HistoryFragmentAdapter;
import com.jzbwlkj.application.base.BaseFragment;
import com.jzbwlkj.application.bean.HistoryBean;
import com.jzbwlkj.application.bean.HistoryBeanDao;
import com.jzbwlkj.application.bean.InfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/5/10.
 */

public class HistoryFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemLongClickListener {
    private RecyclerView mRecycler;
    private HistoryFragmentAdapter mAdapter;
    private List<String> mList;
    private HistoryBeanDao jsonDao;
    private List<HistoryBean> historyBeen;

    @Override
    protected View loadLayout(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_history, null);
        mRecycler = (RecyclerView) view.findViewById(R.id.lv_history);
        mList = new ArrayList<>();
        jsonDao = App.getDaoInstant().getHistoryBeanDao();
        historyBeen = jsonDao.loadAll();
        for (int i = 0; i < historyBeen.size(); i++) {
            InfoBean infoBean = new Gson().fromJson(historyBeen.get(i).getJson(), InfoBean.class);
            mList.add(infoBean.getTime());
        }
        mAdapter = new HistoryFragmentAdapter(R.layout.item_history2, mList);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mList.clear();
        historyBeen = jsonDao.loadAll();
        for (int i = 0; i < historyBeen.size(); i++) {
            InfoBean infoBean = new Gson().fromJson(historyBeen.get(i).getJson(), InfoBean.class);
            mList.add(infoBean.getTime());
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void init(View view) {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void set() {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HistoryBean historyBean = historyBeen.get(position);
        Intent intent = new Intent(getActivity(), HistoryActivity.class);
        intent.putExtra("data", historyBean.getJson());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        openDelDialog(position);
        return true;
    }

    private void openDelDialog(final int position) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setMessage("是否删除该条历史记录?")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        jsonDao.delete(historyBeen.get(position));
                        mList.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }
                }).create().show();
    }


}
