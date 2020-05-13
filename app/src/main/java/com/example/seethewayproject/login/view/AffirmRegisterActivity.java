package com.example.seethewayproject.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seethewayproject.R;
import com.example.seethewayproject.base.BaseActivity;
import com.example.seethewayproject.home.view.HomeActivity;
import com.example.seethewayproject.login.bean.AffirmRegisterBean;
import com.example.seethewayproject.login.contract.AffirmRegisterContract;
import com.example.seethewayproject.login.presenter.AffirmRegisterPresenter;
import com.tencent.mmkv.MMKV;

import butterknife.BindView;
import butterknife.ButterKnife;

//确认注册
public class AffirmRegisterActivity extends BaseActivity<AffirmRegisterPresenter> implements AffirmRegisterContract.IAffirmView {

    @BindView(R.id.et_aff_reg_psw)
    EditText mEtAffRegPsw;
    @BindView(R.id.et_aff_reg_confirm_psw)
    EditText mEtAffRegConfirmPsw;
    @BindView(R.id.btn_aff_reg)
    Button mBtnAffReg;
    private String mPhoneNum;

    @Override
    public int getLayoutId() {
        return R.layout.activity_affirm_register;
    }

    @Override
    protected AffirmRegisterPresenter initPresenter() {
        return new AffirmRegisterPresenter();
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mPhoneNum = getIntent().getStringExtra("phoneNum");
    }

    @Override
    public void initListener() {
        mBtnAffReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String psw = mEtAffRegPsw.getText().toString();
                String psw_confirm = mEtAffRegConfirmPsw.getText().toString();
                if (!TextUtils.isEmpty(psw) && !TextUtils.isEmpty(psw_confirm)){
                    if (psw.equals(psw_confirm)){
                        mPresenter.register(mPhoneNum, psw,psw_confirm );
                    }else Toast.makeText(AffirmRegisterActivity.this, "两次输入密码不同", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(AffirmRegisterActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void registerResult(AffirmRegisterBean registerBean) {

        if (registerBean.getCode() == 1){
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            if (null != registerBean.getData().getToken().getValue() && registerBean.getData().getToken().getValue() != ""){
                MMKV mmkv = MMKV.defaultMMKV();
                mmkv.encode("token", registerBean.getData().getToken().getValue());
                mmkv.encode("expire_time", registerBean.getData().getToken().getExpire_time());
                mmkv.encode("head_url", registerBean.getData().getUser_info().getHead_url());
                mmkv.encode("nickname", registerBean.getData().getUser_info().getNickname());
                mmkv.encode("mobile", registerBean.getData().getUser_info().getMobile());

                Intent intent = new Intent(AffirmRegisterActivity.this, HomeActivity.class);
                startActivity(intent);

            }
        }else {
            Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}
