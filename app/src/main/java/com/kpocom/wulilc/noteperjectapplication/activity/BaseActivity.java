package com.kpocom.wulilc.noteperjectapplication.activity;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.kpocom.wulilc.noteperjectapplication.utils.Writelog;

/**
 * Created by wulilc on 2018/1/30.
 */

public abstract class BaseActivity <T extends ViewDataBinding> extends AppCompatActivity{
    private String TAG;

    public BaseActivity(String TAG){
        this.TAG = TAG;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Writelog.i(TAG,"执行了"+TAG+".onCreate()方法");
        super.onCreate(savedInstanceState);
        onCreate();
        InitView();
        InitData();
    }

    public abstract void onCreate();
    public abstract void InitView();
    public abstract void InitData();

    @Override
    protected void onStart() {
        Writelog.i(TAG,"执行了"+TAG+".onStart()方法");
        super.onStart();
    }

    public abstract void OnRestart();
    @Override
    protected void onRestart() {
        Writelog.i(TAG,"执行了"+TAG+".onRestart()方法");
        super.onRestart();
        OnRestart();
    }

    public abstract void OnPostResume();
    @Override
    protected void onPostResume() {
        super.onPostResume();
        OnPostResume();
    }

    public abstract void OnPause();
    @Override
    protected void onPause() {
        Writelog.i(TAG,"执行了"+TAG+".onPause()方法");
        super.onPause();
        OnPause();
    }

    public abstract void OnStop();
    @Override
    protected void onStop() {
        Writelog.i(TAG,"执行了"+TAG+".onStop()方法");
        super.onStop();
        OnStop();
    }

    public abstract void OnDestroy();
    @Override
    protected void onDestroy() {
        Writelog.i(TAG,"执行了"+TAG+".onDestroy()方法");
        super.onDestroy();
        OnDestroy();
    }

}
