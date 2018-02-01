package com.kpocom.wulilc.noteperjectapplication.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wulilc on 2018/1/29.
 */

public class WulilcConfig {

    static SharedPreferences wShareSettings;
    static SharedPreferences.Editor wShareEditor;
    static final String PREFS_NAME = "config";
    private static final String tag = WulilcConfig.class.getSimpleName();

    @SuppressLint("CommitPrefEdits")
    public static boolean Init(Context ctx) {
        wShareSettings = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        wShareEditor = wShareSettings.edit();
        return true;
    }

    public static boolean save() {
        return wShareEditor.commit();
    }

    //设置/获取是否还需要在进行介绍界面
    public static void setIsStartApplicationStatus(boolean isStartApplicationStatus){
        wShareEditor.putBoolean("0x0001",isStartApplicationStatus);
    }
    public static boolean getIsStartApplicationStatus(){
        return wShareSettings.getBoolean("0x0001",false);
    }

    //设置/获取用户是否注册
    public static void setIsUserRegister(boolean isUserRegister){
        wShareEditor.putBoolean("0x0002",isUserRegister);
    }
    public static boolean getIsUserRegister(){
        return wShareSettings.getBoolean("0x0002",false);
    }

    //设置/获取用户是否登录
    public static void setIsUserLogin(boolean isUserLogin){
        wShareEditor.putBoolean("0x0003",isUserLogin);
    }
    public static boolean getIsUserLogin(){
        return wShareSettings.getBoolean("0x0003",false);
    }

}
