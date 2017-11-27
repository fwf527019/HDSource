package com.cdhd.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.cdhd.activity.LoginActivity;

/**
 * Created by Administrator on 2017/11/21.
 */

public class ToastExUtils {

    public static void showError(Context context,String e) {
        String ex="";
        if(e.contains("404")){
            ex="访问地址错误";
        }else if(e.contains("405")||e.contains("400")){
            ex="参数错误";
        }else if (e.contains("java.net.SocketTimeoutException")){
            ex="网络请求超时";
        }
        Toast.makeText(context, ex, Toast.LENGTH_SHORT).show();
    }

    public  static  void showMassegeInfo(Context context,String mag){
        String massege="";
        if(mag.contains("缺少验证口令")||mag.contains("无效的口令")){
            massege="登录信息失效,请重新登录";
            context.startActivity(new Intent(context, LoginActivity.class));
        }else {
            massege=mag;
        }
        Toast.makeText(context, massege, Toast.LENGTH_SHORT).show();
    }
}
