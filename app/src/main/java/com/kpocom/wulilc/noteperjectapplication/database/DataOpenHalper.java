package com.kpocom.wulilc.noteperjectapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.kpocom.wulilc.noteperjectapplication.database.wobjectmodel.WUserInfo;

import java.sql.SQLException;

/**
 * Created by wulilc on 2018/1/30.
 */

public class DataOpenHalper extends OrmLiteSqliteOpenHelper{

    public DataOpenHalper(Context context) {
        super(context, "wulilc.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        //创建表
        try {
            TableUtils.createTable(connectionSource, WUserInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        //数据库版本变大时执行这里
//        try {
//            TableUtils.createTable(connectionSource, RecepitAddressBean.class);//覆盖安装 adb istall -r ，老用户
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
