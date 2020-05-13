package com.example.seethewayproject.utils;

import com.example.seethewayproject.net.INetCallBack;
import com.example.seethewayproject.net.INetWork;

import java.util.HashMap;

/**
 * httpurl   请求方式---   切换使用  不需要更改代码，做到网络请求的切换
 * 工厂设计模式
 * 工厂设计类
 */
public class HttpUrlConnetionUtils implements INetWork {

    private static  HttpUrlConnetionUtils httpUrlConnetionUtils;

    private HttpUrlConnetionUtils() {
    }

    public static HttpUrlConnetionUtils getInstance() {
        if(httpUrlConnetionUtils == null){
            synchronized (RetrofitUtils.class){
                if (httpUrlConnetionUtils == null){
                    httpUrlConnetionUtils = new HttpUrlConnetionUtils();
                }
            }
        }
        return httpUrlConnetionUtils;
    }

    @Override
    public <T> void get(String url, INetCallBack<T> netCallBack) {
//        也做网络请求
//将请求到的结果通过咱们的   INetCallBack   返回的
    }

    @Override
    public <T> void get(String url, HashMap<String, String> s,INetCallBack<T> netCallBack) {

    }

    @Override
    public <T> void post(String url,INetCallBack<T> netCallBack) {

    }

    @Override
    public <T> void post(String url, HashMap<String, String> s,INetCallBack<T> netCallBack) {

    }
}
