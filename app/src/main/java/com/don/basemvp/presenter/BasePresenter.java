package com.don.basemvp.presenter;

import com.don.basemvp.view.IBaseView;

import java.lang.ref.WeakReference;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

/***
 *@author Don
 *@date on 2020/4/23 11:55
 *@describe 基础Presenter
 */
public abstract class BasePresenter<V extends IBaseView> implements IPresenter<V> {
    private CompositeDisposable compositeDisposable = null;
    private WeakReference<V> weakView;

    /**
     * 界面创建，Presenter与界面取得联系
     */
    @Override
    public void attachView(V view) {
        weakView = new WeakReference<V>(view);
        compositeDisposable = new CompositeDisposable();
    }

    /**
     * 获取界面的引用
     */
    protected V getView() {
        return weakView == null ? null : weakView.get();
    }

    /**
     * 判断界面是否销毁
     */
    protected boolean isViewAttached() {
        return weakView != null && weakView.get() != null;
    }

    /**
     * 界面销毁，Presenter与界面断开联系
     */
    @Override
    public void detachView() {
        if (weakView != null) {
            weakView.clear();
            weakView = null;
        }
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }

    public void addToCompositeDisposable(Disposable disposable) {
        if (compositeDisposable != null) {
            compositeDisposable.add(disposable);
        }

    }

    public void removeToCompositeDisposable(Disposable disposable) {
        if (compositeDisposable != null) {
            compositeDisposable.remove(disposable);
        }
    }
}
