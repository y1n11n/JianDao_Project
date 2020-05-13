package com.example.seethewayproject.login.model;

import android.util.Log;

import com.example.seethewayproject.login.contract.LoginContract;
import com.example.seethewayproject.net.INetCallBack;
import com.example.seethewayproject.net.NetWorkFactory;
import com.example.seethewayproject.net.api.URLConstants;
import com.example.seethewayproject.utils.ParamsUtils;

import java.util.HashMap;

public class LoginModel implements LoginContract.ILoginModel {

    @Override
    public <T> void getVerified(String phoneStr, String type, INetCallBack<T> iNetCallBack) {

        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",phoneStr);
        commonParams.put("type", type);

        for (String key: commonParams.keySet()) {
            Log.e("SSTAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.SEND_SMS,commonParams,iNetCallBack);
    }

    @Override
    public <T> void login(String phone_num, String sms_code, INetCallBack<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",phone_num);
        commonParams.put("sms_code", sms_code);
//        此处  -- 登录以后，  需要修改

        for (String key: commonParams.keySet()) {
            Log.e("SLTAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.SMS_LOGIN,commonParams,iNetCallBack);

    }


    @Override
    public <T> void checksmscode(String phoneStr, String smsCode, String type, INetCallBack<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",phoneStr);
        commonParams.put("sms_code", smsCode);
        commonParams.put("type", type);

        for (String key: commonParams.keySet()) {
            Log.e("CSCTAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.CHECKSMSCODE,commonParams,iNetCallBack);
    }
}
