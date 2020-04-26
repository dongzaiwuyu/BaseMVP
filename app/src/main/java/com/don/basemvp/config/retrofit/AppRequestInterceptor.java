package com.don.basemvp.config.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/***
 *@author Don
 *@date on 2020/4/26 16:59
 *@describe app请求拦截器
 */
public class AppRequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //可追加请求头
        Response response = chain.proceed(request);
        //可验证token、数据
        return response;
    }
}
