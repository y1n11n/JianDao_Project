package com.example.seethewayproject.home.presenter;

import com.example.seethewayproject.base.BasePresenter;
import com.example.seethewayproject.home.bean.SpecialBean;
import com.example.seethewayproject.home.contract.SpecialContract;
import com.example.seethewayproject.home.model.SpecialModel;
import com.example.seethewayproject.net.INetCallBack;

public class SpecialPresenter extends BasePresenter<SpecialContract.ISpecialView> implements SpecialContract.ISpecialPresenter {
    SpecialContract.ISpecialModel mISpecialModel;

    public SpecialPresenter() {
        mISpecialModel = new SpecialModel();
    }

    @Override
    public void getSpecialList(int start, int number, int point_time) {
        mISpecialModel.getSpecialList(start, number, point_time, new INetCallBack<SpecialBean>() {
            @Override
            public void onSuccess(SpecialBean specialBean) {
                mView.setSpecialList(specialBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
