package com.example.seethewayproject.home.model;

import android.util.Log;

import com.example.seethewayproject.base.BaseModel;
import com.example.seethewayproject.home.contract.VideoContract;
import com.example.seethewayproject.net.INetCallBack;
import com.example.seethewayproject.net.NetWorkFactory;
import com.example.seethewayproject.net.api.URLConstants;
import com.example.seethewayproject.utils.ParamsUtils;

import java.util.HashMap;

public class VideoModel implements VideoContract.IVideoModel {

    @Override
    public <T> void getVideoData(INetCallBack<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
//        commonParams.put("id","0");
        commonParams.put("start","0");
        commonParams.put("number ","0");
        commonParams.put("point_time","0");
//        此处  -- 登录以后，  需要修改

        for (String key: commonParams.keySet()) {
            Log.e("VTAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().get(URLConstants.VEDIO_LIST,commonParams,iNetCallBack);
    }
}
