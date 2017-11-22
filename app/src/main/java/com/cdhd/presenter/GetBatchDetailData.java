package com.cdhd.presenter;

import com.alibaba.fastjson.JSON;
import com.cdhd.ApiUrl;
import com.cdhd.response.BatchDetailData;
import com.cdhd.utils.HttpRequst;
import com.cdhd.utils.MStringCallback;
import com.cdhd.view.BatchDetailInterface;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/11/21.
 */

public class GetBatchDetailData {

    private BatchDetailInterface batchDetailInterface;

    public GetBatchDetailData(BatchDetailInterface batchDetailInterface) {
        this.batchDetailInterface = batchDetailInterface;
    }

    public void getDetailData(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("batchId", id);
        HttpRequst.CreatGetRequst(ApiUrl.BATCHDETAIL, map, new MStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                batchDetailInterface.error(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                BatchDetailData data = JSON.parseObject(response, BatchDetailData.class);
                batchDetailInterface.showBatchData(data);
            }
        });


    }

}
