package com.example.seethewayproject.home.view.fragment;


import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.seethewayproject.R;
import com.example.seethewayproject.base.BaseFragment;
import com.example.seethewayproject.home.adapter.VpAdapter;
import com.example.seethewayproject.home.bean.ColumBean;
import com.example.seethewayproject.home.bean.RecommendListBean;
import com.example.seethewayproject.home.contract.RecommendContract;
import com.example.seethewayproject.home.presenter.RecommendPresenter;
import com.example.seethewayproject.utils.LogUtils;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecommendFragment extends BaseFragment<RecommendPresenter> implements RecommendContract.IRecommendView {

    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.navigation)
    NavigationView mNavigation;
    @BindView(R.id.iv_rec_logo)
    ImageView mIvRecLogo;
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerlayout;
    @BindView(R.id.iv_rec_search)
    ImageView mIvRecSearch;

    @Override
    protected RecommendPresenter initPresenter() {
        return new RecommendPresenter();
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView(View inflate) {
        //可以禁止NavigationView侧滑弹出
        mDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mIvRecLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开侧滑栏
                mDrawerlayout.openDrawer(mNavigation);
            }
        });

    }

    @Override
    protected void initData() {
        mPresenter.getColumList();
    }

    @Override
    protected void initListener() {
    }

    @Override
    public void setColumList(ColumBean columList) {
        if (columList.getCode() == 1) {
            List<ColumBean.DataBean.ListBean> list = columList.getData().getList();
            List<Fragment> fragments = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                RecommendListFragment fragment = new RecommendListFragment();
                fragments.add(fragment);
                String id = list.get(i).getId();
                Bundle bundle = new Bundle();
                bundle.putString("remId", id);
                fragment.setArguments(bundle);
            }
            VpAdapter vpAdapter = new VpAdapter(getChildFragmentManager(), fragments);
            mViewpager.setAdapter(vpAdapter);
            mTablayout.setupWithViewPager(mViewpager);
            //设置tab文字
            for (int i = 0; i < columList.getData().getList().size(); i++) {
                TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.recommend_tab_item, null);
                //设置第一个tab的背景
                if (i == 0){
                    GradientDrawable drawable = new GradientDrawable();
                    drawable.setCornerRadius(10);
                    drawable.setColor(Color.parseColor("#"+columList.getData().getList().get(i).getBack_color()));
                    textView.setBackground(drawable);
                }
                textView.setText(columList.getData().getList().get(i).getName());
                textView.setGravity(Gravity.CENTER);
                mTablayout.getTabAt(i).setCustomView(textView);
//                mTablayout.addTab(mTablayout.newTab().setCustomView(textView),i);
            }
            //设置tab背景
            mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    for (int i = 0; i < columList.getData().getList().size(); i++) {
                        TextView tabAt = (TextView) mTablayout.getTabAt(i).getCustomView();
                        tabAt.setBackgroundResource(R.color.color_transparent);
                    }
                    GradientDrawable drawable = new GradientDrawable();
                    drawable.setCornerRadius(10);
                    TextView customView = (TextView) tab.getCustomView();
//                    drawable.setStroke(1, Color.parseColor("#ff00ff"));
                    drawable.setColor(Color.parseColor("#" + columList.getData().getList().get(tab.getPosition()).getBack_color()));
                    customView.setBackground(drawable);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        }
    }
}
