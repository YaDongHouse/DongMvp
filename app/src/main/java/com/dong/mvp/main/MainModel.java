package com.dong.mvp.main;



import com.dong.mvp.http.ApiService;
import com.dong.mvp.http.bean.WeatherBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @packInfo:com.dong.mvp.main
 * @author: yadong.qiu
 * Created by 邱亚东
 * Date: 2018/4/26
 * Time: 14:19
 */

public class MainModel {


    private static final String BASE_RUL = " https://www.sojson.com/";



    private Call<WeatherBean> weatherBeanCall;

    public void request(String cityId,Callback<WeatherBean> callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_RUL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        weatherBeanCall = apiService.requestWeather(cityId);
        weatherBeanCall.enqueue(callback);
    }

    public void interuptHttp(){
        if (weatherBeanCall!=null&&!weatherBeanCall.isCanceled()){
            weatherBeanCall.cancel();
        }
    }


}
