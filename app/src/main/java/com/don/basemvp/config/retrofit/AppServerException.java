package com.don.basemvp.config.retrofit;
/***
 *@author Don
 *@date on 2020/4/26 16:25
 *@describe App自定义网络错误
 */
public class AppServerException  extends RuntimeException {

    public String errorCode;

    public AppServerException(String message) {
        super(message);
    }

    public AppServerException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}