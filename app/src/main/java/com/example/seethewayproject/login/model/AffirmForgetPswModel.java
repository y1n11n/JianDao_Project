package com.example.seethewayproject.login.model;

import android.util.Log;

import com.example.seethewayproject.login.contract.AffirmForgetPswContract;
import com.example.seethewayproject.net.INetCallBack;
import com.example.seethewayproject.net.NetWorkFactory;
import com.example.seethewayproject.net.api.URLConstants;
import com.example.seethewayproject.utils.ParamsUtils;

import java.util.HashMap;

public class AffirmForgetPswModel implements AffirmForgetPswContract.IAffirmPaswMode {
    @Override
    public <T> void forgetPasw(String mobile, String sms_code, String password, INetCallBack<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",mobile);
        commonParams.put("password",password);
        commonParams.put("sms_code",sms_code);

        for (String key: commonParams.keySet()) {
            Log.e("TAG","FPkey="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.FORGET_PSW,commonParams,iNetCallBack);


    }
}
