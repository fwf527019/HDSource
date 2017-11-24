package com.cdhd.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdhd.ApiUrl;
import com.cdhd.response.ProduceData;
import com.cdhd.utils.HttpRequst;
import com.cdhd.utils.MStringCallback;
import com.cdhd.view.ProduceInterface;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/11/23.
 */

public class GetProduceData {

    private ProduceInterface produceInterface;

    public GetProduceData(ProduceInterface produceInterface) {
        this.produceInterface = produceInterface;
    }

    public void GetData(String batchId) {

        Map<String,String> map=new HashMap<>();
        map.put("batchId",batchId);
        HttpRequst.CreatGetRequst(ApiUrl.GETPRODUCEDATA, map, new MStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                produceInterface.equals(e.toString());
        }

            @Override
            public void onResponse(String response, int id) {
                Log.d("GetProduceData", response);
                ProduceData data= JSON.parseObject(response,ProduceData.class);
                produceInterface.showProduceData(data);
            }
        });


    }
}
