package com.example.seethewayproject.home.contract;

import com.example.seethewayproject.base.BaseView;
import com.example.seethewayproject.home.bean.ColumBean;
import com.example.seethewayproject.home.bean.SpecialBean;
import com.example.seethewayproject.net.INetCallBack;

public class SpecialContract {
    public interface ISpecialView extends BaseView {
        void setSpecialList(SpecialBean specialBean);
    }
    public interface ISpecialPresenter {
        void getSpecialList(int start, int number, int point_time);
    }
    public interface ISpecialModel {
        <T> void getSpecialList(int start, int number, int point_time,INetCallBack<T> iNetCallBack);
    }
}
