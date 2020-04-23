package com.don.basemvp;

import android.os.Bundle;

import com.don.basemvp.base.BaseActivity;
import com.don.basemvp.contract.MainContract;
import com.don.basemvp.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainContract.IMainView, MainPresenter> implements MainContract.IMainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus)
        {
            presenter.test();
        }
    }

    @Override
    public void showLoadingPop() {
        showLoading();
    }

    @Override
    public void dimissLoadingPop() {
        dissLoading();
    }
}
