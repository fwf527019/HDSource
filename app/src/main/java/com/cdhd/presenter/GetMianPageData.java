package com.cdhd.presenter;

import com.alibaba.fastjson.JSON;
import com.cdhd.ApiUrl;
import com.cdhd.response.MainListData;
import com.cdhd.utils.HttpRequst;
import com.cdhd.utils.MStringCallback;
import com.cdhd.view.MainPageInterface;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/11/17.
 */

public class GetMianPageData {

    private MainPageInterface mainPageInterface;


    public GetMianPageData(MainPageInterface mainPageInterface) {
        this.mainPageInterface = mainPageInterface;
    }
    /**
     * 分页获取批次列表。
     */
    public void getMainList(String keyword,int index,int size){
        Map<String,String> map=new HashMap<>();
        map.put("keyword",keyword);
        map.put("index", String.valueOf(index));
        map.put("size", String.valueOf(size));
        HttpRequst.CreatGetRequst(ApiUrl.BATHLIST, map, new MStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mainPageInterface.error();
            }
            @Override
            public void onResponse(String response, int id) {
                MainListData data=JSON.parseObject(response, MainListData.class);
                mainPageInterface.showData(data);
            }
        });

    }
}
