package com.don.basemvp.http;

import com.blankj.utilcode.util.Utils;
import com.don.basemvp.config.retrofit.AppRetrofitClient;

/***
 *@author Don
 *@date on 2020/4/26 17:17
 *@describe services factory
 */
public class HttpServiceFactory {

    private static AppRetrofitClient client = null;

    private static AppRetrofitClient getAppRetrofitClient() {
        if (client == null) {
            client = new AppRetrofitClient(Utils.getApp());
        }
        return client;
    }

    public static <T>T getHttpService(Class<T> Class)
    {
        return getAppRetrofitClient().createService(Class);
    }

}
