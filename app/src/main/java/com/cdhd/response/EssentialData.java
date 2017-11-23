package com.cdhd.response;

/**
 * Created by Administrator on 2017/11/22.
 */

public class EssentialData {

    /**
     * Data : {"Guid":"c7aaf01a-f8f3-4189-8157-95a3a053f173","EssentialId":2,"BatchId":2,"ProductName":"第一个产品","ProductDesc":"产品描述描述描述","ProductImage":"/Images/Upload/201711/56d46acdbb0441d3b4584331240eea99.jpeg","IsDelete":0,"CreateManId":3,"CreateManName":"企业管理员","CreateTime":"2017-11-21 11:19:13","Batch":null}
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
         * Guid : c7aaf01a-f8f3-4189-8157-95a3a053f173
         * EssentialId : 2
         * BatchId : 2
         * ProductName : 第一个产品
         * ProductDesc : 产品描述描述描述
         * ProductImage : /Images/Upload/201711/56d46acdbb0441d3b4584331240eea99.jpeg
         * IsDelete : 0
         * CreateManId : 3
         * CreateManName : 企业管理员
         * CreateTime : 2017-11-21 11:19:13
         * Batch : null
         */

        private String Guid;
        private int EssentialId;
        private int BatchId;
        private String ProductName;
        private String ProductDesc;
        private String ProductImage;
        private int IsDelete;
        private int CreateManId;
        private String CreateManName;
        private String CreateTime;
        private String Batch;


        public String getGuid() {
            return Guid;
        }

        public void setGuid(String Guid) {
            this.Guid = Guid;
        }

        public int getEssentialId() {
            return EssentialId;
        }

        public void setEssentialId(int EssentialId) {
            this.EssentialId = EssentialId;
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

        public String getProductDesc() {
            return ProductDesc;
        }

        public void setProductDesc(String ProductDesc) {
            this.ProductDesc = ProductDesc;
        }

        public String getProductImage() {
            return ProductImage;
        }

        public void setProductImage(String ProductImage) {
            this.ProductImage = ProductImage;
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

        public String getBatch() {
            return Batch;
        }

        public void setBatch(String Batch) {
            this.Batch = Batch;
        }
    }


}
