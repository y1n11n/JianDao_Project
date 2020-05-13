package com.example.seethewayproject.home.contract;

import com.example.seethewayproject.base.BaseView;
import com.example.seethewayproject.home.bean.VideoBean;
import com.example.seethewayproject.net.INetCallBack;

public class VideoContract {
    public interface IVideoView extends BaseView {
        void setVideoData(VideoBean videoBean);
    }

    public interface IVideoModel{
        <T> void getVideoData(INetCallBack<T> iNetCallBack);
    }
    public interface IVideoPresenter{
        void getVideoData();
    }
}
