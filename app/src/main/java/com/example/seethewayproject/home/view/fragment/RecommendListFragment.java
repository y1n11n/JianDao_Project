package com.example.seethewayproject.home.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.seethewayproject.R;
import com.example.seethewayproject.base.BaseFragment;
import com.example.seethewayproject.home.adapter.RecommendListAdapter;
import com.example.seethewayproject.home.adapter.RemBannerAdapter;
import com.example.seethewayproject.home.bean.ArticleListBean;
import com.example.seethewayproject.home.bean.RecommendListBean;
import com.example.seethewayproject.home.contract.RecommendListContract;
import com.example.seethewayproject.home.presenter.RecommendListPresenter;
import com.example.seethewayproject.home.view.Banner_Indicator;
import com.example.seethewayproject.home.view.ItemWebActivity;
import com.xiaweizi.marquee.MarqueeTextView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class RecommendListFragment extends BaseFragment<RecommendListPresenter> implements RecommendListContract.IRecommendListView {

    Banner mRecListBanner;
    @BindView(R.id.rem_list_rlv)
    RecyclerView mRemListRlv;
    private String mRemId;
    private RecommendListAdapter mAdapter;
    private TextView mTvFlash;
    private List<View> banner_views = new ArrayList<>();

    private int viewpager_current_pos = 0;

    @Override
    protected RecommendListPresenter initPresenter() {
        return new RecommendListPresenter();
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_recommend_list;
    }

    @Override
    protected void initView(View inflate) {
        mRemId = getArguments().getString("remId");
        Log.d("RemID", "当前Tab的id: "+mRemId);
        mRemListRlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<RecommendListBean.RecommendListItemBean> list = new ArrayList<>();
        mAdapter = new RecommendListAdapter(list);
        mRemListRlv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getRecommendList(mRemId);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void setRecommendList(RecommendListBean recommendList) {
        if (recommendList.getCode() == 1) {
            ArrayList<RecommendListBean.RecommendListItemBean> list = new ArrayList<>();
            //添加头部Banner
            addHeaderBanner(recommendList.getData().getBanner_list());
//            //添加头部文字轮播
            addHeaderFlash(recommendList.getData().getFlash_list());

            List<ArticleListBean> article_list = recommendList.getData().getArticle_list();
            for (int i = 0; i <article_list.size(); i++) {
                ArticleListBean articleListBean = article_list.get(i);
                RecommendListBean.RecommendListItemBean bean = new RecommendListBean.RecommendListItemBean();
                if (articleListBean.getView_type() == 1) { //左图
                    bean.itemType = RecommendListBean.RecommendListItemBean.TYPE_LEFT_IMG;
                } else if (articleListBean.getView_type() == 2) {//大图
                    bean.itemType = RecommendListBean.RecommendListItemBean.TYPE_BIG_IMG;
                }else if (articleListBean.getView_type() == 3){//右图
                    bean.itemType = RecommendListBean.RecommendListItemBean.TYPE_RIGHT_IMG;
                }else if (articleListBean.getView_type() == 4) {//视频
                    bean.itemType = RecommendListBean.RecommendListItemBean.TYPE_VIDEO;
                }else if (articleListBean.getView_type() == 5) {//即时
                    bean.itemType = RecommendListBean.RecommendListItemBean.TYPE_NOW;
                }
                bean.data  = articleListBean;
                list.add(bean);
            }
            mAdapter.addData(list);
            mAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                    ArticleListBean data = article_list.get(position);
                    Toast.makeText(getActivity(), data.getTheme(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), ItemWebActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("itemData", recommendList);
                    bundle.putInt("itemData_position", position);
                    intent.putExtra("rec_item_bundle", bundle);
                    startActivity(intent);
                }
            });
        }
    }

    private void addHeaderFlash(List<RecommendListBean.DataBean.FlashListBean> flash_list) {

        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.recommend_flash, null, false);
        mTvFlash = inflate.findViewById(R.id.tv_flash);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < flash_list.size(); i++) {
            String theme = flash_list.get(i).getTheme() + "   ";
            Log.d("TAG", "新闻文字轮播--: " + theme);
            SpannableString spannableString = new SpannableString(theme);
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    Toast.makeText(getContext(), theme, Toast.LENGTH_SHORT).show();
                }
            };
            spannableString.setSpan(clickableSpan, 0, theme.length() - 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            mTvFlash.setMovementMethod(LinkMovementMethod.getInstance());
            mTvFlash.setText(spannableString);
            builder.append(spannableString);
        }
        mTvFlash.setText(builder.toString());
        if (flash_list.size() == 0) {
            mTvFlash.setVisibility(View.GONE);
        }
        mAdapter.addHeaderView(inflate, 1);

    }

    int current_banner_item;

    private void addHeaderBanner(List<RecommendListBean.DataBean.BannerListBean> banner_list) {
        addheaderbanner(banner_list);
//        initPagerBanner(banner_list);
    }

    private void initPagerBanner(List<RecommendListBean.DataBean.BannerListBean> banner_list) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.banner_layout, null, false);
        mAdapter.addHeaderView(inflate, 0);
        for (int i = 0; i < banner_list.size(); i++) {
            current_banner_item = i;
            View banner_view = LayoutInflater.from(getActivity()).inflate(R.layout.news_banner_item, null, false);
            TextView tvBanner = banner_view.findViewById(R.id.tv_banner);
            ImageView imgBanner = banner_view.findViewById(R.id.img_banner);
            tvBanner.setText(banner_list.get(i).getTheme());
            Glide.with(getActivity()).load(banner_list.get(i).getImage_url()).into(imgBanner);
            banner_views.add(banner_view);
            banner_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "点击了" + current_banner_item + "个view", Toast.LENGTH_SHORT).show();
                }
            });
        }
        ViewPager vpBanner = inflate.findViewById(R.id.vp_banner);
        Banner_Indicator bannerIndicator = inflate.findViewById(R.id.banner_indicator);
        RemBannerAdapter bannerAdapter = new RemBannerAdapter(banner_views);
        vpBanner.setAdapter(bannerAdapter);
        bannerAdapter.notifyDataSetChanged();
        bannerIndicator.setBannerImageSize(banner_list.size());
        bannerIndicator.setCurrentBannerItem(0);
        vpBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                current_banner_item = position;
                bannerIndicator.setCurrentBannerItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //倒计时
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewpager_current_pos += 1;
                Log.d("TAG", "当前位置: " + viewpager_current_pos % (banner_list.size()));
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        vpBanner.setCurrentItem(viewpager_current_pos % (banner_list.size()));
                    }
                });
            }
        };
        timer.schedule(timerTask, 2000, 2000);
        mAdapter.addHeaderView(inflate, 0);
    }

    private void addheaderbanner(List<RecommendListBean.DataBean.BannerListBean> banner_list) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.header_banner, null);
        mAdapter.addHeaderView(inflate, 0);
        TextView tv_title = inflate.findViewById(R.id.tv_header_banner);
        Banner banner = inflate.findViewById(R.id.banner_news);
        Banner_Indicator btnIndiactor = inflate.findViewById(R.id.ban_indicator);
        btnIndiactor.setBannerImageSize(banner_list.size());
        btnIndiactor.setCurrentBannerItem(0);
        //banner滑动监听
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                String theme = banner_list.get(position).getTheme();
                tv_title.setText(theme);
                btnIndiactor.setCurrentBannerItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        banner.setImages(banner_list).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                RecommendListBean.DataBean.BannerListBean bannerData = (RecommendListBean.DataBean.BannerListBean) path;
                Glide.with(getActivity()).load(bannerData.getImage_url()).into(imageView);
            }
        }).start();
        //banner 条目点击监听
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getActivity(), banner_list.get(position).getTheme(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
