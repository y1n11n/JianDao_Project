package com.example.seethewayproject.home.presenter;

import com.example.seethewayproject.base.BasePresenter;
import com.example.seethewayproject.home.bean.ColumBean;
import com.example.seethewayproject.home.bean.ColumnManageBean;
import com.example.seethewayproject.home.bean.RecommendListBean;
import com.example.seethewayproject.home.bean.User;
import com.example.seethewayproject.home.contract.RecommendContract;
import com.example.seethewayproject.home.model.RecommendModel;
import com.example.seethewayproject.net.INetCallBack;

public class RecommendPresenter extends BasePresenter<RecommendContract.IRecommendView> implements RecommendContract.IRecommendPresenter {
    RecommendContract.IRecommendModel mIRecommendModel;
    public RecommendPresenter() {
        mIRecommendModel = new RecommendModel();
    }

    @Override
    public void getColumList() {
        mIRecommendModel.getColumList(new INetCallBack<ColumBean>() {
            @Override
            public void onSuccess(ColumBean bean) {
                mView.setColumList(bean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }


}
