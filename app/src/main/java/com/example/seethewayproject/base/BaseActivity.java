package com.example.seethewayproject.base;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.seethewayproject.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{
    protected P mPresenter;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBind = ButterKnife.bind(this);
        mPresenter = initPresenter();
        if (mPresenter != null){
            mPresenter.AttachView(this);
        }
        initView();
        initData();
        initListener();
    }

    public abstract int getLayoutId();

    protected abstract P initPresenter();

    public abstract void initView();

    public abstract void initData();

    public abstract void initListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mPresenter.disAttachView();
        mBind.unbind();
    }
}
