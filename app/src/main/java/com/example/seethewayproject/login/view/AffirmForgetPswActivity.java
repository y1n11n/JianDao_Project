package com.example.seethewayproject.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.seethewayproject.R;
import com.example.seethewayproject.base.BaseActivity;
import com.example.seethewayproject.login.bean.VerfiedBean;
import com.example.seethewayproject.login.contract.AffirmForgetPswContract;
import com.example.seethewayproject.login.presenter.AffirmForgetPswPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AffirmForgetPswActivity extends BaseActivity<AffirmForgetPswPresenter> implements AffirmForgetPswContract.IAffirmPaswView {

    @BindView(R.id.iv_aff_forget_close)
    ImageView mIvAffForgetClose;
    @BindView(R.id.et_aff_forget_psw)
    EditText mEtAffForgetPsw;
    @BindView(R.id.et_aff_forget_reg_psw)
    EditText mEtAffForgetRegPsw;
    @BindView(R.id.btn_aff_forget_ok)
    Button mBtnAffForgetOk;
    private String mPhone_num;
    private String mSms;

    @Override
    public int getLayoutId() {
        return R.layout.activity_affirm_forget_psw;
    }

    @Override
    protected AffirmForgetPswPresenter initPresenter() {
        return new AffirmForgetPswPresenter();
    }


    @Override
    public void initView() {
        Intent intent = getIntent();
        mPhone_num = intent.getStringExtra("phone_Num");
        mSms = intent.getStringExtra("verfied_str");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mIvAffForgetClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtnAffForgetOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String psw = mEtAffForgetPsw.getText().toString();
                String regPsw = mEtAffForgetRegPsw.getText().toString();
                if (!TextUtils.isEmpty(psw) && !TextUtils.isEmpty(regPsw)){
                    if (psw.equals(regPsw)){
                        mPresenter.forgetPasw(mPhone_num, mSms, psw);
                    }else
                        Toast.makeText(AffirmForgetPswActivity.this, "请输入相同密码", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(AffirmForgetPswActivity.this, "请完善密码", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void registerResult(VerfiedBean bean) {
        if (bean.getCode() == 1){
            Intent intent = new Intent(AffirmForgetPswActivity.this, LoginActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
