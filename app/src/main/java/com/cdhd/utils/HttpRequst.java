package com.cdhd.utils;

import android.util.Log;

import com.cdhd.App;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/11/10.
 */

public class HttpRequst {

    /**
     * get 请求
     *
     * @param map
     * @param callback
     */
    public static void CreatGetRequst(String url, Map<String, String> map, MStringCallback callback) {
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("OriginToken", App.getOriginToken());
        //遍历map中的值

        for (String value : map2.values()) {
            Log.d("HtttpRequest", ("Value = " + value));

        }
        OkHttpUtils
                .get()
                .headers(map2)
                .params(map)
                .url(url)
                .build()
                .execute(callback);
    }

    /**
     * post 请求
     *
     * @param st
     * @param callback
     */
    public static void CreatPostRequst(String url, String st, MStringCallback callback) {
        OkHttpUtils
                .postString()
                .addHeader("OriginToken", App.getOriginToken())
                .content(st)
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(callback);
    }

}
