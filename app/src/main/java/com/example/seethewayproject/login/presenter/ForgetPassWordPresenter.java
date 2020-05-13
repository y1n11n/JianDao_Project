package com.example.seethewayproject.login.presenter;

import com.example.seethewayproject.base.BasePresenter;
import com.example.seethewayproject.login.bean.VerfiedBean;
import com.example.seethewayproject.login.contract.ForgetPassWordContract;
import com.example.seethewayproject.login.contract.PassWordLoginContract;
import com.example.seethewayproject.login.model.ForgetPassWordModel;
import com.example.seethewayproject.net.INetCallBack;

public class ForgetPassWordPresenter extends BasePresenter<ForgetPassWordContract.IForgetPWView> implements ForgetPassWordContract.IForgetPWPresenter {
    ForgetPassWordContract.IForgetPWMode mIForgetPWMode;
    public ForgetPassWordPresenter() {
        mIForgetPWMode = new ForgetPassWordModel();
    }

    @Override
    public void getVerified(String string, String type) {
        mIForgetPWMode.getVerified(string, type, new INetCallBack<VerfiedBean>() {
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
        mIForgetPWMode.checkSmsCode(phoneNum, smsCode, type, new INetCallBack<VerfiedBean>() {
            @Override
            public void onSuccess(VerfiedBean bean) {
                mView.checkSmsCodeResult(bean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
