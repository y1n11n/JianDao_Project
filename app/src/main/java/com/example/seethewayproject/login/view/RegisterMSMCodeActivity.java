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
import com.example.seethewayproject.login.bean.VerfiedBean;
import com.example.seethewayproject.login.contract.RegisterMSMContract;
import com.example.seethewayproject.login.presenter.RegisterMSMPresneter;
import com.example.seethewayproject.utils.PhoneNumFormatUtil;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterMSMCodeActivity extends BaseActivity<RegisterMSMPresneter> implements RegisterMSMContract.IRegisterMSMView {

    @BindView(R.id.et_reg_phone_num)
    EditText mEtRegPhoneNum;
    @BindView(R.id.et_reg_verified)
    EditText mEtRegVerified;
    @BindView(R.id.tv_reg_send_verfied)
    TextView mBtnRegSendVerfied;
    @BindView(R.id.btn_regist_next)
    Button mBtnRegist;
    @BindView(R.id.iv_login_close)
    ImageView mIvLoginClose;
    @BindView(R.id.tv_go_sms_login)
    TextView mTvGoSmsLogin;
    @BindView(R.id.tv_go_psw_login)
    TextView mTvGoPswLogin;
    private String reg_et_phone_num;
    private String reg_et_sms_code;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_msm_code;
    }

    @Override
    protected RegisterMSMPresneter initPresenter() {
        return new RegisterMSMPresneter();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mBtnRegSendVerfied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneStr = mEtRegPhoneNum.getText().toString();
                if (!TextUtils.isEmpty(phoneStr) && PhoneNumFormatUtil.isMobileNO(phoneStr)) {
                    //手机号格式正确，发送短信验证码，  1 为注册
                    mPresenter.getVerified(phoneStr, "1");
                } else {
                    Toast.makeText(RegisterMSMCodeActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBtnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg_et_phone_num = mEtRegPhoneNum.getText().toString();
                reg_et_sms_code = mEtRegVerified.getText().toString();
                if (!TextUtils.isEmpty(reg_et_phone_num) && PhoneNumFormatUtil.isMobileNO(reg_et_phone_num)) {
                    if (!TextUtils.isEmpty(reg_et_sms_code)) {
                        Pattern pattern = Pattern.compile("\\d{6}");
                        boolean matches = pattern.matcher(reg_et_sms_code).matches();
                        if (matches) {
                            mPresenter.checkSmsCode(reg_et_phone_num, reg_et_sms_code, "1");
//                            Toast.makeText(RegisterMSMCodeActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(RegisterMSMCodeActivity.this, "验证码输入错误", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(RegisterMSMCodeActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(RegisterMSMCodeActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();


            }
        });

        mTvGoSmsLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mIvLoginClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvGoPswLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterMSMCodeActivity.this, PassWordLoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void getVerified(VerfiedBean s) {
        if (s.getCode() == 1) {
            Toast.makeText(this, "验证码发送成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "验证码发送失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getLoginResult(String string) {

    }

    @Override
    public void checkSmsCodeResult(VerfiedBean verfiedBean) {
        if (verfiedBean.getCode() == 1) {
//            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AffirmRegisterActivity.class);
            intent.putExtra("phoneNum", reg_et_phone_num);
            startActivity(intent);

        }else {
            Toast.makeText(this, verfiedBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
