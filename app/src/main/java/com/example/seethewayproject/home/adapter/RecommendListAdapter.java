package com.example.seethewayproject.home.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.seethewayproject.R;
import com.example.seethewayproject.home.bean.RecommendListBean;

import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class RecommendListAdapter extends BaseMultiItemQuickAdapter<RecommendListBean.RecommendListItemBean, BaseViewHolder> {
    public RecommendListAdapter(List<RecommendListBean.RecommendListItemBean> recommendListItemBean) {
        addItemType(RecommendListBean.RecommendListItemBean.TYPE_LEFT_IMG, R.layout.recommend_news_left_img);//左图
        addItemType(RecommendListBean.RecommendListItemBean.TYPE_BIG_IMG, R.layout.recommend_news_big_img);//中间大图
        addItemType(RecommendListBean.RecommendListItemBean.TYPE_RIGHT_IMG, R.layout.recommend_news_right_img);//右图
        addItemType(RecommendListBean.RecommendListItemBean.TYPE_VIDEO, R.layout.recommend_news_video);//视频
        addItemType(RecommendListBean.RecommendListItemBean.TYPE_NOW, R.layout.recommend_news_now);//即时

    }

    @Override
    protected void convert(BaseViewHolder holder, RecommendListBean.RecommendListItemBean recommendListItemBean) {
        switch (holder.getItemViewType()) {
            case RecommendListBean.RecommendListItemBean.TYPE_LEFT_IMG:
                initLeftImg(holder, recommendListItemBean);
                break;
            case RecommendListBean.RecommendListItemBean.TYPE_BIG_IMG:
                initBigImg(holder, recommendListItemBean);
                break;
            case RecommendListBean.RecommendListItemBean.TYPE_RIGHT_IMG:
                initRightImg(holder, recommendListItemBean);
                break;
            case RecommendListBean.RecommendListItemBean.TYPE_VIDEO:
                initVideo(holder, recommendListItemBean);
                break;
            case RecommendListBean.RecommendListItemBean.TYPE_NOW:
                initNow(holder, recommendListItemBean);
                break;
        }
    }

    private void initNow(BaseViewHolder holder, RecommendListBean.RecommendListItemBean bean) {
        RecommendListBean.DataBean.ArticleListBean data = (RecommendListBean.DataBean.ArticleListBean) bean.data;
        holder.setText(R.id.tv_now_title, "[ "+data.getTheme()+" ]");
        holder.setText(R.id.tv_now_desc, data.getContent());
        holder.setText(R.id.tv_now_time, data.getEdit_time());
    }

    private void initVideo(BaseViewHolder holder, RecommendListBean.RecommendListItemBean bean) {
        RecommendListBean.DataBean.ArticleListBean data = (RecommendListBean.DataBean.ArticleListBean) bean.data;
        holder.setText(R.id.tv_video_title, data.getTheme());
        holder.setText(R.id.tv_video_column_name, data.getColumn_name());
        JzvdStd jZVideo = holder.findView(R.id.jiaozi_video_player);
        jZVideo.setUp(data.getVideo_url(), null);
        //设置视频封面
        ImageView posterImageView = jZVideo.posterImageView;
        posterImageView.setScaleType(ImageView.ScaleType.CENTER);
        Glide.with(getContext()).load(data.getImage_url()).into(posterImageView);

    }

    private void initRightImg(BaseViewHolder holder, RecommendListBean.RecommendListItemBean bean) {
        RecommendListBean.DataBean.ArticleListBean data = (RecommendListBean.DataBean.ArticleListBean) bean.data;
        holder.setText(R.id.tv_right_title, data.getTheme());
        holder.setText(R.id.tv_right_column_name, data.getColumn_name());
        ImageView iv = holder.findView(R.id.iv_right);
        Glide.with(getContext()).load(data.getImage_url()).into(iv);
    }

    private void initBigImg(BaseViewHolder holder, RecommendListBean.RecommendListItemBean bean) {
        RecommendListBean.DataBean.ArticleListBean data = (RecommendListBean.DataBean.ArticleListBean) bean.data;
        holder.setText(R.id.tv_big_title, data.getTheme());
        holder.setText(R.id.tv_big_column, data.getColumn_name());
        Glide.with(getContext()).load(data.getImage_url()).into((ImageView) holder.findView(R.id.img_big));
    }

    private void initLeftImg(BaseViewHolder holder, RecommendListBean.RecommendListItemBean bean) {
        RecommendListBean.DataBean.ArticleListBean data = (RecommendListBean.DataBean.ArticleListBean) bean.data;
        String theme = data.getTheme();
        holder.setText(R.id.tv_title, theme);
        holder.setText(R.id.tv_column_name, data.getColumn_name());
        ImageView ivLeft = holder.findView(R.id.iv_left);
        Glide.with(getContext()).load(data.getImage_url()).into(ivLeft);
    }
}
