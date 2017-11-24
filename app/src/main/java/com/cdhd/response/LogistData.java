package com.cdhd.response;

import java.util.List;

/**
 * Created by Administrator on 2017/11/23.
 */

public class LogistData {


    /**
     * Data : {"Guid":"ed28fb51-5c68-421b-b428-ac5a6859c6a2","LogisticsId":3,"BatchId":3,"StorageTime":"2017-11-01 00:00:00","StorageNum":5000,"StorageMan":"那个谁","OutputTime":"2017-11-23 00:00:00","OutputNum":5000,"OutputMan":"这个谁","IsDelete":0,"CreateManId":3,"CreateManName":"企业管理员","CreateTime":"2017-11-23 11:33:10","Batch":null,"Images":["/Images/Upload/201711/bb794acb1cb1403aa90aeee49905fe3f.jpeg","/Images/Upload/201711/1ce2d329ecf84a89988e16334ba61c99.jpeg"]}
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
         * Guid : ed28fb51-5c68-421b-b428-ac5a6859c6a2
         * LogisticsId : 3
         * BatchId : 3
         * StorageTime : 2017-11-01 00:00:00
         * StorageNum : 5000
         * StorageMan : 那个谁
         * OutputTime : 2017-11-23 00:00:00
         * OutputNum : 5000
         * OutputMan : 这个谁
         * IsDelete : 0
         * CreateManId : 3
         * CreateManName : 企业管理员
         * CreateTime : 2017-11-23 11:33:10
         * Batch : null
         * Images : ["/Images/Upload/201711/bb794acb1cb1403aa90aeee49905fe3f.jpeg","/Images/Upload/201711/1ce2d329ecf84a89988e16334ba61c99.jpeg"]
         */

        private String Guid;
        private int LogisticsId;
        private int BatchId;
        private String StorageTime;
        private String StorageNum;
        private String StorageMan;
        private String OutputTime;
        private String OutputNum;
        private String OutputMan;
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

        public int getLogisticsId() {
            return LogisticsId;
        }

        public void setLogisticsId(int LogisticsId) {
            this.LogisticsId = LogisticsId;
        }

        public int getBatchId() {
            return BatchId;
        }

        public void setBatchId(int BatchId) {
            this.BatchId = BatchId;
        }

        public String getStorageTime() {
            return StorageTime;
        }

        public void setStorageTime(String StorageTime) {
            this.StorageTime = StorageTime;
        }

        public String getStorageNum() {
            return StorageNum;
        }

        public void setStorageNum(String StorageNum) {
            this.StorageNum = StorageNum;
        }

        public String getStorageMan() {
            return StorageMan;
        }

        public void setStorageMan(String StorageMan) {
            this.StorageMan = StorageMan;
        }

        public String getOutputTime() {
            return OutputTime;
        }

        public void setOutputTime(String OutputTime) {
            this.OutputTime = OutputTime;
        }

        public String getOutputNum() {
            return OutputNum;
        }

        public void setOutputNum(String OutputNum) {
            this.OutputNum = OutputNum;
        }

        public String getOutputMan() {
            return OutputMan;
        }

        public void setOutputMan(String OutputMan) {
            this.OutputMan = OutputMan;
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
