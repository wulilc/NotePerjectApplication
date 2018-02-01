package com.kpocom.wulilc.noteperjectapplication.common;

import com.kpocom.wulilc.noteperjectapplication.utils.Writelog;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wulilc on 2018/1/30.
 */

public class WCommon {

    /**
     * 避免一个按钮多次点击
     */
    private static final int FAST_DOUBLE_CLICK_INTERVAL=500;
    public static long lastTime;
    public synchronized  static boolean isOnclick() {
        try {
            return  System.currentTimeMillis()-lastTime>FAST_DOUBLE_CLICK_INTERVAL;
        } catch (Exception e){
            Writelog.exception("DZ",e);
            return false;
        }finally {
            lastTime = System.currentTimeMillis();
        }
    }


    /**
     * 开启一个定时器
     */
    public static Timer wStartTimerTaskForOnce(final DoSomeThing wdoSomeThing,long longTime){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (wdoSomeThing!=null){
                    wdoSomeThing.doSomeThing();
                }
            }
        },longTime);
        return timer;
    }

    public interface DoSomeThing{
        void doSomeThing();
    }

}
