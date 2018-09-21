package com.dong.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @packInfo:com.dong.mvp.base
 * @author: yadong.qiu
 * Created by 邱亚东
 * Date: 2018/4/26
 * Time: 10:45
 */

public abstract class  IMvpBasePersenter<V extends IMvpBaseView> {

    private V mMvpView;

    private static final String TAG = "Perfect-mvp";


    public void onCreatePersenter(@Nullable Bundle savedSate){
        Log.e(TAG, "P onCreatePersenter = ");
    }


    /**
     * 绑定V层
     * @param mMvpView
     */
    public void onAttachMvpView(V mMvpView){
        this.mMvpView = mMvpView;
        Log.e(TAG, "P onResume ");
    }


    /**
     * 解除绑定V层
     */
    public void onDetachMvpView(){
        mMvpView = null;
        Log.e(TAG, "P onDestroy = ");
    }


    /**
     * Presenter被销毁时调用
     */
    public void onDestroyPersenter(){
        Log.e(TAG, "P onDestroy =  ");
    }


    public void onSaveInstanceState(Bundle outState){
        Log.e(TAG, "P onSaveInstanceState = ");
    }


    /**
     * 获取V层
     * @return
     */
    public V getmMvpView(){
        return mMvpView;
    }


}
