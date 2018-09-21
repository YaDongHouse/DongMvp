package com.dong.mvp.factory;

import com.dong.mvp.base.IMvpBasePersenter;
import com.dong.mvp.base.IMvpBaseView;

/**
 * @packInfo:com.dong.mvp.factory
 * @author: yadong.qiu
 * Created by 邱亚东
 * Date: 2018/4/26
 * Time: 10:57
 * 说明：P层的工厂接口
 */

public interface PresenterMvpFactory<V extends IMvpBaseView,P extends IMvpBasePersenter<V>> {

    /**
     * 创建Perserter的接口
     * @return 需要创建的Persenter
     */
    P createMvpPresenter();

}
