package com.example.seethewayproject.login.presenter;

import com.example.seethewayproject.base.BasePresenter;
import com.example.seethewayproject.login.bean.AffirmRegisterBean;
import com.example.seethewayproject.login.contract.PassWordLoginContract;
import com.example.seethewayproject.login.model.PassWordLoginModel;
import com.example.seethewayproject.net.INetCallBack;

public class PassWordLoginPresenter extends BasePresenter<PassWordLoginContract.IPassWordLoginView> implements PassWordLoginContract.IPassWordLoginPresenter {
    PassWordLoginContract.IPassWordLoginModel mIPassWordLoginModel;
    public PassWordLoginPresenter() {
        mIPassWordLoginModel = new PassWordLoginModel();
    }

    @Override
    public void passWordLogin(String username, String password) {
        mIPassWordLoginModel.passWordLogin(username, password, new INetCallBack<AffirmRegisterBean>() {
            @Override
            public void onSuccess(AffirmRegisterBean affirmRegisterBean) {
                mView.getPWLoginResult(affirmRegisterBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
