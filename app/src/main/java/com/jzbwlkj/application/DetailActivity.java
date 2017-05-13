package com.jzbwlkj.application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jzbwlkj.application.adapter.HistoryAdapter;
import com.jzbwlkj.application.adapter.HomeAdapter;
import com.jzbwlkj.application.bean.HistoryBean;
import com.jzbwlkj.application.bean.HistoryBeanDao;
import com.jzbwlkj.application.bean.InfoBean;
import com.jzbwlkj.application.bean.NumberBean;
import com.jzbwlkj.application.util.TitleBar;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ArrayList<NumberBean.DataBean> mList;
    private String money;

    private RecyclerView mRecyclerView;
    private TextView tvNum;
    private TextView tvDisMoney;
    private HistoryAdapter mAdapter;
    private HistoryBeanDao jsonDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        jsonDao = App.getDaoInstant().getHistoryBeanDao();
        new TitleBar(this).setTitle("详情");
        mList = (ArrayList<NumberBean.DataBean>) getIntent().getSerializableExtra("data");
        money = getIntent().getStringExtra("money");
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
        mAdapter = new HistoryAdapter(R.layout.item_history, mList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);

        tvNum.setText("总人数(" + mList.size() + "人)");
        tvAllMoney.setText("￥ " + money + "元");
        tvDisMoney.setText("￥ " + String.format("%.2f", (Float.valueOf(money) / mList.size())) + "元");

        InfoBean infoBean = new InfoBean();
        infoBean.setMoney(money);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            list.add(mList.get(i).getName());
        }
        infoBean.setList(list);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = format.format(new Date());
        infoBean.setTime(date);

        saveJson(infoBean);
    }


    private void saveJson(InfoBean infoBean) {
        String s = new Gson().toJson(infoBean);
        Log.e("gy", s);
        HistoryBean jsonBean = new HistoryBean();
        jsonBean.setJson(s);
        jsonDao.insert(jsonBean);
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

}
