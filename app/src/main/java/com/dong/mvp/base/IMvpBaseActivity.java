package com.dong.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dong.mvp.factory.IMvpBaseProxy;
import com.dong.mvp.factory.PresenterMvpFactory;
import com.dong.mvp.factory.PresenterMvpFactoryImpl;
import com.dong.mvp.factory.PresenterProxyInterface;

import butterknife.ButterKnife;

/**
 * @packInfo:com.dong.mvp.base
 * @author: yadong.qiu
 * Created by 邱亚东
 * Date: 2018/4/26
 * Time: 10:49
 */

public abstract class IMvpBaseActivity<V extends IMvpBaseView,P extends IMvpBasePersenter<V>>
        extends AppCompatActivity implements PresenterProxyInterface<V,P> {

    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    private static final String TAG = "Perfect-mvp";

    /**
     * 创建被代理对象，传入默认Presenter的工厂
     */
    private IMvpBaseProxy<V,P> mProxy = new IMvpBaseProxy<>(PresenterMvpFactoryImpl.<V,P>createFactory(getClass()));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建Persenter
        Log.e(TAG, "V onCreate");
        Log.e(TAG, "V onCreate mProxy = "+mProxy );
        Log.e(TAG, "V onCreate this = "+this.hashCode());
        setContentView(getContentView());
        //绑定activity
        ButterKnife.bind( this ) ;
        if (savedInstanceState!=null){
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "V onResume");
        mProxy.onResume((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "V onDestroy");
        mProxy.onDestroy();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "V onSaveInstanceState");
        outState.putBundle(PRESENTER_SAVE_KEY,mProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        Log.e(TAG, "V setPresenterFactory");
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        Log.e(TAG, "V getPresenterFactory");
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getMvpPresenter() {
        Log.e(TAG, "V getMvpPresenter");
        return mProxy.getMvpPresenter();
    }

    public abstract int getContentView();



}



