package com.cdhd.presenter;

import com.alibaba.fastjson.JSON;
import com.cdhd.ApiUrl;
import com.cdhd.response.EnterpriseData;
import com.cdhd.response.PlaceData;
import com.cdhd.utils.HttpRequst;
import com.cdhd.utils.MStringCallback;
import com.cdhd.view.EnterpriseInterface;
import com.cdhd.view.PlaceInterface;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/11/23.
 */

public class GetEnterpriseData {
    EnterpriseInterface enterpriseInterface;

    public GetEnterpriseData(EnterpriseInterface enterpriseInterface) {
        this.enterpriseInterface = enterpriseInterface;
    }

    public void getData(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("batchId", id);
        HttpRequst.CreatGetRequst(ApiUrl.GETENTERPRISEDATA, map, new MStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                enterpriseInterface.error(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                EnterpriseData data = JSON.parseObject(response, EnterpriseData.class);
                enterpriseInterface.showData(data);
            }
        });


    }
}
