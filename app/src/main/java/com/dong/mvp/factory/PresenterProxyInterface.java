package com.dong.mvp.factory;

import com.dong.mvp.base.IMvpBasePersenter;
import com.dong.mvp.base.IMvpBaseView;

/**
 * @packInfo:com.dong.mvp.factory
 * @author: yadong.qiu
 * Created by 邱亚东
 * Date: 2018/4/26
 * Time: 11:19
 * 说明：代理的接口
 * 主要任务：
 * 1设置工厂
 * 2获取P层的工厂
 * 3获取P层
 */

public interface PresenterProxyInterface<V extends IMvpBaseView,P extends IMvpBasePersenter<V>> {

    /**
     * 设置创建Presenter的工厂
     * @param presenterFactory
     */
    void setPresenterFactory(PresenterMvpFactory<V,P> presenterFactory);


    /**
     * 获取presenter的工厂类
     * @return
     */
    PresenterMvpFactory<V,P> getPresenterFactory();


    /**
     * 获取创建的Presenter
     * @return
     */
    P getMvpPresenter();


}
