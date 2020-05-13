package com.example.seethewayproject.home.contract;

import com.example.seethewayproject.base.BaseView;
import com.example.seethewayproject.home.bean.ColumBean;
import com.example.seethewayproject.home.bean.ColumnManageBean;
import com.example.seethewayproject.home.bean.RecommendListBean;
import com.example.seethewayproject.net.INetCallBack;

public class RecommendContract {
    public interface IRecommendView extends BaseView {
        void setColumList(ColumBean columList);
    }

    public interface IRecommendModel {
        <T> void getColumList(INetCallBack<T> iNetCallBack);
    }


    public interface IRecommendPresenter {

        void getColumList();

    }
}
