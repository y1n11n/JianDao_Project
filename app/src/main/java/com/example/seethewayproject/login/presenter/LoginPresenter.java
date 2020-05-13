package com.example.seethewayproject.login.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.seethewayproject.base.BasePresenter;
import com.example.seethewayproject.login.bean.AffirmRegisterBean;
import com.example.seethewayproject.login.bean.VerfiedBean;
import com.example.seethewayproject.login.contract.LoginContract;
import com.example.seethewayproject.login.model.LoginModel;
import com.example.seethewayproject.net.INetCallBack;

import java.io.IOException;

import okhttp3.ResponseBody;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> implements LoginContract.ILoginPresenter {

    LoginContract.ILoginModel mILoginModel;

    public LoginPresenter() {
        mILoginModel = new LoginModel();
    }

    @Override
    public void getVerified(String phoneStr, String type) {
        mILoginModel.getVerified(phoneStr, type, new INetCallBack<VerfiedBean>() {
            @Override
            public void onSuccess(VerfiedBean verfiedBean) {
                    mView.getVerified(verfiedBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
    @Override
    public void login(String phone_num, String sms_code) {
        mILoginModel.login(phone_num, sms_code, new INetCallBack<AffirmRegisterBean>() {
            @Override
            public void onSuccess(AffirmRegisterBean affirmRegisterBean) {
                mView.getLoginResult(affirmRegisterBean);
            }

            @Override
            public void onError(Throwable throwable) {
            }
        });
    }
    @Override
    public void checksmscode(String phoneStr, String smsCode, String type) {
        mILoginModel.checksmscode(phoneStr, smsCode, type, new INetCallBack<VerfiedBean>() {
            @Override
            public void onSuccess(VerfiedBean verfiedBean) {
                mView.checksmscodeResult(verfiedBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
