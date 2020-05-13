package com.example.seethewayproject.home.view.fragment;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seethewayproject.R;
import com.example.seethewayproject.base.BaseFragment;
import com.example.seethewayproject.home.adapter.VideoAdapter;
import com.example.seethewayproject.home.bean.VideoBean;
import com.example.seethewayproject.home.contract.VideoContract;
import com.example.seethewayproject.home.presenter.VideoPresenter;

import butterknife.BindView;

public class VideoFragment extends BaseFragment<VideoPresenter> implements VideoContract.IVideoView {

    @BindView(R.id.toolbar_video)
    Toolbar mToolbarVideo;

    @BindView(R.id.rlv_videl)
    RecyclerView mRlvVidel;
    private VideoAdapter mAdapter;

    @Override
    protected VideoPresenter initPresenter() {
        return new VideoPresenter();
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView(View inflate) {

    }

    @Override
    protected void initData() {
        mPresenter.getVideoData();
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void setVideoData(VideoBean videoBean) {
        if (videoBean.getCode() == 1) {
            mRlvVidel.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter = new VideoAdapter(R.layout.video_item, videoBean.getData().getList());
            mRlvVidel.setAdapter(mAdapter);
        }
    }
}
