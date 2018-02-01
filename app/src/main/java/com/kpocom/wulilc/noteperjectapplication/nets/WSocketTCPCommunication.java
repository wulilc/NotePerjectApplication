package com.kpocom.wulilc.noteperjectapplication.nets;
import com.kpocom.wulilc.noteperjectapplication.service.WService;
import com.kpocom.wulilc.noteperjectapplication.utils.Writelog;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by wulilc on 2018/1/30.
 */

public class WSocketTCPCommunication {

    private static final String TAG = WSocketTCPCommunication.class.getSimpleName();
    private final String wSocketTCPComIP = "120.0.0.1";
    private final int wScocketTCPPort = 8080;
    private boolean bRuning = false;
    private boolean wConnected = false;
    private InetSocketAddress mAddr;
    private WService wService;
    private SocketTCPComReadThread socketTCPComReadThread;
    private OnSocketTCPCommunicationDataR onSocketTCPCommunicationDataR;
    /**
     * Wulilc Socket--TCP--Communication
     * @param service 服务
     */
    public WSocketTCPCommunication(WService service,OnSocketTCPCommunicationDataR onSocketTCPCommunicationDataR){
        this.wService = service;
        this.onSocketTCPCommunicationDataR = onSocketTCPCommunicationDataR;
    }
    //实例化TCPSocket连接和读数据的线程
    public void InitandstartTCPComReadThread() {
        socketTCPComReadThread = new SocketTCPComReadThread();
        bRuning = true;
        socketTCPComReadThread.start();
    }

    //停止Socket连接和释放资源
    public void stopTCPComReadThread() {
        Writelog.i(TAG, "stop");
        bRuning = false;
    }

    /**
     * tcp连接发送的数据
     * @param str INTEGER
     */
    public void tcpComSendData(int str){
        try {
            Writelog.d(TAG, "WSocketTCPCommunication --- send:"+str);
            socketTCPComReadThread.wdos.write(str);
        } catch (Exception e) {
            Writelog.e(TAG, "write failed!");
        }
    }

    /**
     * tcp连接发送的数据
     * @param tcpComData BYTE[]
     */
    public void tcpComSendData(byte[] tcpComData){
        try {
            Writelog.d(TAG, "WSocketTCPCommunication --- send:"+tcpComData.toString());
            socketTCPComReadThread.wdos.write(tcpComData);
        } catch (Exception e) {
            Writelog.e(TAG, "write failed!");
        }
    }

    /**
     * tcp连接发送的数据
     * @param tcpComData BYTE[]
     * @param offset 起始位
     * @param dataLength 字节数组的大小
     */
    public void tcpComSendData(byte[] tcpComData,int offset,int dataLength){
        try {
            Writelog.d(TAG, "WSocketTCPCommunication --- send:"+tcpComData.toString());
            socketTCPComReadThread.wdos.write(tcpComData,offset,dataLength);
        } catch (Exception e) {
            Writelog.e(TAG, "write failed!");
        }
    }


    /**
     * 数据接收线程
     */
    public class SocketTCPComReadThread extends Thread {
        DataInputStream win = null;
        DataOutputStream wdos = null;
        Socket wsocket = null;
        int timeout = 15000;
        private boolean connect() {
            mAddr = new InetSocketAddress(wSocketTCPComIP, wScocketTCPPort);
            Writelog.i(TAG, "connect %s:%d ...", wSocketTCPComIP, wScocketTCPPort);
            boolean bl = false;
            try {
                wsocket = new Socket();
                wsocket.connect(mAddr, timeout);
                // 获取输出流
                wdos = new DataOutputStream(wsocket.getOutputStream());
                // 获取输入流
                win = new DataInputStream(wsocket.getInputStream());
                wConnected = true;
                Writelog.i(TAG, "connect ok!");
                bl = true;
            } catch (Exception ex) {
                bl = false;
                Writelog.i(TAG, "connect %s:%d failed!", wSocketTCPComIP, wScocketTCPPort);
            }
            return bl;
        }

        @Override
        public void run() {
            Writelog.i(TAG, "RecvThread Run");
            byte[] temp = new byte[1024 * 10];
            int recvLen;

            while (bRuning) {
                try {
                    if (!wConnected) {
                        if (!connect()) {
                            sleep(10000);
                            continue;
                        }
                    }
                    // 循环读取数据
                    recvLen = win.read(temp, 0, temp.length);
                    Writelog.i(TAG, "Recv:%d", recvLen);
                    if (recvLen <= 0) {
                        Writelog.i(TAG, "disconnect!");
                        wCloseSocketConnect();
                        sleep(10 * 1000);
                        continue;
                    }
                    if (onSocketTCPCommunicationDataR!=null){
                        onSocketTCPCommunicationDataR.onSocketTCPCommunication(temp,0,recvLen);
                    }
                } catch (Exception e) {
                    Writelog.e(TAG, "closesocket!");
                    wCloseSocketConnect();
                    try {
                        super.sleep(5000);
                    } catch (InterruptedException e1) {
                        Writelog.exception(TAG, e1);
                    }
                }
            }
            Writelog.i(TAG, "recv Thread exit!");
            wCloseSocketConnect();
        }

        private void wCloseSocketConnect() {
            Writelog.i(TAG, "close");
            if (!wConnected) {
                return;
            }
            wConnected = false;
            if (wdos != null) {
                try {
                    wdos.flush();
                    wdos.close();
                } catch (IOException e) {
                    Writelog.exception(TAG, e);
                }
                wdos = null;
            }
            if (win != null) {
                try {
                    win.close();
                } catch (IOException e) {
                    Writelog.exception(TAG, e);
                }
                win = null;
            }

            if (wsocket != null) {
                try {
                    wsocket.close();
                } catch (IOException e) {
                    Writelog.exception(TAG, e);
                }
                wsocket = null;
            }
        }

    }

    /**
     * Socket -- TCP -- Communication DataInterface
     */
    public interface OnSocketTCPCommunicationDataR{
        /**
         * 数据返回的接口
         * @param buffer
         * @param offset
         * @param recvLen
         */
        void onSocketTCPCommunication(byte[] buffer,int offset,int recvLen);
    }
}
