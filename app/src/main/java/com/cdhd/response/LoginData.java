package com.cdhd.response;

/**
 * Created by Administrator on 2017/11/10.
 */
public class LoginData {


    /**
     * Data : {"Guid":"11d88b13-b5d1-4d55-8edb-4f389e92683e","UserId":3,"Account":"app-admin","Password":"","Salt":"","UserType":1,"MerchantId":2,"UserName":"企业管理员","LinkPhone":"13012345678","UserStatus":1,"LastLoginTime":"2017-11-21 11:14:18","UserToken":"3a1af0039baa4652ac75bf555d73b491","IsDelete":0,"RoleId":4,"RoleName":"企业管理员","MerchantName":"APP测试企业"}
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
         * Guid : 11d88b13-b5d1-4d55-8edb-4f389e92683e
         * UserId : 3
         * Account : app-admin
         * Password :
         * Salt :
         * UserType : 1
         * MerchantId : 2
         * UserName : 企业管理员
         * LinkPhone : 13012345678
         * UserStatus : 1
         * LastLoginTime : 2017-11-21 11:14:18
         * UserToken : 3a1af0039baa4652ac75bf555d73b491
         * IsDelete : 0
         * RoleId : 4
         * RoleName : 企业管理员
         * MerchantName : APP测试企业
         */

        private String Guid;
        private String UserId;
        private String Account;
        private String Password;
        private String Salt;
        private int UserType;
        private int MerchantId;
        private String UserName;
        private String LinkPhone;
        private int UserStatus;
        private String LastLoginTime;
        private String UserToken;
        private int IsDelete;
        private int RoleId;
        private String RoleName;
        private String MerchantName;

        public String getGuid() {
            return Guid;
        }

        public void setGuid(String Guid) {
            this.Guid = Guid;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getAccount() {
            return Account;
        }

        public void setAccount(String Account) {
            this.Account = Account;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public String getSalt() {
            return Salt;
        }

        public void setSalt(String Salt) {
            this.Salt = Salt;
        }

        public int getUserType() {
            return UserType;
        }

        public void setUserType(int UserType) {
            this.UserType = UserType;
        }

        public int getMerchantId() {
            return MerchantId;
        }

        public void setMerchantId(int MerchantId) {
            this.MerchantId = MerchantId;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getLinkPhone() {
            return LinkPhone;
        }

        public void setLinkPhone(String LinkPhone) {
            this.LinkPhone = LinkPhone;
        }

        public int getUserStatus() {
            return UserStatus;
        }

        public void setUserStatus(int UserStatus) {
            this.UserStatus = UserStatus;
        }

        public String getLastLoginTime() {
            return LastLoginTime;
        }

        public void setLastLoginTime(String LastLoginTime) {
            this.LastLoginTime = LastLoginTime;
        }

        public String getUserToken() {
            return UserToken;
        }

        public void setUserToken(String UserToken) {
            this.UserToken = UserToken;
        }

        public int getIsDelete() {
            return IsDelete;
        }

        public void setIsDelete(int IsDelete) {
            this.IsDelete = IsDelete;
        }

        public int getRoleId() {
            return RoleId;
        }

        public void setRoleId(int RoleId) {
            this.RoleId = RoleId;
        }

        public String getRoleName() {
            return RoleName;
        }

        public void setRoleName(String RoleName) {
            this.RoleName = RoleName;
        }

        public String getMerchantName() {
            return MerchantName;
        }

        public void setMerchantName(String MerchantName) {
            this.MerchantName = MerchantName;
        }
    }
}
