package com.dong.mvp.factory;

import com.dong.mvp.base.IMvpBasePersenter;
import com.dong.mvp.base.IMvpBaseView;

/**
 * @packInfo:com.dong.mvp.factory
 * @author: yadong.qiu
 * Created by 邱亚东
 * Date: 2018/4/26
 * Time: 11:07
 * 说明：P层工厂的实现类
 */

public class PresenterMvpFactoryImpl<V extends IMvpBaseView, P extends IMvpBasePersenter<V>> implements PresenterMvpFactory<V, P> {

    /**
     * 需要创建的Presenter的类型
     */
    private final Class<P> mPresenterClass;


    public static <V extends IMvpBaseView, P extends IMvpBasePersenter<V>> PresenterMvpFactoryImpl<V, P> createFactory(Class<?> viewClazz) {
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if (annotation != null) {
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new PresenterMvpFactoryImpl<V, P>(aClass);
    }

    public PresenterMvpFactoryImpl(Class<P> mPresenterClass) {
        this.mPresenterClass = mPresenterClass;
    }

    @Override
    public P createMvpPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Presenter创建失败，检查是否声明了@CreatePresenter（xxx.class)注解");
        }
    }
}
