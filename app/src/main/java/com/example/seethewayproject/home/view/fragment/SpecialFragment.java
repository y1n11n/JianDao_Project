package com.example.seethewayproject.home.view.fragment;


import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.example.seethewayproject.R;
import com.example.seethewayproject.base.BaseFragment;
import com.example.seethewayproject.home.adapter.SpecialAdapter;
import com.example.seethewayproject.home.bean.RecommendListBean;
import com.example.seethewayproject.home.bean.SpecialBean;
import com.example.seethewayproject.home.contract.SpecialContract;
import com.example.seethewayproject.home.presenter.SpecialPresenter;
import com.example.seethewayproject.home.view.Banner_Indicator;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;

public class SpecialFragment extends BaseFragment<SpecialPresenter> implements SpecialContract.ISpecialView {


    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;
    private SpecialAdapter mAdapter;

    private int start;
    private int number;
    private int point_time;
    private SpecialBean mSpecialBean;

    @Override
    protected SpecialPresenter initPresenter() {
        return new SpecialPresenter();
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_special;
    }

    @Override
    protected void initView(View inflate) {
    }

    @Override
    protected void initData() {
        mPresenter.getSpecialList(start, number, point_time);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void setSpecialList(SpecialBean specialBean) {
        mSpecialBean = specialBean;
        mRlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SpecialAdapter(R.layout.special_item, mSpecialBean.getData().getList());
        //添加分割线
//        mRlv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRlv.setAdapter(mAdapter);
        addHeader(mSpecialBean.getData().getBanner_list());
        loadMore(mSpecialBean);
    }

    private void loadMore(SpecialBean bean) {
        if (bean.getData().getMore() == 1) {
//            start = bean.getData().getStart();
//            number = bean.getData().getNumber();
//            point_time = bean.getData().getPoint_time();
//            mAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
//                @Override
//                public void onLoadMore() {
//                    mPresenter.getSpecialList(start, number, point_time);
//                }
//            });
            mSrl.finishRefresh();
            mSrl.finishLoadMore();
            mSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    start = bean.getData().getStart();
                    number = bean.getData().getNumber();
                    point_time = bean.getData().getPoint_time();
                    mPresenter.getSpecialList(start, number, point_time);
                }

                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    start = 0;
                    number = 0;
                    point_time = 0;
                    mPresenter.getSpecialList(start, number, point_time);
                }
            });


        } else {
            Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
//            mAdapter.getLoadMoreModule().loadMoreEnd();
        }
    }

    private void addHeader(List<SpecialBean.DataBean.BannerListBean> banner_list) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.header_banner, null);
        mAdapter.addHeaderView(inflate);
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
                SpecialBean.DataBean.BannerListBean bannerData = (SpecialBean.DataBean.BannerListBean) path;
                Glide.with(getActivity()).load(bannerData.getImage_url()).into(imageView);
            }
        }).start();

    }

}
