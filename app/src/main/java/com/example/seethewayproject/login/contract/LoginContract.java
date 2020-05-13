package com.example.seethewayproject.login.contract;

import com.example.seethewayproject.base.BaseView;
import com.example.seethewayproject.home.bean.SpecialBean;
import com.example.seethewayproject.login.bean.AffirmRegisterBean;
import com.example.seethewayproject.login.bean.VerfiedBean;
import com.example.seethewayproject.net.INetCallBack;

public class LoginContract {
    public interface ILoginView extends BaseView {
        void getVerified(VerfiedBean verfiedBean);
        void getLoginResult(AffirmRegisterBean str);
        void checksmscodeResult(VerfiedBean verfiedBean);
    }
    public interface ILoginPresenter {
        void getVerified(String str, String type);
        void login(String phone_num ,String sms_code);
        void checksmscode(String phoneStr,String smsCode, String type);
    }
    public interface ILoginModel {
        <T> void getVerified(String phoneStr, String type,INetCallBack<T> iNetCallBack);
        <T> void login(String phone_num ,String sms_code,INetCallBack<T> iNetCallBack);
        <T> void checksmscode(String phoneStr,String smsCode, String type,INetCallBack<T> iNetCallBack);
    }
}
