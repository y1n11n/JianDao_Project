package com.example.seethewayproject.home.adapter;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.seethewayproject.R;
import com.example.seethewayproject.home.bean.VideoBean;

import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class VideoAdapter extends BaseQuickAdapter<VideoBean.DataBean.ListBean , BaseViewHolder> {
    public VideoAdapter(int layoutResId, List<VideoBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder holder, VideoBean.DataBean.ListBean dataBean) {
        JzvdStd jiaoziVideo = holder.findView(R.id.jiaozi_video_player);
        jiaoziVideo.setUp(dataBean.getVideo_url(), null);
        ImageView posterImageView = jiaoziVideo.posterImageView;
        posterImageView.setScaleType(ImageView.ScaleType.CENTER);
        Glide.with(getContext()).load(dataBean.getImage_url()).into(posterImageView);
        holder.setText(R.id.tv_title, dataBean.getTheme());
        holder.setText(R.id.tv_desc, dataBean.getDescription());
    }
}
