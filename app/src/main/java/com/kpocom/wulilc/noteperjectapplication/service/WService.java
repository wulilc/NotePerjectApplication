package com.kpocom.wulilc.noteperjectapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.kpocom.wulilc.noteperjectapplication.nets.WSocketTCPCommunication;

/**
 * Created by wulilc on 2018/1/30.
 */

public class WService extends Service implements WSocketTCPCommunication.OnSocketTCPCommunicationDataR{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSocketTCPCommunication(byte[] buffer, int offset, int recvLen) {

    }
}
