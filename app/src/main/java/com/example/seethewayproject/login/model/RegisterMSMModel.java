package com.example.seethewayproject.login.model;

import android.util.Log;

import com.example.seethewayproject.home.contract.RecommendListContract;
import com.example.seethewayproject.login.contract.RegisterMSMContract;
import com.example.seethewayproject.net.INetCallBack;
import com.example.seethewayproject.net.NetWorkFactory;
import com.example.seethewayproject.net.api.URLConstants;
import com.example.seethewayproject.utils.ParamsUtils;

import java.util.HashMap;

public class RegisterMSMModel implements RegisterMSMContract.IRegisterMSMMode {

    @Override
    public <T> void getVerified(String phoneNum, String type, INetCallBack<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",phoneNum);
        commonParams.put("type", type);

        for (String key: commonParams.keySet()) {
            Log.e("SSTAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.SEND_SMS,commonParams,iNetCallBack);

    }

    @Override
    public <T> void checkSmsCode(String phoneNum, String smsCode, String type, INetCallBack<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",phoneNum);
        commonParams.put("sms_code", smsCode);
        commonParams.put("type", type);

        for (String key: commonParams.keySet()) {
            Log.e("CSCTAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.CHECKSMSCODE,commonParams,iNetCallBack);

    }
}
