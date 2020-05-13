package com.example.seethewayproject.login.contract;

import com.example.seethewayproject.base.BaseView;
import com.example.seethewayproject.login.bean.AffirmRegisterBean;
import com.example.seethewayproject.net.INetCallBack;

public class AffirmRegisterContract {
    public interface IAffirmView extends BaseView {
        void registerResult(AffirmRegisterBean registerBean);
    }

    public interface IAffirmMode {
        <T> void register(String phoneNum, String password, String affirm_password, INetCallBack<T> iNetCallBack);
    }

    public interface IAffirmPresenter {
        void register(String phoneNum, String password, String affirm_password);

    }
}
