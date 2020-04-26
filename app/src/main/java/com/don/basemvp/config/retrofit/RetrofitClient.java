package com.don.basemvp.config.retrofit;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.don.basemvp.config.HttpConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/***
*@author Don
*@date on 2020/4/26 16:09
*@describe 基础Retrofit
*/
public abstract class RetrofitClient {
    private Context context;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private File httpCacheDir;
    private Cache cache;

    public RetrofitClient(Context context)
    {
        this.context=context;
        init();
    }

    private void init()
    {
        if (httpCacheDir == null) {
            //TODO
            httpCacheDir = new File(context.getCacheDir(), "app_cache");
        }
        if (cache == null) {
            cache = new Cache(httpCacheDir, 10 * 1024 * 1024);
        }

        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .addInterceptor(noNetWorkInterceptor())
                    .addInterceptor(requestInterceptor())
                    .addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            LogUtils.d(message);
                        }
                    }).setLevel(HttpLoggingInterceptor.Level.BODY))
                    .cache(cache)
                    .connectTimeout(HttpConfig.BaseHttpTimeOut, TimeUnit.SECONDS)
                    .readTimeout(HttpConfig.BaseHttpTimeOut, TimeUnit.SECONDS)
                    .writeTimeout(HttpConfig.BaseHttpTimeOut, TimeUnit.SECONDS)
                    .build();
        }

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(gsonConverterFactory())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(HttpConfig.BaseUrl)
                    .build();
        }
    }

    public <T> T createService(Class<T> t) {
        if (retrofit == null) {
            throw new RuntimeException("Api service is null");
        }
        return retrofit.create(t);
    }

    public void reset() {
        retrofit = null;
        okHttpClient = null;
        httpCacheDir = null;
        cache = null;
    }

    public abstract Interceptor requestInterceptor();

    public abstract Interceptor noNetWorkInterceptor();

    public abstract Converter.Factory gsonConverterFactory();
}
