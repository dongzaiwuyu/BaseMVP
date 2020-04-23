package com.don.basemvp.view;

import com.don.basemvp.presenter.IPresenter;

/***
 *@author Don
 *@date on 2020/4/23 11:54
 *@describe 基础View
 */
public interface IBaseView<T extends IPresenter> {
    void setPresenter(T presenter);

    void showLoadingPop();

    void dimissLoadingPop();
}
