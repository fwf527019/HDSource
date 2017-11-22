package com.cdhd.utils;

import android.content.Context;
import android.widget.Toast;

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
}
