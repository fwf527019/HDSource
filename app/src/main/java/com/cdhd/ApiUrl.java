package com.cdhd;

/**
 * Created by Administrator on 2017/11/10.
 */

public class ApiUrl {
    public static String SERVICE_URL = "http://192.168.1.14:803/";

    //登录
    public static String LOGIN = SERVICE_URL + "api/OriginApi/Login";
    //分页获取批次列表
    public static String BATHLIST = SERVICE_URL + "api/OriginApi/PagedBatchList";

    //获取批次详情
    public static String BATCHDETAIL = SERVICE_URL + "api/OriginApi/BatchDetail";
    //获取批次详情全部数据
    public static String BATCHDETAILALLDATA = SERVICE_URL + "api/OriginApi/GetBatchAllInfo";

}
