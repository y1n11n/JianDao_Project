package com.example.seethewayproject.home.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.seethewayproject.R;
import com.example.seethewayproject.base.BaseFragment;
import com.example.seethewayproject.base.BasePresenter;
import com.example.seethewayproject.login.view.LoginActivity;

import butterknife.BindView;

public class MeFragment extends BaseFragment {


    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView(View inflate) {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
