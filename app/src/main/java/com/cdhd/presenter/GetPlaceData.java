package com.cdhd.presenter;

import com.alibaba.fastjson.JSON;
import com.cdhd.ApiUrl;
import com.cdhd.response.LogistData;
import com.cdhd.response.PlaceData;
import com.cdhd.utils.HttpRequst;
import com.cdhd.utils.MStringCallback;
import com.cdhd.view.LogistInterface;
import com.cdhd.view.PlaceInterface;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/11/23.
 */

public class GetPlaceData {
    PlaceInterface placeInterface;

    public GetPlaceData(PlaceInterface placeInterface) {
        this.placeInterface = placeInterface;
    }

    public void getData(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("batchId", id);
        HttpRequst.CreatGetRequst(ApiUrl.GETPLACEDATA, map, new MStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                placeInterface.error(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                PlaceData data = JSON.parseObject(response, PlaceData.class);
                placeInterface.showPlaceInfo(data);
            }
        });


    }
}
