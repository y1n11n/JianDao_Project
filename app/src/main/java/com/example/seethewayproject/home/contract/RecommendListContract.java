package com.example.seethewayproject.home.contract;

import com.example.seethewayproject.base.BaseView;
import com.example.seethewayproject.home.bean.RecommendListBean;
import com.example.seethewayproject.net.INetCallBack;

public class RecommendListContract {
    public interface IRecommendListView extends BaseView{
        void setRecommendList(RecommendListBean recommendList);
    }
    public interface IRecommendListModel{
        <T> void getRecommendList(String id, INetCallBack<T> iNetCallBack);
    }
    public interface IRecommendListPresenter{
        void getRecommendList(String id);
    }
}
