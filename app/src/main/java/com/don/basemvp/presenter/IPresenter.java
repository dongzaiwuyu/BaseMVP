package com.don.basemvp.presenter;

import com.don.basemvp.view.IBaseView;
/***
*@author Don
*@date on 2020/4/23 11:54
*@describe 基础Presente
*/
public interface IPresenter<V extends IBaseView> {
    void attachView(V view);
    void detachView();
}
