package com.cdhd.response;

import java.util.List;

/**
 * Created by Administrator on 2017/11/23.
 */
public class PlaceData {

    /**
     * Data : {"Guid":"527c034b-a399-414a-961c-022844f72b01","BaseId":3,"RelationTable":"Basic_Product","RelationId":2,"BaseName":"基地","BaseAddress":"基地地址","BaseType":"类型描述","BaseArea":5000,"BuiltTime":"2017-11-01 00:00:00","SourceInfo":"情况就是这么个情况","IsDelete":0,"CreateManId":0,"CreateManName":null,"CreateTime":"2017-11-21 11:17:34","Images":["/Images/Upload/201711/5db7dfb25e3c4d88a0b3aac59de1a459.jpeg","/Images/Upload/201711/ab4d591c28174faab06e6869ae7af6cb.jpeg","/Images/Upload/201711/c643cd55fb3644208304ebc0983db8c6.jpeg"]}
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
         * Guid : 527c034b-a399-414a-961c-022844f72b01
         * BaseId : 3
         * RelationTable : Basic_Product
         * RelationId : 2
         * BaseName : 基地
         * BaseAddress : 基地地址
         * BaseType : 类型描述
         * BaseArea : 5000.0
         * BuiltTime : 2017-11-01 00:00:00
         * SourceInfo : 情况就是这么个情况
         * IsDelete : 0
         * CreateManId : 0
         * CreateManName : null
         * CreateTime : 2017-11-21 11:17:34
         * Images : ["/Images/Upload/201711/5db7dfb25e3c4d88a0b3aac59de1a459.jpeg","/Images/Upload/201711/ab4d591c28174faab06e6869ae7af6cb.jpeg","/Images/Upload/201711/c643cd55fb3644208304ebc0983db8c6.jpeg"]
         */

        private String Guid;
        private int BaseId;
        private String RelationTable;
        private int RelationId;
        private String BaseName;
        private String BaseAddress;
        private String BaseType;
        private String BaseArea;
        private String BuiltTime;
        private String SourceInfo;
        private int IsDelete;
        private int CreateManId;
        private Object CreateManName;
        private String CreateTime;
        private List<String> Images;

        public String getGuid() {
            return Guid;
        }

        public void setGuid(String Guid) {
            this.Guid = Guid;
        }

        public int getBaseId() {
            return BaseId;
        }

        public void setBaseId(int BaseId) {
            this.BaseId = BaseId;
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

        public String getBaseName() {
            return BaseName;
        }

        public void setBaseName(String BaseName) {
            this.BaseName = BaseName;
        }

        public String getBaseAddress() {
            return BaseAddress;
        }

        public void setBaseAddress(String BaseAddress) {
            this.BaseAddress = BaseAddress;
        }

        public String getBaseType() {
            return BaseType;
        }

        public void setBaseType(String BaseType) {
            this.BaseType = BaseType;
        }

        public String getBaseArea() {
            return BaseArea;
        }

        public void setBaseArea(String BaseArea) {
            this.BaseArea = BaseArea;
        }

        public String getBuiltTime() {
            return BuiltTime;
        }

        public void setBuiltTime(String BuiltTime) {
            this.BuiltTime = BuiltTime;
        }

        public String getSourceInfo() {
            return SourceInfo;
        }

        public void setSourceInfo(String SourceInfo) {
            this.SourceInfo = SourceInfo;
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

        public Object getCreateManName() {
            return CreateManName;
        }

        public void setCreateManName(Object CreateManName) {
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
