package com.kpocom.wulilc.noteperjectapplication;

import android.app.Application;

import com.kpocom.wulilc.noteperjectapplication.common.WulilcConfig;
import com.kpocom.wulilc.noteperjectapplication.utils.Writelog;

/**
 * Created by wulilc on 2018/1/30.
 */

public class WApplication extends Application {

    public static boolean isUserLogin = false;
    public static boolean isUserRegister = false;

    @Override
    public void onCreate() {
        super.onCreate();

        //实例调试开关
        Writelog.init(true);
        //实例矢量数据库
        WulilcConfig.Init(this);

        initCommonVariables();

    }

    private void initCommonVariables() {
        isUserLogin = WulilcConfig.getIsUserLogin();
        isUserRegister = WulilcConfig.getIsUserRegister();
    }
}
