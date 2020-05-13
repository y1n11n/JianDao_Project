package com.example.seethewayproject.net;

public interface INetCallBack<T> {
    void onSuccess(T t);
    void onError(Throwable throwable);
}
