package com.cdhd;

/**
 * Created by Administrator on 2017/11/10.
 */

public class ApiUrl {
    public static String SERVICE_URL = "http://192.168.1.14:803/";
    //  public static String SERVICE_URL = "http://192.168.1.56:8098/";
    //登录
    public static String LOGIN = SERVICE_URL + "api/OriginApi/Login";
    //分页获取批次列表
    public static String BATHLIST = SERVICE_URL + "api/OriginApi/PagedBatchList";

    //获取批次详情
    public static String BATCHDETAIL = SERVICE_URL + "api/OriginApi/BatchDetail";
    //获取批次详情全部数据
    public static String BATCHDETAILALLDATA = SERVICE_URL + "api/OriginApi/GetBatchAllInfo";
    //获取批次基本信息
    public static String GETESSENTIALDATA = SERVICE_URL + "api/OriginApi/GetEssentialInfo";
    //保存基本信息
    public static String SAVEESSENTIALDATA = SERVICE_URL + "api/OriginApi/SaveEssentialInfo";
    //获取生产信息
    public static String GETPRODUCEDATA = SERVICE_URL + "api/OriginApi/GetProductionInfo";
    //提交生产信息
    public static String SAVEPRODUCEDATA = SERVICE_URL + "api/OriginApi/SaveProductionInfo";

    //获取物流信息
    public static String GETLOGISTDATA = SERVICE_URL + "api/OriginApi/GetLogisticsInfo";
    //提交物流信息
    public static String SAVELOGISTDATA = SERVICE_URL + "api/OriginApi/SaveLogisticsInfo";

    //获取检测信息
    public static String GETTESTDATA = SERVICE_URL + "api/OriginApi/GetQualityInfo";
    //提交检测信息
    public static String SAVETESTTDATA = SERVICE_URL + "api/OriginApi/SaveQualityInfo";

    //获产地信息
    public static String GETPLACEDATA = SERVICE_URL + "api/OriginApi/GetProductionBaseInfo";
    //获企业信息
    public static String GETENTERPRISEDATA = SERVICE_URL + "api/OriginApi/GetProductionEnterpriseInfo";
}
