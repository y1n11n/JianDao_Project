package com.example.seethewayproject.login.contract;

import com.example.seethewayproject.base.BaseView;
import com.example.seethewayproject.login.bean.AffirmRegisterBean;
import com.example.seethewayproject.net.INetCallBack;

public class PassWordLoginContract {
    public interface IPassWordLoginView extends BaseView {
        void  getPWLoginResult(AffirmRegisterBean string);
    }
    public interface IPassWordLoginModel{
        <T> void passWordLogin(String username, String password, INetCallBack<T> iNetCallBack);
    }
    public interface IPassWordLoginPresenter{
        void passWordLogin(String username, String password);

    }

}
