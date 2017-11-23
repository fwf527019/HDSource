package com.cdht.Service;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.cdht.App;
import com.cdht.MainActivity;
import com.cdht.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/11/7.
 */

public class LocationService extends Service {
    private LocationManager locationManager;
    private String locationProvider;
    private Notification.Builder builder;
    private NotificationManager notificationManager;
    private Location mLocation;
    private long TIME = 10000;
    private Handler handler;
    private boolean isRun;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isRun) {
                handler.postDelayed(this, TIME);
                sendMassege(mLocation);
            }
        }


    };
    /**
     * Location Listener
     */
    LocationListener listerner = new LocationListener() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onLocationChanged(Location location) {
            //如果位置发生变化,重新显示
            //  showLocation(location);
            mLocation = location;
            if (location != null) {
                isRun = true;
            }
            builder.setContentText("纬度：" + location.getLatitude() +
                    "经度：" + location.getLongitude());
            notificationManager.notify(1100, builder.build());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        super.onCreate();
        //
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
         mLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        /**
         * 初始化通知栏
         */
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

         Intent intent= new Intent(this, MainActivity.class);
         builder.setContentIntent(PendingIntent.getActivity(this, 0, intent, 0)) // 设置PendingIntent
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
                .setContentTitle("下拉列表中的Title") // 设置下拉列表里的标题
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                .setContentText("要显示的内容") // 设置上下文内容
                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间
       Notification notification = builder.build() ;// 获取构建好的Notification
        notification.defaults = Notification.DEFAULT_SOUND ;//设置为默认的声音
        //设置通知默认效果
        notification.flags = Notification.FLAG_SHOW_LIGHTS;
        startForeground(1100, notification);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,3000,1f,listerner);
        handler.postDelayed(runnable, TIME); // 在初始化方法里.
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void sendMassege(Location mLocation) {
        com.alibaba.fastjson.JSONObject jsobj = new com.alibaba.fastjson.JSONObject();
        jsobj.put("lng", mLocation.getLongitude());
        jsobj.put("lat", mLocation.getLatitude());
        jsobj.put("GPSCode", "12345678944444");
        OkHttpUtils
                .postString()
                .content(jsobj.toJSONString())
                .url("192.168.1.55:8002/LogisticsPlatform/UpLoadTruckPosition")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("LocationService", response);
                    }
                });


    }
}
