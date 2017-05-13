package com.jzbwlkj.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.jzbwlkj.application.bean.NumberBean;
import com.jzbwlkj.application.util.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class MoneyActivity extends AppCompatActivity {
    private EditText mEditText;
    private String money;
    private ArrayList<NumberBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number);

        mList = (ArrayList<NumberBean.DataBean>) getIntent().getSerializableExtra("data");

        Log.e("gy", "size :" + mList.size());

        mEditText = (EditText) findViewById(R.id.et_add_number);
        new TitleBar(this).setTitle("收入").showRight().setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                money = mEditText.getText().toString();
                if (TextUtils.isEmpty(money)) {
                    return;
                }
                Intent intent = new Intent(MoneyActivity.this, DetailActivity.class);
                intent.putExtra("data", mList);
                intent.putExtra("money", money);
                startActivity(intent);
            }
        });
    }
}
