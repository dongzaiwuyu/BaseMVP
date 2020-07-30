package com.don.basemvp.base;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.don.basemvp.event.BaseEvent;
import com.don.basemvp.presenter.IPresenter;
import com.don.basemvp.view.IBaseView;
import com.don.basemvp.widget.BaseLoadingPopupWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends IBaseView, P extends IPresenter> extends Fragment implements IBaseView {

    protected P presenter;
    protected V view;
    private Unbinder unbinder;
    protected BaseLoadingPopupWindow popupWindow;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentLayout(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        if (getLoadingPop().isShowing()) {
            getLoadingPop().dismiss();
        }
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroyView();
    }

    @Override
    public void setPresenter(IPresenter presenter) {

    }

    public void showLoading() {
        getLoadingPop().showAtLocation(this.getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

    public void dissLoading() {
        getLoadingPop().dismiss();
    }

    public PopupWindow getLoadingPop() {
        if (popupWindow == null) {
            popupWindow = new BaseLoadingPopupWindow(this.getContext());
            popupWindow.UpdateLoadingTips("加载中...");
        }
        return popupWindow;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventUpdate(BaseEvent event) {
    }

    protected abstract P createPresenter();

    protected abstract int getContentLayout();

    public abstract void init();
}
