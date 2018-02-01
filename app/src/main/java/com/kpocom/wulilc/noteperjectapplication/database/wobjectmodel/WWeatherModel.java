package com.kpocom.wulilc.noteperjectapplication.database.wobjectmodel;

import android.util.Log;

import com.kpocom.wulilc.noteperjectapplication.common.WCommon;
import com.kpocom.wulilc.noteperjectapplication.utils.Writelog;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by wulilc on 2018/2/1.
 */

public class WWeatherModel {

    private static final String TAG = WWeatherModel.class.getSimpleName();

    private int status;
    private String msg;
    private result result;
    private static class result{
        private static String city;
        private String cityid;
        private String citycode;
        private String date;
        private String week;
        private String weather;
        private String temp;
        private String temphigh;
        private String templow;
        private String img;
        private String humidity;
        private String pressure;
        private String windspeed;
        private String winddirect;
        private String windpower;
        private String updatetime;
    }

    public void show(){
        System.out.println("show()");
        Log.d(TAG,"status:"+status +" msg:"+msg+" city:"+result.city+" cityid:"+result.cityid+" citycode:"+result.citycode);
        Log.d(TAG,"date:"+result.date +" week:"+result.week+" weather:"+result.weather+" temp:"+result.temp+" temphigh:"+result.temphigh);
        Log.d(TAG,"templow:"+result.templow +" img:"+result.img+" humidity:"+result.humidity+" pressure:"+result.pressure+" windspeed:"+result.windspeed);
        Log.d(TAG,"winddirect:"+result.winddirect +" windpower:"+result.windpower+" updatetime:"+result.updatetime);
    }


    public interface WPostRequstWeatherInterface {
        @POST("query?appkey=b7dd2459d2538a4a&city=桐城")
        Call<WWeatherModel> show();
    }

}
