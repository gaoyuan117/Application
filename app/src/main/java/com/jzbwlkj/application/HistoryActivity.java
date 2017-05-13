package com.jzbwlkj.application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jzbwlkj.application.adapter.HistoryActivityAdapter;
import com.jzbwlkj.application.adapter.HistoryAdapter;
import com.jzbwlkj.application.bean.HistoryBean;
import com.jzbwlkj.application.bean.HistoryBeanDao;
import com.jzbwlkj.application.bean.InfoBean;
import com.jzbwlkj.application.bean.NumberBean;
import com.jzbwlkj.application.util.TitleBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<String> mList;
    private String money;

    private RecyclerView mRecyclerView;
    private TextView tvNum;
    private TextView tvDisMoney;
    private HistoryActivityAdapter mAdapter;
    private HistoryBeanDao historyBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        new TitleBar(this).setTitle("详情");
        historyBeanDao = App.getDaoInstant().getHistoryBeanDao();
        mList = new ArrayList<>();
        String json = getIntent().getStringExtra("data");
        InfoBean infoBean = new Gson().fromJson(json, InfoBean.class);
        mList.addAll(infoBean.getList());
        money = infoBean.money;
        init();

    }


    private void init() {
        if (mList == null || mList.size() == 0) {
            return;
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_result);
        tvNum = (TextView) findViewById(R.id.tv_result_num);
        TextView tvAllMoney = (TextView) findViewById(R.id.tv_result_money);
        tvDisMoney = (TextView) findViewById(R.id.tv_result_dismoney);
        mAdapter = new HistoryActivityAdapter(R.layout.item_history, mList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);

        tvNum.setText("总人数(" + mList.size() + "人)");
        tvAllMoney.setText("￥ " + money + "元");
        tvDisMoney.setText("￥ " + String.format("%.2f", (Float.valueOf(money) / mList.size())) + "元");

    }

}
