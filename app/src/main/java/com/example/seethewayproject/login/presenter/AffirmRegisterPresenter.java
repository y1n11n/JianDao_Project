package com.example.seethewayproject.login.presenter;

import com.example.seethewayproject.base.BasePresenter;
import com.example.seethewayproject.login.bean.AffirmRegisterBean;
import com.example.seethewayproject.login.contract.AffirmRegisterContract;
import com.example.seethewayproject.login.model.AffirmRegisterModel;
import com.example.seethewayproject.net.INetCallBack;

public class AffirmRegisterPresenter extends BasePresenter<AffirmRegisterContract.IAffirmView> implements AffirmRegisterContract.IAffirmPresenter {
    AffirmRegisterContract.IAffirmMode mIAffirmMode;
    public AffirmRegisterPresenter() {
        mIAffirmMode = new AffirmRegisterModel();
    }

    @Override
    public void register(String phoneNum, String password, String affirm_password) {
       mIAffirmMode.register(phoneNum, password, affirm_password, new INetCallBack<AffirmRegisterBean>() {
           @Override
           public void onSuccess(AffirmRegisterBean affirmRegisterBean) {
               mView.registerResult(affirmRegisterBean);
           }

           @Override
           public void onError(Throwable throwable) {

           }
       });
    }
}
