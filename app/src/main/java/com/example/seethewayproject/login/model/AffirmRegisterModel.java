package com.example.seethewayproject.login.model;

import android.util.Log;

import com.example.seethewayproject.base.BaseModel;
import com.example.seethewayproject.login.contract.AffirmRegisterContract;
import com.example.seethewayproject.net.INetCallBack;
import com.example.seethewayproject.net.NetWorkFactory;
import com.example.seethewayproject.net.api.URLConstants;
import com.example.seethewayproject.utils.ParamsUtils;

import java.util.HashMap;

public class AffirmRegisterModel implements AffirmRegisterContract.IAffirmMode {
    @Override
    public <T> void register(String phoneNum, String password, String affirm_password, INetCallBack<T> iNetCallBack) {

        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",phoneNum);
        commonParams.put("password",password);
        commonParams.put("affirm_password",affirm_password);

        for (String key: commonParams.keySet()) {
            Log.e("URTAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.USER_REGISTER,commonParams,iNetCallBack);
    }
}
