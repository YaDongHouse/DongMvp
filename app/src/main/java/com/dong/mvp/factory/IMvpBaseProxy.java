package com.dong.mvp.factory;

import android.os.Bundle;
import android.util.Log;

import com.dong.mvp.base.IMvpBasePersenter;
import com.dong.mvp.base.IMvpBaseView;

/**
 * @packInfo:com.dong.mvp.base
 * @author: yadong.qiu
 * Created by 邱亚东
 * Date: 2018/4/26
 * Time: 11:26
 * 说明：代理接口的实现类
 */

public class IMvpBaseProxy<V extends IMvpBaseView,P extends IMvpBasePersenter<V>> implements PresenterProxyInterface<V,P> {


    /**
     * 获取onSaveInstanceState中的bundle的key
     * @param presenterFactory
     */
    private static final String PRESENTER_KEY = "presenter_key";

    private static final String TAG = "Perfect-mvp";


    private PresenterMvpFactory<V,P> mFactory;
    private P mPresenter;
    private Bundle mBundle;
    private boolean mIsAttchView;

    public IMvpBaseProxy(PresenterMvpFactory<V, P> mFactory) {
        this.mFactory = mFactory;
    }


    /**
     * 设置Presenter的工厂类，这个方法只能在创建Presenter之前调用，也就是调用getMvpPresenter()之前调用，如果Presenter已经创建
     * 则不能在修改
     * @param presenterFactory PresenterFactory 类型
     */
    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        if (mPresenter!=null){
            throw new IllegalArgumentException("这个方法只能在getMvpPresenter()之前调用,如果Presenter已经创建则不能在修改");
        }
        this.mFactory = presenterFactory;
    }


    /**
     * 获取Presenter的工厂类
     * @return
     */

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        return mFactory;
    }


    /**
     * 获取创建的Presenter
     * @return
     * 如果是之前创建的，而且是意外销毁则从Bundle中恢复
     */
    @Override
    public P getMvpPresenter() {
        Log.e(TAG, "Proxy getMvpPresenter");
        if (mFactory!=null){
            if (mPresenter==null){
                mPresenter = mFactory.createMvpPresenter();
                mPresenter.onCreatePersenter(mBundle==null?null:mBundle.getBundle(PRESENTER_KEY));
            }
        }
        Log.e(TAG, "Proxy getMvpPresenter = " + mPresenter);
        return mPresenter;
    }

    /**
     * 绑定Persenter和View
     * @param mvpView
     */
    public void onResume(V mvpView){
        getMvpPresenter();
        Log.e(TAG, "Proxy onResume");
        if (mPresenter!=null&&!mIsAttchView){
            mPresenter.onAttachMvpView(mvpView);
            mIsAttchView = true;
        }
    }


    /**
     * 销毁Presenter持有的View
     */
    public void onDetachMvpView(){
        Log.e(TAG, "Proxy onDetachMvpView = ");
        if (mPresenter!=null&&mIsAttchView){
            mPresenter.onDetachMvpView();
            mIsAttchView = false;
        }
    }


    /**
     * 销毁Presenter
     */
    public void onDestroy(){
        Log.e(TAG, "Proxy onDestroy = ");
        if (mPresenter!=null){
            onDetachMvpView();
            mPresenter.onDestroyPersenter();
            mPresenter = null;
        }
    }


    /**
     * 意外销毁的时候调用
     * @return 存入回掉给Presenter的Bundle和当前Presenter的id
     */
    public Bundle onSaveInstanceState(){
        Log.e(TAG, "Proxy onSaveInstanceState ");
        Bundle bundle = new Bundle();
        getMvpPresenter();
        if (mPresenter!=null){
            Bundle presenterBundle = new Bundle();
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENTER_KEY,presenterBundle);
        }
        return bundle;
    }


    /**
     * 意外关闭回复Presenter
     * @param saveInstanceState
     */
    public void onRestoreInstanceState(Bundle saveInstanceState){
        Log.e(TAG, "Proxy onRestoreInstanceState: ");
        Log.e(TAG, "Proxy onRestoreInstanceState  Presenter = "+mPresenter );
        mBundle = saveInstanceState;
    }






}
