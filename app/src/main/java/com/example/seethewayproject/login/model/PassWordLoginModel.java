package com.example.seethewayproject.login.model;

import android.util.Log;

import com.example.seethewayproject.login.contract.PassWordLoginContract;
import com.example.seethewayproject.net.INetCallBack;
import com.example.seethewayproject.net.NetWorkFactory;
import com.example.seethewayproject.net.api.URLConstants;
import com.example.seethewayproject.utils.ParamsUtils;

import java.util.HashMap;

public class PassWordLoginModel implements PassWordLoginContract.IPassWordLoginModel {
    @Override
    public <T> void passWordLogin(String username, String password, INetCallBack<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("username",username);
        commonParams.put("password",password);

        for (String key: commonParams.keySet()) {
            Log.e("ULTAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.USER_LOGIN,commonParams,iNetCallBack);
    }
}
