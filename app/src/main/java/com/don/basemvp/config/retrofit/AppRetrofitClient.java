package com.don.basemvp.config.retrofit;

import android.content.Context;

import okhttp3.Interceptor;
import retrofit2.Converter;

/***
*@author Don
*@date on 2020/4/26 16:25
*@describe App级别基础Retrofit
*/
public class AppRetrofitClient extends RetrofitClient {

    public AppRetrofitClient(Context context) {
        super(context);
    }

    @Override
    public Interceptor requestInterceptor() {
        return new AppRequestInterceptor();
    }

    @Override
    public Interceptor noNetWorkInterceptor() {
        return new NetworkMonitorInterceptor();
    }

    @Override
    public Converter.Factory gsonConverterFactory() {
        //自定义GsonFactory
        return  GsonConverterFactory.create();
    }
}
