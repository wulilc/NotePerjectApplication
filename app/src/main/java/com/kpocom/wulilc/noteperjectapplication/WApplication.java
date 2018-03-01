package com.kpocom.wulilc.noteperjectapplication;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.kpocom.wulilc.noteperjectapplication.common.WulilcConfig;
import com.kpocom.wulilc.noteperjectapplication.utils.Writelog;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by wulilc on 2018/1/30.
 */

public class WApplication extends Application {


    public static final String SDCARD= Environment.getExternalStorageDirectory()+"";
    public static String CACHE_FILE;
    public static String CACHE_IMAGES;
    public static String CACHE_DOWNLOADS;
    public static String CACHE_DATASETS;
    public static String CACHE_ASSETS;
    public static String CACHE_SCREENCAPS;
    public static String CACHE_SCREENSHOTS;
    public static String CACHE_VIDEO;
    private static Context appContext;

    public static boolean isUserLogin = false;
    public static boolean isUserRegister = false;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("TAG","WApplication.onCreate()");

        intDIR();

        //实例调试开关
        Writelog.init(true);
        //实例矢量数据库
        WulilcConfig.Init(this);

        initCommonVariables();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

    }

    private void initCommonVariables() {
        isUserLogin = WulilcConfig.getIsUserLogin();
        isUserRegister = WulilcConfig.getIsUserRegister();
    }

    private void intDIR(){
        CACHE_FILE=getExternalFilesDir(null)+"/";
        CACHE_IMAGES=getExternalFilesDir("images")+"/";
        CACHE_DOWNLOADS=getExternalFilesDir("downloads")+"/";
        CACHE_SCREENCAPS=getExternalFilesDir("ScreenCaps")+"/";
        CACHE_SCREENSHOTS=getExternalFilesDir("ScreenShots")+"/";
        CACHE_DATASETS=getExternalFilesDir("DataSets")+"/";
        CACHE_ASSETS=getExternalFilesDir("AssetsBundles")+"/";
        CACHE_VIDEO=getExternalFilesDir("video")+"/";
    }

}
