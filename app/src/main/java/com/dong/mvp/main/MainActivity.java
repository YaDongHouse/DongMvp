package com.dong.mvp.main;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.dong.mvp.R;
import com.dong.mvp.base.IMvpBaseActivity;
import com.dong.mvp.factory.CreatePresenter;
import com.dong.mvp.http.bean.WeatherBean;

import butterknife.BindView;
import butterknife.OnClick;


@CreatePresenter(MainPresenter.class)
public class MainActivity extends IMvpBaseActivity<MainView,MainPresenter> implements MainView {

    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.button)
    Button mButton;

    private ProgressDialog dialog;

    @OnClick(R.id.button)
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.button:
                Log.e("00000", "onClick: 点击" );
                getMvpPresenter().clickRequest("上海");
                break;
                default:
        }
    }
    @Override
    public int getContentView() {
        return R.layout.content_main;
    }

    @Override
    public void requestLoading() {
        dialog = ProgressDialog.show(this, "提示", "正在登陆",
                false, true, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        getMvpPresenter().interrupHttp();
                    }
                });
    }

    @Override
    public void requestSuccess(WeatherBean result) {
        mTextView.setText(result.toString());
    }

    @Override
    public void requestFailure(String result) {
        mTextView.setText(result);
    }

    @Override
    public void resultFinish() {
        dialog.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMvpPresenter().interrupHttp();
    }
}
