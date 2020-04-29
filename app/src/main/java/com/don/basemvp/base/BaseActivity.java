package com.don.basemvp.base;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.don.basemvp.event.BaseEvent;
import com.don.basemvp.presenter.IPresenter;
import com.don.basemvp.view.IBaseView;
import com.don.basemvp.widget.BaseLoadingPopupWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/***
 *@author Don
 *@date on 2020/4/23 13:50
 *@describe 基础Activity
 */
public abstract class BaseActivity<V extends IBaseView, P extends IPresenter> extends AppCompatActivity implements IBaseView {

    protected P presenter;
    protected V view;
    protected BaseLoadingPopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (getLoadingPop().isShowing()) {
            getLoadingPop().dismiss();
        }
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void setPresenter(IPresenter presenter) {
    }

    public void showLoading() {
        getLoadingPop().showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

    public void dissLoading() {
        getLoadingPop().dismiss();
    }

    public PopupWindow getLoadingPop() {
        if (popupWindow == null) {
            popupWindow = new BaseLoadingPopupWindow(this);
            popupWindow.UpdateLoadingTips("加载中...");
        }
        return popupWindow;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventUpdate(BaseEvent event)
    {
    }

    protected abstract P createPresenter();
}
