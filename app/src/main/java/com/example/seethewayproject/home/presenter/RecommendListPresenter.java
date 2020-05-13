package com.example.seethewayproject.home.presenter;

import com.example.seethewayproject.base.BasePresenter;
import com.example.seethewayproject.home.bean.RecommendListBean;
import com.example.seethewayproject.home.contract.RecommendContract;
import com.example.seethewayproject.home.contract.RecommendListContract;
import com.example.seethewayproject.home.model.RecommendListModel;
import com.example.seethewayproject.home.view.fragment.RecommendListFragment;
import com.example.seethewayproject.net.INetCallBack;

public class RecommendListPresenter extends BasePresenter<RecommendListContract.IRecommendListView> implements RecommendListContract.IRecommendListPresenter {
    RecommendListContract.IRecommendListModel mIRecommendListModel;

    public RecommendListPresenter() {
        mIRecommendListModel = new RecommendListModel();
    }

    public void getRecommendList(String remId) {
        mIRecommendListModel.getRecommendList(remId, new INetCallBack<RecommendListBean>() {
            @Override
            public void onSuccess(RecommendListBean recommendListBean) {
                mView.setRecommendList(recommendListBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
