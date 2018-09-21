package com.dong.mvp.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dong.mvp.base.IMvpBasePersenter;
import com.dong.mvp.http.bean.WeatherBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @packInfo:com.dong.mvp.main
 * @author: yadong.qiu
 * Created by 邱亚东
 * Date: 2018/4/26
 * Time: 14:19
 */

public class MainPresenter extends IMvpBasePersenter<MainView> {

    private MainModel mainModel ;
    private static final String TAG = "perfect-mvp";

    public MainPresenter() {
        if (mainModel == null){
            mainModel = new MainModel();
        }
    }

    public void clickRequest(final String cityid){
        Log.e(TAG, "点击事件 P" );
        if (getmMvpView()!=null){
            getmMvpView().requestLoading();
        }
        //模拟网络延迟，可以显示出加载中
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainModel.request(cityid,new Callback<WeatherBean>() {
                    @Override
                    public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                        Log.e(TAG, "点击事件 P onResponse" );
                        if (getmMvpView()!=null){
                            getmMvpView().requestSuccess(response.body());
                            getmMvpView().resultFinish();
                        }


                    }

                    @Override
                    public void onFailure(Call<WeatherBean> call, Throwable t) {
                        Log.e(TAG, "点击事件 P onFailure" );

                        if (getmMvpView()!=null){
                            getmMvpView().requestFailure(Log.getStackTraceString(t));
                            getmMvpView().resultFinish();
                        }

                    }
                });
            }
        }, 1000);
    }

    @Override
    public void onCreatePersenter(@Nullable Bundle savedSate) {
        super.onCreatePersenter(savedSate);
        if (savedSate!=null){
            Log.e(TAG, "MainPresenter onCreatePersenter: 测试 = "+savedSate.getString("test"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "MainPresenter onSaveInstanceState: ");
        outState.putString("test","test_save");
    }

    @Override
    public void onDestroyPersenter() {
        super.onDestroyPersenter();
    }
    public void interrupHttp(){
        mainModel.interuptHttp();
    }

}
