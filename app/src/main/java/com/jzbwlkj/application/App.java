package com.jzbwlkj.application;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jzbwlkj.application.bean.DaoMaster;
import com.jzbwlkj.application.bean.DaoSession;

/**
 * Created by admin on 2017/5/10.
 */

public class App extends Application {
    public static Context app;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        setupDatabase();
    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库shop.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "GreenDao.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }
}
