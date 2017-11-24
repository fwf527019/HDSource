package com.cdhd.response;

import java.util.List;

/**
 * Created by Administrator on 2017/11/23.
 */

public class ProduceData {


    /**
     * Data : {"Guid":"b23ed5c2-2014-4aba-bf2a-2987ab91a0a2","ProductionId":3,"BatchId":3,"ProductName":"神农架野香蕉","BrandName":"未知品牌","ProductGrade":"登峰造极","ProductionEnterprise":"纯天然","OriginCode":"6004980001171123001","BatchCode":"004980001171123001","ProductionDate":"2017-11-01 00:00:00","ExpireDate":"500年","IsDelete":0,"CreateManId":3,"CreateManName":"企业管理员","CreateTime":"2017-11-23 11:33:10","Batch":null,"Images":["/Images/Upload/201711/38ca4965616f4bdc8c49c0d8fc62601b.jpeg","/Images/Upload/201711/31462093e5a04c5ea64582a1339fe7d5.jpeg","/Images/Upload/201711/8f05db62da134baaa0641e70fd40f607.jpeg"]}
     * Message :
     * Success : true
     */

    private DataBean Data;
    private String Message;
    private boolean Success;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public static class DataBean {
        /**
         * Guid : b23ed5c2-2014-4aba-bf2a-2987ab91a0a2
         * ProductionId : 3
         * BatchId : 3
         * ProductName : 神农架野香蕉
         * BrandName : 未知品牌
         * ProductGrade : 登峰造极
         * ProductionEnterprise : 纯天然
         * OriginCode : 6004980001171123001
         * BatchCode : 004980001171123001
         * ProductionDate : 2017-11-01 00:00:00
         * ExpireDate : 500年
         * IsDelete : 0
         * CreateManId : 3
         * CreateManName : 企业管理员
         * CreateTime : 2017-11-23 11:33:10
         * Batch : null
         * Images : ["/Images/Upload/201711/38ca4965616f4bdc8c49c0d8fc62601b.jpeg","/Images/Upload/201711/31462093e5a04c5ea64582a1339fe7d5.jpeg","/Images/Upload/201711/8f05db62da134baaa0641e70fd40f607.jpeg"]
         */

        private String Guid;
        private int ProductionId;
        private int BatchId;
        private String ProductName;
        private String BrandName;
        private String ProductGrade;
        private String ProductionEnterprise;
        private String OriginCode;
        private String BatchCode;
        private String ProductionDate;
        private String ExpireDate;
        private int IsDelete;
        private int CreateManId;
        private String CreateManName;
        private String CreateTime;
        private Object Batch;
        private List<String> Images;

        public String getGuid() {
            return Guid;
        }

        public void setGuid(String Guid) {
            this.Guid = Guid;
        }

        public int getProductionId() {
            return ProductionId;
        }

        public void setProductionId(int ProductionId) {
            this.ProductionId = ProductionId;
        }

        public int getBatchId() {
            return BatchId;
        }

        public void setBatchId(int BatchId) {
            this.BatchId = BatchId;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public String getBrandName() {
            return BrandName;
        }

        public void setBrandName(String BrandName) {
            this.BrandName = BrandName;
        }

        public String getProductGrade() {
            return ProductGrade;
        }

        public void setProductGrade(String ProductGrade) {
            this.ProductGrade = ProductGrade;
        }

        public String getProductionEnterprise() {
            return ProductionEnterprise;
        }

        public void setProductionEnterprise(String ProductionEnterprise) {
            this.ProductionEnterprise = ProductionEnterprise;
        }

        public String getOriginCode() {
            return OriginCode;
        }

        public void setOriginCode(String OriginCode) {
            this.OriginCode = OriginCode;
        }

        public String getBatchCode() {
            return BatchCode;
        }

        public void setBatchCode(String BatchCode) {
            this.BatchCode = BatchCode;
        }

        public String getProductionDate() {
            return ProductionDate;
        }

        public void setProductionDate(String ProductionDate) {
            this.ProductionDate = ProductionDate;
        }

        public String getExpireDate() {
            return ExpireDate;
        }

        public void setExpireDate(String ExpireDate) {
            this.ExpireDate = ExpireDate;
        }

        public int getIsDelete() {
            return IsDelete;
        }

        public void setIsDelete(int IsDelete) {
            this.IsDelete = IsDelete;
        }

        public int getCreateManId() {
            return CreateManId;
        }

        public void setCreateManId(int CreateManId) {
            this.CreateManId = CreateManId;
        }

        public String getCreateManName() {
            return CreateManName;
        }

        public void setCreateManName(String CreateManName) {
            this.CreateManName = CreateManName;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public Object getBatch() {
            return Batch;
        }

        public void setBatch(Object Batch) {
            this.Batch = Batch;
        }

        public List<String> getImages() {
            return Images;
        }

        public void setImages(List<String> Images) {
            this.Images = Images;
        }
    }
}
