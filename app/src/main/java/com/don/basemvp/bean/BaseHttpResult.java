package com.don.basemvp.bean;

/***
 *@author Don
 *@date on 2020/4/26 16:03
 *@describe 默认请求返回体
 */
public class BaseHttpResult<T> {
    public String msg;
    public int code;
    public T data;
}
