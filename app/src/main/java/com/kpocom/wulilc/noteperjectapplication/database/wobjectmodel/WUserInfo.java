package com.kpocom.wulilc.noteperjectapplication.database.wobjectmodel;

import android.content.Context;
import android.view.View;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.kpocom.wulilc.noteperjectapplication.database.DataOpenHalper;
import com.kpocom.wulilc.noteperjectapplication.utils.Writelog;

import java.sql.SQLException;
import java.sql.Savepoint;

/**
 * Created by wulilc on 2018/1/30.
 */

public class WUserInfo {

    /**
     * Created by wulilc on 2018/1/30.
     */

        @DatabaseField(id = true)
        private int userid;
        @DatabaseField(columnName = "wusername")
        private String wusername;
        @DatabaseField(columnName = "wuserpassword")
        private String wuserpassword;
        @DatabaseField(columnName = "wuseremail")
        private String wuseremail;
        @DatabaseField(columnName = "wuserphonenumber")
        private String wuserphonenumber;

        public WUserInfo() {
        }

        public WUserInfo(int userid, String wusername, String wuserpassword, String wuseremail, String wuserphonenumber) {
            this.userid = userid;
            this.wusername = wusername;
            this.wuserpassword = wuserpassword;
            this.wuseremail = wuseremail;
            this.wuserphonenumber = wuserphonenumber;
        }


    public void testAddUser(Context context,WUserInfo wUserInfo) {
        boolean isLoginOk = false;
        //2.保存到sdcard或者rom中，使用sp/文件、sqlite
        DataOpenHalper takeoutOpenHelper = new DataOpenHalper(context);
        //事务属于一个数据库连接的会话中内部处理
        AndroidDatabaseConnection connection = new AndroidDatabaseConnection(takeoutOpenHelper.getWritableDatabase(), true);
        Savepoint savepoint = null;
        try {
            savepoint = connection.setSavePoint("start"); //事务的保存点
            connection.setAutoCommit(false); //事务处理需要手动提交
            Dao<WUserInfo,Integer> userDao = takeoutOpenHelper.getDao(WUserInfo.class);
//            userDao.createIfNotExists(user);  //如果之前已经有该用户，不再创建，更新用户信息
            WUserInfo oldUser = userDao.queryForId(001);
            if(oldUser!=null){
                userDao.update(wUserInfo);
//                TecentTjSdk.submitUserInfo(false); //老用户登录
                Writelog.e("login","老用户登录");
            }else{
                userDao.create(wUserInfo);
//                TecentTjSdk.submitUserInfo(false); //新用户登录
                Writelog.e("login","新用户登录");
            }
            connection.commit(savepoint);
            isLoginOk = true;
        } catch (SQLException e) {
            e.printStackTrace();

            try {
                connection.rollback(savepoint);
                Writelog.e("login","保存用户信息失败");
                isLoginOk = false;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }

}
