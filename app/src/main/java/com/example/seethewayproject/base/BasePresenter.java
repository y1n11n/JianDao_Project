package com.example.seethewayproject.base;

public class BasePresenter<T extends BaseView> {
    protected T mView;
    public void AttachView(T baseView){
        mView = baseView;
    }
    public void disAttachView(){
        mView = null;
    }

}
