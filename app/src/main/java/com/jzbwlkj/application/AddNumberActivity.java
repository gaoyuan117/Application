package com.jzbwlkj.application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jzbwlkj.application.bean.CommonBean;
import com.jzbwlkj.application.retrofit.RetrofitClient;
import com.jzbwlkj.application.rxjava.RxUtils;
import com.jzbwlkj.application.util.TitleBar;

import io.reactivex.functions.Consumer;

public class AddNumberActivity extends AppCompatActivity {
    private EditText mEditText;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number);
        mEditText = (EditText) findViewById(R.id.et_add_number);
        new TitleBar(this).setTitle("添加成员").showRight().setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mEditText.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    return;
                }
                Log.e("gy", "name：" + name);
                addNumber();
            }
        });
    }

    private void addNumber() {
        RetrofitClient.getInstance().createApi().addNumber(name)
                .compose(RxUtils.<CommonBean>io_main())
                .subscribe(new Consumer<CommonBean>() {
                    @Override
                    public void accept(CommonBean bean) throws Exception {
                        if (bean.getCode() == 1) {
                            Toast.makeText(AddNumberActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            mEditText.setText("");
                        } else {
                            Toast.makeText(AddNumberActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
