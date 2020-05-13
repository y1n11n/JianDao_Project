package com.example.seethewayproject.home.view;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.seethewayproject.R;
import com.example.seethewayproject.base.BaseActivity;
import com.example.seethewayproject.home.adapter.VpAdapter;
import com.example.seethewayproject.home.contract.HomeContract;
import com.example.seethewayproject.home.presenter.HomePresenterImpl;
import com.example.seethewayproject.home.view.fragment.MeFragment;
import com.example.seethewayproject.home.view.fragment.RecommendFragment;
import com.example.seethewayproject.home.view.fragment.SpecialFragment;
import com.example.seethewayproject.home.view.fragment.VideoFragment;
import com.example.seethewayproject.utils.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity<HomePresenterImpl> implements HomeContract.IHomePresenter {


    //    @BindView(R.id.fl)
//    FrameLayout mFl;
    @BindView(R.id.rg)
    RadioGroup mRg;
    @BindView(R.id.home_vp)
    NoScrollViewPager mHomeVp;
    @BindView(R.id.rb_recommend)
    RadioButton mRbRecommend;
    @BindView(R.id.rb_video)
    RadioButton mRbVideo;
    @BindView(R.id.rb_special)
    RadioButton mRbSpecial;
    @BindView(R.id.rb_me)
    RadioButton mRbMe;
    private List<Fragment> mFragments;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected HomePresenterImpl initPresenter() {
        return new HomePresenterImpl();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        initTab();
    }

    private void initTab() {
        mFragments = new ArrayList<>();
        RecommendFragment recommendFragment = new RecommendFragment();
        VideoFragment videoFragment = new VideoFragment();
        SpecialFragment specialFragment = new SpecialFragment();
        MeFragment meFragment = new MeFragment();
        mFragments.add(recommendFragment);
        mFragments.add(videoFragment);
        mFragments.add(specialFragment);
        mFragments.add(meFragment);
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), mFragments);
        mHomeVp.setAdapter(vpAdapter);
        mHomeVp.setCurrentItem(0);
        mHomeVp.setOffscreenPageLimit(4);
    }
    @Override
    public void initListener() {
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_recommend:
                        mHomeVp.setCurrentItem(0);
                        break;
                    case R.id.rb_video:
                        mHomeVp.setCurrentItem(1);
                        break;
                    case R.id.rb_special:
                        mHomeVp.setCurrentItem(2);
                        break;
                    case R.id.rb_me:
                        mHomeVp.setCurrentItem(3);
                        break;
                }
            }
        });
    }

}
