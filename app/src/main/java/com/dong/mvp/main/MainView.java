package com.dong.mvp.main;

import com.dong.mvp.base.IMvpBaseView;
import com.dong.mvp.http.bean.WeatherBean;

/**
 * @packInfo:com.dong.mvp.main
 * @author: yadong.qiu
 * Created by 邱亚东
 * Date: 2018/4/26
 * Time: 14:19
 */

public interface MainView extends IMvpBaseView {
    void requestLoading();
    void requestSuccess(WeatherBean result);
    void requestFailure(String result);
    void resultFinish();
}
