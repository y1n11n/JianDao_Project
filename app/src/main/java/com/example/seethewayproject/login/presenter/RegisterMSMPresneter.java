package com.example.seethewayproject.login.presenter;

import com.example.seethewayproject.base.BasePresenter;
import com.example.seethewayproject.home.contract.RecommendListContract;
import com.example.seethewayproject.login.bean.VerfiedBean;
import com.example.seethewayproject.login.contract.RegisterMSMContract;
import com.example.seethewayproject.login.model.RegisterMSMModel;
import com.example.seethewayproject.net.INetCallBack;

public class RegisterMSMPresneter extends BasePresenter<RegisterMSMContract.IRegisterMSMView> implements RegisterMSMContract.IRegisterMSMPresenter {
    RegisterMSMContract.IRegisterMSMMode mIRegisterMSMMode;

    public RegisterMSMPresneter() {
        mIRegisterMSMMode = new RegisterMSMModel();
    }

    @Override
    public void getVerified(String string, String type) {
        mIRegisterMSMMode.getVerified(string, type, new INetCallBack<VerfiedBean>() {
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
    public void checkSmsCode(String phoneNum, String smsCode, String type) {
        mIRegisterMSMMode.checkSmsCode(phoneNum, smsCode, type, new INetCallBack<VerfiedBean>() {
            @Override
            public void onSuccess(VerfiedBean verfiedBean) {
                mView.checkSmsCodeResult(verfiedBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
