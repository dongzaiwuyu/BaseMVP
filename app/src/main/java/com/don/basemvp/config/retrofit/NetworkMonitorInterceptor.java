package com.don.basemvp.config.retrofit;

import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
/***
*@author Don
*@date on 2020/4/26 17:02
*@describe 网络拦截器
*/
public class NetworkMonitorInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        boolean isAvailable = NetworkUtils.isConnected();
        if (isAvailable) {
            return chain.proceed(chain.request());
        } else {
            throw new NoNetworkException();
        }
    }
}
