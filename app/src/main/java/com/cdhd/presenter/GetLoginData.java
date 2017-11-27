package com.cdhd.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.cdhd.ApiUrl;
import com.cdhd.App;
import com.cdhd.response.LoginData;
import com.cdhd.utils.LogUtils;
import com.cdhd.utils.MStringCallback;
import com.cdhd.view.LoginInterface;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/11/10.
 */

public class GetLoginData {

    LoginInterface loginInterface;

    public GetLoginData(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
    }

    public void GotoLogin(String Account, String Password, boolean RememberPwd, boolean IsMerchant) {
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("Account", Account);
        jsonObject.put("Password", Password);
        jsonObject.put("RememberPwd", RememberPwd);
        jsonObject.put("IsMerchant", IsMerchant);

        String st = jsonObject.toJSONString();
        OkHttpUtils
                .postString()
             //   .addHeader("OriginToken", App.getOriginToken())
                .content(st)
                .url(ApiUrl.LOGIN)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new MStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        loginInterface.error();
                        Log.d("GetLoginData", "e:" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("GetLoginData", response);
                        LoginData data = JSON.parseObject(response, LoginData.class);
                        loginInterface.showLoginResult(data);
                    }
                });


    }
}
