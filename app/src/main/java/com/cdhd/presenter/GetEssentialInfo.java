package com.cdhd.presenter;

import com.alibaba.fastjson.JSON;
import com.cdhd.ApiUrl;
import com.cdhd.response.EssentialData;
import com.cdhd.utils.HttpRequst;
import com.cdhd.utils.MStringCallback;
import com.cdhd.view.EssentialInfoInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/11/22.
 */

public class GetEssentialInfo {

private EssentialInfoInterface essentialInfoInterface;

    public GetEssentialInfo(EssentialInfoInterface essentialInfoInterface) {
        this.essentialInfoInterface = essentialInfoInterface;
    }

    public void GetEssentialData(String batchId){
        Map<String,String> map=new HashMap<>();
        map.put("batchId",batchId);
        HttpRequst.CreatGetRequst(ApiUrl.GETESSENTIALDATA, map, new MStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                essentialInfoInterface.error(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                EssentialData data= JSON.parseObject(response,EssentialData.class);
                essentialInfoInterface.showEssentialInfo(data);
            }
        });


    }
}
