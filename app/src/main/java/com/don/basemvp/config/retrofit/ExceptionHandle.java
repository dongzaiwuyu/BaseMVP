package com.don.basemvp.config.retrofit;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/***
 *@author Don
 *@date on 2020/4/26 16:25
 *@describe 错误码统一处理
 */
public class ExceptionHandle {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ResponeThrowable handleException(Throwable e) {
        ResponeThrowable ex;

            e.printStackTrace();

        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponeThrowable(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    //ex.code = httpException.code();
                    ex.message = "网络不给力，请检查网络连接";
                    break;
            }
            return ex;
        } else if (e instanceof NoNetworkException) {
            ex = new ResponeThrowable(e, ERROR.HTTP_ERROR);
            ex.message = "网络不给力，请检查网络连接";
            return ex;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new ResponeThrowable(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
            /*|| e instanceof ParseException*/) {
            ex = new ResponeThrowable(e, ERROR.PARSE_ERROR);
            ex.message = "解析错误";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponeThrowable(e, ERROR.NETWORD_ERROR);
            ex.message = "网络不给力，请检查网络连接";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponeThrowable(e, ERROR.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        } else if (e instanceof AppServerException) {
            ex = new ResponeThrowable(e, ERROR.APP_SERVER_ERROR);
            ex.message = e.getMessage();
            ex.errorCode = ((AppServerException) e).errorCode;
            return ex;
        } else if (e instanceof SocketTimeoutException) {
            ex = new ResponeThrowable(e, ERROR.APP_SERVER_ERROR);
            ex.message = "请求连接超时,请稍后重试";
            return ex;
        } else if (e instanceof SocketException) {
            ex = new ResponeThrowable(e, ERROR.APP_SERVER_ERROR);
            ex.message = "网络不给力，请检查网络连接";
            return ex;
        } else {
            ex = new ResponeThrowable(e, ERROR.UNKNOWN);
            ex.message = "网络不给力，请检查网络连接";
            return ex;
        }
    }

    public static class ResponeThrowable extends Exception {
        public int code;
        public String message;
        public String errorCode;

        public ResponeThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }

    }

    /**
     * 约定异常
     */
    class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;

        /**
         * Server custom error
         */
        public static final int APP_SERVER_ERROR = 1006;
    }

    /**
     * ServerException发生后，将自动转换为ResponeThrowable返回
     */
    class ServerException extends RuntimeException {
        int code;
        String message;
    }

}
