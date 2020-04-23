package com.don.basemvp.contract;

import com.don.basemvp.presenter.IPresenter;
import com.don.basemvp.view.IBaseView;

public interface MainContract {
    interface IMainView extends IBaseView
    {

    }

    interface IMainPresenter extends IPresenter
    {
        void test();
    }
}
