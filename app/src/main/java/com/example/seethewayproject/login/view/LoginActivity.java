package com.example.seethewayproject.login.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.example.seethewayproject.login.bean.VerfiedBean;
import com.example.seethewayproject.login.contract.LoginContract;
import com.example.seethewayproject.login.presenter.LoginPresenter;
import com.example.seethewayproject.utils.PhoneNumFormatUtil;
import com.tencent.mmkv.MMKV;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.ILoginView {

    @BindView(R.id.et_phone_num)
    EditText mEtPhoneNum;
    @BindView(R.id.et_verified)
    EditText mEtVerified;
    @BindView(R.id.tv_send_verfied)
    TextView mTvSendVerfied;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_go_regiest)
    TextView mTvGoRegiest;
    @BindView(R.id.iv_login_close)
    ImageView mIvLoginClose;
    @BindView(R.id.tv_go_psw_login)
    TextView mTvGoPswLogin;

    private String edit_phone_num;
    private String edit_sms_code;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mTvSendVerfied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneStr = mEtPhoneNum.getText().toString();
                if (!TextUtils.isEmpty(phoneStr) && PhoneNumFormatUtil.isMobileNO(phoneStr)) {
                    //手机号格式正确，发送短信验证码，  4 为登录
                    mPresenter.getVerified(phoneStr, "4");
                } else {
                    Toast.makeText(LoginActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_phone_num = mEtPhoneNum.getText().toString();
                edit_sms_code = mEtVerified.getText().toString();
                if (!TextUtils.isEmpty(edit_phone_num) && PhoneNumFormatUtil.isMobileNO(edit_phone_num)) {
                    if (!TextUtils.isEmpty(edit_sms_code)) {
                        Pattern pattern = Pattern.compile("\\d{6}");
                        boolean matches = pattern.matcher(edit_sms_code).matches();
                        if (matches) {
                            mPresenter.checksmscode(edit_phone_num, edit_sms_code, "4");
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        } else Toast.makeText(LoginActivity.this, "验证码输入错误", Toast.LENGTH_SHORT).show();
                    } else Toast.makeText(LoginActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(LoginActivity.this, "请输入正确输入手机号", Toast.LENGTH_SHORT).show();
            }
        });
        mTvGoRegiest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterMSMCodeActivity.class);
                startActivity(intent);
            }
        });

        mTvGoPswLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, PassWordLoginActivity.class);
                startActivity(intent);
            }
        });
        mIvLoginClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void getVerified(VerfiedBean verfiedBean) {
        if (verfiedBean.getCode() == 1) {
            Toast.makeText(this, "发送验证码成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "发送验证码失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getLoginResult(AffirmRegisterBean registerBean) {
        if (registerBean.getCode() == 1) {
            Toast.makeText(this, "登录成功，并返回数据", Toast.LENGTH_SHORT).show();
            if (null != registerBean.getData().getToken().getValue() && registerBean.getData().getToken().getValue() != "") {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                MMKV mmkv = MMKV.defaultMMKV();
                mmkv.encode("token", registerBean.getData().getToken().getValue());
                mmkv.encode("expire_time", registerBean.getData().getToken().getExpire_time());
                mmkv.encode("head_url", registerBean.getData().getUser_info().getHead_url());
                mmkv.encode("nickname", registerBean.getData().getUser_info().getNickname());
                mmkv.encode("mobile", registerBean.getData().getUser_info().getMobile());
                startActivity(intent);
            }
        }


    }

    /**
     * 验证码是否正确返回
     */
    @Override
    public void checksmscodeResult(VerfiedBean verfiedBean) {
        if (verfiedBean.getCode() == 1) {
            mPresenter.login(edit_phone_num, edit_sms_code);
        } else {
            Toast.makeText(this, "验证码输入错误", Toast.LENGTH_SHORT).show();
        }
    }

}
