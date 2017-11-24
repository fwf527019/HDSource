package com.cdhd.presenter;

import com.alibaba.fastjson.JSON;
import com.cdhd.ApiUrl;
import com.cdhd.response.LogistData;
import com.cdhd.response.TesttData;
import com.cdhd.utils.HttpRequst;
import com.cdhd.utils.MStringCallback;
import com.cdhd.view.LogistInterface;
import com.cdhd.view.TestInterface;

import junit.framework.Test;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/11/23.
 */

public class GetTestData {
    TestInterface testInterface;

    public GetTestData(TestInterface testInterface) {
        this.testInterface = testInterface;
    }

    public void getData(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("batchId", id);
        HttpRequst.CreatGetRequst(ApiUrl.GETTESTDATA, map, new MStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                testInterface.error(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                TesttData data= JSON.parseObject(response,TesttData.class);
                testInterface.showTestInfo(data);
            }
        });


    }
}
