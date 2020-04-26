package com.don.basemvp.config.retrofit;

import com.don.basemvp.bean.BaseHttpResult;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/***
 *@author Don
 *@date on 2020/4/26 16:36
 *@describe 自定义请求ResponseBody
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<BaseHttpResult<T>> adapter;

    GsonResponseBodyConverter(Gson gson, TypeToken<T> typeToken) {
        this.gson = gson;
        RetrofitGsonUtils.ParameterizedTypeImpl parameterizedType =
                new RetrofitGsonUtils.ParameterizedTypeImpl(null, BaseHttpResult.class, typeToken.getType());
        this.adapter = (TypeAdapter<BaseHttpResult<T>>) this.gson.getAdapter(TypeToken.get(parameterizedType));
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            BaseHttpResult<T> result = adapter.read(jsonReader);
            int errorCode = result.code;
            String msg = result.msg;
            if (errorCode != 1) {
                //做自定义错误代码判断
            }
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonIOException("JSON document was not fully consumed.");
            }
            return result.data;
        } finally {
            value.close();
        }
    }
}
