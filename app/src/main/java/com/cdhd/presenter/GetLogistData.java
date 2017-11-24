package com.cdhd.presenter;

import com.alibaba.fastjson.JSON;
import com.cdhd.ApiUrl;
import com.cdhd.response.LogistData;
import com.cdhd.utils.HttpRequst;
import com.cdhd.utils.MStringCallback;
import com.cdhd.utils.ToastExUtils;
import com.cdhd.view.LogistInterface;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/11/23.
 */

public class GetLogistData {
    LogistInterface logistInterface;

    public GetLogistData(LogistInterface logistInterface) {
        this.logistInterface = logistInterface;
    }

    public void getData(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("batchId", id);
        HttpRequst.CreatGetRequst(ApiUrl.GETLOGISTDATA, map, new MStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
              logistInterface.error(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                LogistData data= JSON.parseObject(response,LogistData.class);
                logistInterface.showLogistInfo(data);
            }
        });


    }
}
