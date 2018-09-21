package com.dong.mvp.http;

import com.dong.mvp.http.bean.WeatherBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @packInfo:com.dong.mvp.http
 * @author: yadong.qiu
 * Created by 邱亚东
 * Date: 2018/4/26
 * Time: 14:27
 */

public interface ApiService {


    /**
     * 请求天气接口
     * @param cityName 城市
     * @return Call
     */
    @GET("open/api/weather/json.shtml")
    Call<WeatherBean> requestWeather(@Query("city") String cityName);

}
