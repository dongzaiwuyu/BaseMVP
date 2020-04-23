package com.don.basemvp.presenter;

import com.don.basemvp.contract.MainContract;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainPresenter extends BasePresenter implements MainContract.IMainPresenter {
    @Override
    public void test() {
        addToCompositeDisposable(Observable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Throwable {
                                if(isViewAttached())
                                {
                                    getView().showLoadingPop();
                                }
                            }
                        }
                ));

    }
}
