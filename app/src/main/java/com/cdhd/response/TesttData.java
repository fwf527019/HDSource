package com.cdhd.response;

import java.util.List;

/**
 * Created by Administrator on 2017/11/23.
 */
public class TesttData {

    /**
     * Data : {"Guid":"c2ca8031-9c13-4fe3-810b-42bf2e9783ee","QualityId":2,"BatchId":2,"QualityTime":"2017-11-01 00:00:00","QualityOrganization":"优质机构","QualityMan":"人员名称","QualityStandard":"《国家一级标准》","IsDelete":0,"CreateManId":3,"CreateManName":"企业管理员","CreateTime":"2017-11-21 11:19:13","Batch":null,"Images":["/Images/Upload/201711/a2b0291a36854a87ba5eb17fb04c721e.jpeg","/Images/Upload/201711/93ca943c690c4210ab86f1c357bec77a.jpeg"]}
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
         * Guid : c2ca8031-9c13-4fe3-810b-42bf2e9783ee
         * QualityId : 2
         * BatchId : 2
         * QualityTime : 2017-11-01 00:00:00
         * QualityOrganization : 优质机构
         * QualityMan : 人员名称
         * QualityStandard : 《国家一级标准》
         * IsDelete : 0
         * CreateManId : 3
         * CreateManName : 企业管理员
         * CreateTime : 2017-11-21 11:19:13
         * Batch : null
         * Images : ["/Images/Upload/201711/a2b0291a36854a87ba5eb17fb04c721e.jpeg","/Images/Upload/201711/93ca943c690c4210ab86f1c357bec77a.jpeg"]
         */

        private String Guid;
        private int QualityId;
        private int BatchId;
        private String QualityTime;
        private String QualityOrganization;
        private String QualityMan;
        private String QualityStandard;
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

        public int getQualityId() {
            return QualityId;
        }

        public void setQualityId(int QualityId) {
            this.QualityId = QualityId;
        }

        public int getBatchId() {
            return BatchId;
        }

        public void setBatchId(int BatchId) {
            this.BatchId = BatchId;
        }

        public String getQualityTime() {
            return QualityTime;
        }

        public void setQualityTime(String QualityTime) {
            this.QualityTime = QualityTime;
        }

        public String getQualityOrganization() {
            return QualityOrganization;
        }

        public void setQualityOrganization(String QualityOrganization) {
            this.QualityOrganization = QualityOrganization;
        }

        public String getQualityMan() {
            return QualityMan;
        }

        public void setQualityMan(String QualityMan) {
            this.QualityMan = QualityMan;
        }

        public String getQualityStandard() {
            return QualityStandard;
        }

        public void setQualityStandard(String QualityStandard) {
            this.QualityStandard = QualityStandard;
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
