package com.example.seethewayproject.login.contract;

import com.example.seethewayproject.base.BaseView;
import com.example.seethewayproject.login.bean.VerfiedBean;
import com.example.seethewayproject.net.INetCallBack;

public class AffirmForgetPswContract {

    public interface IAffirmPaswView extends BaseView {
        void registerResult(VerfiedBean bean);
    }
    public interface IAffirmPaswMode {
        <T> void forgetPasw(String mobile, String sms_code, String password, INetCallBack<T> iNetCallBack);
    }
    public interface IAffirmPaswPresenter {
        void forgetPasw(String mobile, String sms_code, String password);
    }
}
