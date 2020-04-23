package com.don.basemvp.model;

/***
 *@author Don
 *@date on 2020/4/23 11:48
 *@describe 基础model
 */
public abstract class BaseModel<T, E> {

    abstract public T getApi();

    abstract public E getBean();
}
