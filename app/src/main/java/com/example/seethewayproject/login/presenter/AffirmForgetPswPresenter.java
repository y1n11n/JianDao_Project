package com.example.seethewayproject.login.presenter;

import com.example.seethewayproject.base.BasePresenter;
import com.example.seethewayproject.login.bean.VerfiedBean;
import com.example.seethewayproject.login.contract.AffirmForgetPswContract;
import com.example.seethewayproject.login.model.AffirmForgetPswModel;
import com.example.seethewayproject.net.INetCallBack;

public class AffirmForgetPswPresenter extends BasePresenter<AffirmForgetPswContract.IAffirmPaswView> implements AffirmForgetPswContract.IAffirmPaswPresenter {

    AffirmForgetPswContract.IAffirmPaswMode mIAffirmPaswMode;

    public AffirmForgetPswPresenter() {
        mIAffirmPaswMode = new AffirmForgetPswModel();
    }

    @Override
    public void forgetPasw(String mobile, String sms_code, String password) {
        mIAffirmPaswMode.forgetPasw(mobile, sms_code, password, new INetCallBack<VerfiedBean>() {
            @Override
            public void onSuccess(VerfiedBean bean) {
                mView.registerResult(bean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
