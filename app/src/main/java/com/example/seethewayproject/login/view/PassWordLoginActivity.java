package com.example.seethewayproject.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seethewayproject.R;
import com.example.seethewayproject.base.BaseActivity;
import com.example.seethewayproject.home.view.HomeActivity;
import com.example.seethewayproject.login.bean.AffirmRegisterBean;
import com.example.seethewayproject.login.contract.PassWordLoginContract;
import com.example.seethewayproject.login.presenter.PassWordLoginPresenter;
import com.example.seethewayproject.utils.PhoneNumFormatUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PassWordLoginActivity extends BaseActivity<PassWordLoginPresenter> implements PassWordLoginContract.IPassWordLoginView {

    @BindView(R.id.iv_psw_login_close)
    ImageView mIvPswLoginClose;
    @BindView(R.id.et_phone_num)
    EditText mEtPhoneNum;
    @BindView(R.id.et_psw)
    EditText mEtPsw;
    @BindView(R.id.tv_forget_psw)
    TextView mTvForgetPsw;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_go_sms_login)
    TextView mTvGoSmsLogin;
    @BindView(R.id.tv_go_regiest)
    TextView mTvGoRegiest;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pass_word_login;
    }

    @Override
    protected PassWordLoginPresenter initPresenter() {
        return new PassWordLoginPresenter();
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
    public String mPhoneNum;
    @Override
    public void initListener() {
        mIvPswLoginClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvGoSmsLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvGoRegiest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PassWordLoginActivity.this, RegisterMSMCodeActivity.class);
                startActivity(intent);
            }
        });
        mTvForgetPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PassWordLoginActivity.this, ForgetPassWordActivity.class);
                intent.putExtra("phone_num", mPhoneNum);
                startActivity(intent);
            }
        });
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhoneNum = mEtPhoneNum.getText().toString();
                String pswStr = mEtPsw.getText().toString();
                if (!TextUtils.isEmpty(mPhoneNum) && !TextUtils.isEmpty(pswStr)){
                    if (PhoneNumFormatUtil.isMobileNO(mPhoneNum)){
                        mPresenter.passWordLogin(mPhoneNum, pswStr);
                    }else Toast.makeText(PassWordLoginActivity.this, "手机号输入有误", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(PassWordLoginActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void getPWLoginResult(AffirmRegisterBean bean) {
        if (bean.getCode() == 1){
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PassWordLoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
