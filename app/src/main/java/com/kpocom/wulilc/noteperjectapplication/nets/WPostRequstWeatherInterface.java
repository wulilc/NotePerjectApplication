package com.kpocom.wulilc.noteperjectapplication.nets;

import com.kpocom.wulilc.noteperjectapplication.database.wobjectmodel.WWeatherModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wulilc on 2018/2/1.
 */

public class WPostRequstWeatherInterface {

    //步骤1:创建Retrofit对象
    public void wpostRequstWeather(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.jisuapi.com/weather/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 步骤2:创建 网络请求接口 的实例
        WWeatherModel.WPostRequstWeatherInterface request = retrofit.create(WWeatherModel.WPostRequstWeatherInterface.class);
        //对 发送请求 进行封装
        Call<WWeatherModel> call = request.show();

        //步骤3:发送网络请求(异步)
        call.enqueue(new Callback<WWeatherModel>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<WWeatherModel> call, Response<WWeatherModel> response) {
                // 步骤7：处理返回的数据结果
                if (response.body()!=null){
                    response.body().show();
                }
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<WWeatherModel> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });

    }

}
