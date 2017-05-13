package com.jzbwlkj.application;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jzbwlkj.application.adapter.HomeAdapter;
import com.jzbwlkj.application.base.BaseFragment;
import com.jzbwlkj.application.bean.CommonBean;
import com.jzbwlkj.application.bean.NumberBean;
import com.jzbwlkj.application.retrofit.RetrofitClient;
import com.jzbwlkj.application.rxjava.RxUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * Created by admin on 2017/5/10.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    @BindView(R.id.tv_home_sure)
    TextView mEnsure;
    @BindView(R.id.lv_home)
    ListView mListView;

    private List<NumberBean.DataBean> mList;
    private HomeAdapter mAdapter;
    private View headView;
    private RelativeLayout headLayout;
    private ArrayList<NumberBean.DataBean> list;


    @Override
    protected View loadLayout(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        headView = View.inflate(getActivity(), R.layout.head_home, null);
        headLayout = (RelativeLayout) headView.findViewById(R.id.re_newfriends);
        list = new ArrayList<>();
        return view;
    }

    @Override
    protected void init(View view) {
        mList = new ArrayList<>();
        mAdapter = new HomeAdapter(getActivity(), mList);
        mListView.addHeaderView(headView);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        getList();

    }

    @Override
    protected void set() {
        headLayout.setOnClickListener(this);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
        mEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.size() == 0) {
                    return;
                }
                Intent intent = new Intent(getActivity(), MoneyActivity.class);
                intent.putExtra("data", list);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(), AddNumberActivity.class));
    }

    private void getList() {
        RetrofitClient.getInstance().createApi().getNumbers("")
                .compose(RxUtils.<NumberBean>io_main())
                .subscribe(new Consumer<NumberBean>() {
                    @Override
                    public void accept(NumberBean bean) throws Exception {
                        if (TextUtils.isEmpty(bean.getData().get(0).getName())) {
                            mList.clear();
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                        mList.clear();
                        mList.addAll(bean.getData());
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void delNumber(String id) {
        RetrofitClient.getInstance().createApi().delNumbers(id)
                .compose(RxUtils.<CommonBean>io_main())
                .subscribe(new Consumer<CommonBean>() {
                    @Override
                    public void accept(CommonBean commonBean) throws Exception {
                        if (commonBean.getCode() == 1) {
                            Toast.makeText(mActivity, "删除成功", Toast.LENGTH_SHORT).show();
                            getList();
                        }
                    }
                });
    }

    private void openDelDialog(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("是否删除该成员?")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delNumber(id);
                    }
                }).create().show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.cb_item_home);
        if (checkBox == null) {
            return;
        }
        if (checkBox.isChecked()) {
            checkBox.setChecked(false);
            list.remove(mList.get(position-1));
        } else {
            list.add(mList.get(position - 1));
            checkBox.setChecked(true);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("gy", "id:" + mList.get(position - 1).getId());
        openDelDialog(mList.get(position - 1).getId());
        return false;
    }
}
