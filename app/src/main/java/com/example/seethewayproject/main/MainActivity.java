package com.example.seethewayproject.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.seethewayproject.R;
import com.example.seethewayproject.base.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter> {

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
