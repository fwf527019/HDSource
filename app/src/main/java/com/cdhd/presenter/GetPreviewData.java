package com.cdhd.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cdhd.ApiUrl;
import com.cdhd.response.BatchAllData;
import com.cdhd.response.BatchDetailData;
import com.cdhd.utils.HttpRequst;
import com.cdhd.utils.MStringCallback;
import com.cdhd.view.PreviewInterface;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/11/21.
 */

public class GetPreviewData {
    private PreviewInterface previewInterface;

    public GetPreviewData(PreviewInterface previewInterface) {
        this.previewInterface = previewInterface;
    }

    public void GetAllData(String batchId, String produceId) {
        Map<String, String> map = new HashMap<>();
        map.put("productId", produceId);
        map.put("batchId", batchId);
        HttpRequst.CreatGetRequst(ApiUrl.BATCHDETAILALLDATA, map, new MStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                previewInterface.error(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                BatchAllData data=JSON.parseObject(response, BatchAllData.class);
                previewInterface.showBaseInfo(data.getData().getEssential());
                previewInterface.showProduceInfo(data.getData().getProdution());
                previewInterface.showPlaceInfo(data.getData().getBase());
                previewInterface.showEnterpriseInfo(data.getData().getEnterprise());
                previewInterface.showTestInfo(data.getData().getQuality());

            }
        });
    }


}
