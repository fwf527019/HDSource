package com.cdhd.response;

import java.util.List;

/**
 * Created by Administrator on 2017/11/23.
 */
public class EnterpriseData {

    /**
     * Data : {"Guid":"8dfa7950-20ff-47ff-9317-a70ca6f3338c","EnterpriseId":4,"RelationTable":"Basic_Product","RelationId":3,"EnterpriseName":"APP测试企业","MainProduct":"水果","LicenseCode":"123456789","LinkPhone":"130123456789","FaxNumber":"123456789","LinkAddress":"联系地址","Website":"www.website.com","EnterpriseAbstract":"概况","IsDelete":0,"CreateManId":3,"CreateManName":"企业管理员","CreateTime":"2017-11-23 11:31:09","Images":["/Images/Upload/201711/7779e2247e89469e857bd2ba7ecd56bb.jpeg","/Images/Upload/201711/cba739f2c0b14420ba4cccb1bce842a0.jpeg"]}
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
         * Guid : 8dfa7950-20ff-47ff-9317-a70ca6f3338c
         * EnterpriseId : 4
         * RelationTable : Basic_Product
         * RelationId : 3
         * EnterpriseName : APP测试企业
         * MainProduct : 水果
         * LicenseCode : 123456789
         * LinkPhone : 130123456789
         * FaxNumber : 123456789
         * LinkAddress : 联系地址
         * Website : www.website.com
         * EnterpriseAbstract : 概况
         * IsDelete : 0
         * CreateManId : 3
         * CreateManName : 企业管理员
         * CreateTime : 2017-11-23 11:31:09
         * Images : ["/Images/Upload/201711/7779e2247e89469e857bd2ba7ecd56bb.jpeg","/Images/Upload/201711/cba739f2c0b14420ba4cccb1bce842a0.jpeg"]
         */

        private String Guid;
        private int EnterpriseId;
        private String RelationTable;
        private int RelationId;
        private String EnterpriseName;
        private String MainProduct;
        private String LicenseCode;
        private String LinkPhone;
        private String FaxNumber;
        private String LinkAddress;
        private String Website;
        private String EnterpriseAbstract;
        private int IsDelete;
        private int CreateManId;
        private String CreateManName;
        private String CreateTime;
        private List<String> Images;

        public String getGuid() {
            return Guid;
        }

        public void setGuid(String Guid) {
            this.Guid = Guid;
        }

        public int getEnterpriseId() {
            return EnterpriseId;
        }

        public void setEnterpriseId(int EnterpriseId) {
            this.EnterpriseId = EnterpriseId;
        }

        public String getRelationTable() {
            return RelationTable;
        }

        public void setRelationTable(String RelationTable) {
            this.RelationTable = RelationTable;
        }

        public int getRelationId() {
            return RelationId;
        }

        public void setRelationId(int RelationId) {
            this.RelationId = RelationId;
        }

        public String getEnterpriseName() {
            return EnterpriseName;
        }

        public void setEnterpriseName(String EnterpriseName) {
            this.EnterpriseName = EnterpriseName;
        }

        public String getMainProduct() {
            return MainProduct;
        }

        public void setMainProduct(String MainProduct) {
            this.MainProduct = MainProduct;
        }

        public String getLicenseCode() {
            return LicenseCode;
        }

        public void setLicenseCode(String LicenseCode) {
            this.LicenseCode = LicenseCode;
        }

        public String getLinkPhone() {
            return LinkPhone;
        }

        public void setLinkPhone(String LinkPhone) {
            this.LinkPhone = LinkPhone;
        }

        public String getFaxNumber() {
            return FaxNumber;
        }

        public void setFaxNumber(String FaxNumber) {
            this.FaxNumber = FaxNumber;
        }

        public String getLinkAddress() {
            return LinkAddress;
        }

        public void setLinkAddress(String LinkAddress) {
            this.LinkAddress = LinkAddress;
        }

        public String getWebsite() {
            return Website;
        }

        public void setWebsite(String Website) {
            this.Website = Website;
        }

        public String getEnterpriseAbstract() {
            return EnterpriseAbstract;
        }

        public void setEnterpriseAbstract(String EnterpriseAbstract) {
            this.EnterpriseAbstract = EnterpriseAbstract;
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

        public List<String> getImages() {
            return Images;
        }

        public void setImages(List<String> Images) {
            this.Images = Images;
        }
    }
}
