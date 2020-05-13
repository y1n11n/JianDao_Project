package com.example.seethewayproject.home.presenter;

import com.example.seethewayproject.base.BasePresenter;
import com.example.seethewayproject.home.bean.User;
import com.example.seethewayproject.home.bean.VideoBean;
import com.example.seethewayproject.home.contract.VideoContract;
import com.example.seethewayproject.home.model.VideoModel;
import com.example.seethewayproject.net.INetCallBack;

public class VideoPresenter extends BasePresenter<VideoContract.IVideoView> implements VideoContract.IVideoPresenter {
    VideoContract.IVideoModel mIVideoModel;
    public VideoPresenter() {
        mIVideoModel = new VideoModel();
    }

    @Override
    public void getVideoData() {
        mIVideoModel.getVideoData(new INetCallBack<VideoBean>() {
            @Override
            public void onSuccess(VideoBean videoBean) {
                mView.setVideoData(videoBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
