package com.cdhd.activity;


import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.cdhd.R;
import com.cdhd.utils.LoadingDialog;
import com.cdhd.utils.StatusBarUtil;


/**
 * Created by Administrator on 2017/2/20.
 */
public abstract class ActivityBase extends FragmentActivity {

    private static final String TAG = "ActivityBase";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
       // setContentView(R.layout.activity_bace);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
         //   getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
         //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }
        //设置软键盘不弹出
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(getContentViewResId());
        StatusBarUtil.setColor(this,getResources().getColor(R.color.main_green));
        //状态栏高度
     //   getRootView(this).setPadding(0,getStatusHeight(),0,0);
        initViews(); //初始化控件
        initDatas();//初始化数据

    }

    protected abstract int getContentViewResId();

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    protected abstract void initDatas();




    /**
     * 重写Back键
     *
     * @param keyCode
     * @param event
     * @return
     */

    /**
     * 获取状态栏高度
     */
    public  int getStatusHeight() {
        final Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int n = rect.top;
        if (n != 0) {
            return n;
        }
        try {
            final Class<?> forName = Class.forName("com.android.internal.R$dimen");
            n = getResources().getDimensionPixelSize(Integer.parseInt(forName.getField("status_bar_height").get(forName.newInstance()).toString()));
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex2) {
            ex2.printStackTrace();
        } catch (InstantiationException ex3) {
            ex3.printStackTrace();
        } catch (NumberFormatException ex4) {
            ex4.printStackTrace();
        } catch (IllegalArgumentException ex5) {
            ex5.printStackTrace();
        } catch (SecurityException ex6) {
            ex6.printStackTrace();
        } catch (NoSuchFieldException ex7) {
            ex7.printStackTrace();
        }
        return n;
    }


    /**
     * 设置全屏
     */
    protected void setFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }




    /**
     * 判断是否有网络
     */
    public boolean isNetWork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        return connectivityManager != null
                && connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isAvailable();
    }





    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * dip转像素
     */
    public static int dip2px(Context context,float dpValue){
        final float scale =context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }







    /**
     * 获取根布局
     * @param context
     * @return
     */
    public View getRootView(Activity context)
    {
        return ((ViewGroup)context.findViewById(android.R.id.content)).getChildAt(0);
    }





    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoading(this, msg, true);
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }

}

