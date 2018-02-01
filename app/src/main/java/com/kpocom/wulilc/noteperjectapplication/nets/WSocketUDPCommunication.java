package com.kpocom.wulilc.noteperjectapplication.nets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.content.Context;
import android.util.Log;

import com.kpocom.wulilc.noteperjectapplication.service.WService;
import com.kpocom.wulilc.noteperjectapplication.utils.Writelog;


/**
 * Created by wulilc on 2018/2/1.
 */

public class WSocketUDPCommunication {

    private static final String TAG = WSocketUDPCommunication.class.getSimpleName();

    private WService wService;
    private OnSocketUDPCommunication onSocketUDPCommunication;

    private String mServiceIpAddress = "120.0.0.1";
    private int mPort = 8080;
    private InetAddress serverAdd;
    private DatagramSocket socket;
    private DatagramPacket mPacket;
    private DatagramPacket rPacket;
    byte[] buffer = new byte[1024 * 8];
    public static boolean isConnect = false;
    private AsyncDataThread datathread;
    private boolean isAsyncThread = false;

    public WSocketUDPCommunication(WService service,OnSocketUDPCommunication onSocketUDPCommunication) {
        this.wService = service;
        this.onSocketUDPCommunication = onSocketUDPCommunication;
        rPacket = new DatagramPacket(buffer, buffer.length);
        isAsyncThread = true;
        datathread = new AsyncDataThread();
        datathread.start();
    }

    public boolean wConnectUdpCom() {
        try {
            serverAdd = InetAddress.getByName(mServiceIpAddress);
            socket = new DatagramSocket(mPort);
            Writelog.d(TAG, " net conneting on ip=" + mServiceIpAddress + "  port=" + mPort);
            isConnect = true;
        } catch (Exception e) {
            isConnect = false;
            Writelog.e(TAG, "net connet exception ");
        }
        return isConnect;
    }

    public void wcolseUdpCom() {
        Writelog.d(TAG, "close thread and net");
        if (!isConnect) {
            return;
        }
        isConnect = false;
		isAsyncThread = false;
        if (socket != null) {
            socket.close();
        }
        socket = null;
    }

    public int wreadUdpComData() {
        int size = 0;
        try {
            socket.receive(rPacket);
            size = rPacket.getLength();
            return size;
        } catch (IOException e) {
            Writelog.e(TAG, "读取异常:" + e);
            return size;
        }
    }

    public boolean wsendUdpComData(byte[] data) {
        if (serverAdd == null || socket == null) {
            Writelog.d(TAG, "send : net not connect ");
            return false;
        }
        if (!isConnect) {
            Writelog.d(TAG, "send : net connect failed");
            return false;
        }
        try {
            mPacket = new DatagramPacket(data, data.length, serverAdd, mPort);
            socket.send(mPacket);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 数据解析线程
    int nLength;

    private class AsyncDataThread extends Thread {
        @Override
        public void run() {
            Writelog.d(TAG, "开启了读顶灯服务器数据的线程");
            while (isAsyncThread) {
                int ret = 0;
                try {
                    if (!isConnect) {
                        wConnectUdpCom();
                        sleep(5000);
                        Writelog.d(TAG, "net connecting.....................");
                        continue;
                    }
                    Writelog.d(TAG, "net connect ok");
                    nLength = wreadUdpComData();
                    ret = nLength;
                    if (ret > 0) {
                        if (onSocketUDPCommunication!=null){
                            onSocketUDPCommunication.onSocketUDPCommunication(buffer,ret);
                        }
                    }
                    sleep(100);
                } catch (Exception e) {
                    Writelog.e(TAG, "ERROR");
                    Writelog.e(TAG, "CLOSENET");
                    Writelog.e(TAG, e.getClass().toString());
                    new AsyncDataThread().start();
                }
            }
        }
    }

    public interface OnSocketUDPCommunication{
        void onSocketUDPCommunication(byte[] buffer,int ren);
    }

}
