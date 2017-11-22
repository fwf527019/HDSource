package com.cdhd.response;

/**
 * Created by Administrator on 2017/11/21.
 */

public class BatchDetailData {


    /**
     * Data : {"Guid":"b872b19e-3441-4978-a910-ea3cd6089a97","BatchId":2,"MerchantId":2,"ProductId":2,"OriginCode":"201711211118250000111","BatchCode":"201711211118250000111","CreateTime":"2017-11-21 11:19:13","ReviewStatus":0,"ReviewOpinion":null,"BatchStatus":1,"QrCodeUrl":"/Images/QrCode/201711/c8fb7ccdd0654177bf7a23e853fa4424.png","IsRecommendToHome":null,"IsDelete":0,"Product":{"Guid":"8f743cec-35ee-4592-8ec7-0ea7de15ef08","ProductId":2,"MerchantId":2,"ProductCode":"PRO20171121001","ProductName":"第一个产品","ProductDesc":"产品描述描述描述","ProductImage":"/Images/Upload/201711/56d46acdbb0441d3b4584331240eea99.jpeg","CreateTime":"2017-11-21 11:17:34","ProductionName":"第一个产品","ProductionBrandName":"优质品牌","ProdutionGrade":"优质等级","ProdutionEnterprise":"渣渣企业","ProductStatus":1,"IsDelete":0,"Merchant":null}}
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
         * Guid : b872b19e-3441-4978-a910-ea3cd6089a97
         * BatchId : 2
         * MerchantId : 2
         * ProductId : 2
         * OriginCode : 201711211118250000111
         * BatchCode : 201711211118250000111
         * CreateTime : 2017-11-21 11:19:13
         * ReviewStatus : 0
         * ReviewOpinion : null
         * BatchStatus : 1
         * QrCodeUrl : /Images/QrCode/201711/c8fb7ccdd0654177bf7a23e853fa4424.png
         * IsRecommendToHome : null
         * IsDelete : 0
         * Product : {"Guid":"8f743cec-35ee-4592-8ec7-0ea7de15ef08","ProductId":2,"MerchantId":2,"ProductCode":"PRO20171121001","ProductName":"第一个产品","ProductDesc":"产品描述描述描述","ProductImage":"/Images/Upload/201711/56d46acdbb0441d3b4584331240eea99.jpeg","CreateTime":"2017-11-21 11:17:34","ProductionName":"第一个产品","ProductionBrandName":"优质品牌","ProdutionGrade":"优质等级","ProdutionEnterprise":"渣渣企业","ProductStatus":1,"IsDelete":0,"Merchant":null}
         */

        private String Guid;
        private int BatchId;
        private int MerchantId;
        private int ProductId;
        private String OriginCode;
        private String BatchCode;
        private String CreateTime;
        private int ReviewStatus;
        private Object ReviewOpinion;
        private int BatchStatus;
        private String QrCodeUrl;
        private Object IsRecommendToHome;
        private int IsDelete;
        private ProductBean Product;

        public String getGuid() {
            return Guid;
        }

        public void setGuid(String Guid) {
            this.Guid = Guid;
        }

        public int getBatchId() {
            return BatchId;
        }

        public void setBatchId(int BatchId) {
            this.BatchId = BatchId;
        }

        public int getMerchantId() {
            return MerchantId;
        }

        public void setMerchantId(int MerchantId) {
            this.MerchantId = MerchantId;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
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

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getReviewStatus() {
            return ReviewStatus;
        }

        public void setReviewStatus(int ReviewStatus) {
            this.ReviewStatus = ReviewStatus;
        }

        public Object getReviewOpinion() {
            return ReviewOpinion;
        }

        public void setReviewOpinion(Object ReviewOpinion) {
            this.ReviewOpinion = ReviewOpinion;
        }

        public int getBatchStatus() {
            return BatchStatus;
        }

        public void setBatchStatus(int BatchStatus) {
            this.BatchStatus = BatchStatus;
        }

        public String getQrCodeUrl() {
            return QrCodeUrl;
        }

        public void setQrCodeUrl(String QrCodeUrl) {
            this.QrCodeUrl = QrCodeUrl;
        }

        public Object getIsRecommendToHome() {
            return IsRecommendToHome;
        }

        public void setIsRecommendToHome(Object IsRecommendToHome) {
            this.IsRecommendToHome = IsRecommendToHome;
        }

        public int getIsDelete() {
            return IsDelete;
        }

        public void setIsDelete(int IsDelete) {
            this.IsDelete = IsDelete;
        }

        public ProductBean getProduct() {
            return Product;
        }

        public void setProduct(ProductBean Product) {
            this.Product = Product;
        }

        public static class ProductBean {
            /**
             * Guid : 8f743cec-35ee-4592-8ec7-0ea7de15ef08
             * ProductId : 2
             * MerchantId : 2
             * ProductCode : PRO20171121001
             * ProductName : 第一个产品
             * ProductDesc : 产品描述描述描述
             * ProductImage : /Images/Upload/201711/56d46acdbb0441d3b4584331240eea99.jpeg
             * CreateTime : 2017-11-21 11:17:34
             * ProductionName : 第一个产品
             * ProductionBrandName : 优质品牌
             * ProdutionGrade : 优质等级
             * ProdutionEnterprise : 渣渣企业
             * ProductStatus : 1
             * IsDelete : 0
             * Merchant : null
             */

            private String Guid;
            private int ProductId;
            private int MerchantId;
            private String ProductCode;
            private String ProductName;
            private String ProductDesc;
            private String ProductImage;
            private String CreateTime;
            private String ProductionName;
            private String ProductionBrandName;
            private String ProdutionGrade;
            private String ProdutionEnterprise;
            private int ProductStatus;
            private int IsDelete;
            private Object Merchant;

            public String getGuid() {
                return Guid;
            }

            public void setGuid(String Guid) {
                this.Guid = Guid;
            }

            public int getProductId() {
                return ProductId;
            }

            public void setProductId(int ProductId) {
                this.ProductId = ProductId;
            }

            public int getMerchantId() {
                return MerchantId;
            }

            public void setMerchantId(int MerchantId) {
                this.MerchantId = MerchantId;
            }

            public String getProductCode() {
                return ProductCode;
            }

            public void setProductCode(String ProductCode) {
                this.ProductCode = ProductCode;
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

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getProductionName() {
                return ProductionName;
            }

            public void setProductionName(String ProductionName) {
                this.ProductionName = ProductionName;
            }

            public String getProductionBrandName() {
                return ProductionBrandName;
            }

            public void setProductionBrandName(String ProductionBrandName) {
                this.ProductionBrandName = ProductionBrandName;
            }

            public String getProdutionGrade() {
                return ProdutionGrade;
            }

            public void setProdutionGrade(String ProdutionGrade) {
                this.ProdutionGrade = ProdutionGrade;
            }

            public String getProdutionEnterprise() {
                return ProdutionEnterprise;
            }

            public void setProdutionEnterprise(String ProdutionEnterprise) {
                this.ProdutionEnterprise = ProdutionEnterprise;
            }

            public int getProductStatus() {
                return ProductStatus;
            }

            public void setProductStatus(int ProductStatus) {
                this.ProductStatus = ProductStatus;
            }

            public int getIsDelete() {
                return IsDelete;
            }

            public void setIsDelete(int IsDelete) {
                this.IsDelete = IsDelete;
            }

            public Object getMerchant() {
                return Merchant;
            }

            public void setMerchant(Object Merchant) {
                this.Merchant = Merchant;
            }
        }
    }
}
