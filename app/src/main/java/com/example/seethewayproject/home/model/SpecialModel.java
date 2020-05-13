package com.example.seethewayproject.home.model;

import android.util.Log;

import com.example.seethewayproject.home.contract.SpecialContract;
import com.example.seethewayproject.net.INetCallBack;
import com.example.seethewayproject.net.NetWorkFactory;
import com.example.seethewayproject.net.api.URLConstants;
import com.example.seethewayproject.utils.ParamsUtils;

import java.util.HashMap;

public class SpecialModel implements SpecialContract.ISpecialModel {

    @Override
    public <T> void getSpecialList(int start, int number, int point_time, INetCallBack<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
//        commonParams.put("id",id);
        commonParams.put("start",start+"");
        commonParams.put("number ",number+"");
        commonParams.put("point_time",point_time+"");
//        此处  -- 登录以后，  需要修改

        for (String key: commonParams.keySet()) {
            Log.e("TAG","Skey="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().get(URLConstants.SPECIAL_LIST,commonParams,iNetCallBack);
    }
}
