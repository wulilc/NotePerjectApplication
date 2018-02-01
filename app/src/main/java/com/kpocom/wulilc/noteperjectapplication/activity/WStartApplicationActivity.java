package com.kpocom.wulilc.noteperjectapplication.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.itheima.dialogviewpager.ItHeiMaDialog;
import com.itheima.dialogviewpager.ZoomOutPageTransformer;
import com.kpocom.wulilc.noteperjectapplication.R;
import com.kpocom.wulilc.noteperjectapplication.WApplication;
import com.kpocom.wulilc.noteperjectapplication.common.WCommon;
import com.kpocom.wulilc.noteperjectapplication.common.WulilcConfig;

import dmax.dialog.SpotsDialog;

/**
 * Created by wulilc on 2018/1/30.
 */

public class WStartApplicationActivity extends Activity {

    private AlertDialog alertDialog;
    private WApplication application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        application = (WApplication) getApplication();

        alertDialog = new SpotsDialog(this,R.style.WStartApplicationCustom);
        alertDialog.show();
        WCommon.wStartTimerTaskForOnce(new WCommon.DoSomeThing() {
            @Override
            public void doSomeThing() {
                alertDialog.dismiss();
                if (!WulilcConfig.getIsStartApplicationStatus()){
                    ItHeiMaDialog.getInstance()
                            .setPageTransformer(new ZoomOutPageTransformer())
                            .setImages(new int[]{R.drawable.show1, R.drawable.show2, R.drawable.show3, R.drawable.show4,R.drawable.timg})
                            .show(getFragmentManager()).setOutsideIsTransparent(false);
                }
                WulilcConfig.setIsStartApplicationStatus(true);
                WulilcConfig.save();

//                if (application.isUserLogin){
                startActivity(new Intent(WStartApplicationActivity.this,WApplicationMainActivity.class));
//                }else {
//                    startActivity(new Intent(WStartApplicationActivity.this,WUserLoginAcitivity.class));

//                }
            }
        },3000);

    }

}
