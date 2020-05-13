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
import com.example.seethewayproject.login.contract.ForgetPassWordContract;
import com.example.seethewayproject.login.presenter.ForgetPassWordPresenter;
import com.example.seethewayproject.utils.PhoneNumFormatUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPassWordActivity extends BaseActivity<ForgetPassWordPresenter> implements ForgetPassWordContract.IForgetPWView {

    @BindView(R.id.iv_forget_reg_close)
    ImageView mIvForgetRegClose;
    @BindView(R.id.et_forget_phone_num)
    EditText mEtForgetPhoneNum;
    @BindView(R.id.et_forget_sms)
    EditText mEtForgetSms;
    @BindView(R.id.tv_send_verfied)
    TextView mTvSendVerfied;
    @BindView(R.id.btn_forget_next)
    Button mBtnForgetNext;
    private String mPhone_num;
    private String mSms;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_pass_word;
    }

    @Override
    protected ForgetPassWordPresenter initPresenter() {
        return new ForgetPassWordPresenter();
    }


    @Override
    public void initView() {
        mPhone_num = getIntent().getStringExtra("phone_num");
        mEtForgetPhoneNum.setText(mPhone_num);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mIvForgetRegClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvSendVerfied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getVerified(mPhone_num, "2");
            }
        });
        mBtnForgetNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhone_num = mEtForgetPhoneNum.getText().toString();
                mSms = mEtForgetSms.getText().toString().trim();
                if (!TextUtils.isEmpty(mPhone_num) && !TextUtils.isEmpty(mSms)){
                    if (PhoneNumFormatUtil.isMobileNO(mPhone_num)){
                        mPresenter.checkSmsCode(mPhone_num, mSms, "2");
                    }
                }else Toast.makeText(ForgetPassWordActivity.this, "手机号有误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getVerified(VerfiedBean bean) {
        if (bean.getCode() == 1){
            Toast.makeText(this, "验证码发送成功", Toast.LENGTH_SHORT).show();
        }else Toast.makeText(this, "验证码发送失败", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void checkSmsCodeResult(VerfiedBean verfiedBean) {
        if (verfiedBean.getCode() == 1){
            //验证成功
            Intent intent = new Intent();
            intent.putExtra("phone_Num", mPhone_num);
            intent.putExtra("verfied_str", mSms);
            startActivity(intent);
        }
    }
}
