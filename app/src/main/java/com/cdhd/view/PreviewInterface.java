package com.cdhd.view;

import com.cdhd.response.BatchAllData;

/**
 * Created by Administrator on 2017/11/21.
 */

public interface PreviewInterface {
    void showBaseInfo(BatchAllData.DataBean.EssentialBean data);
    void showProduceInfo(BatchAllData.DataBean.ProdutionBean data);
    void showTestInfo(BatchAllData.DataBean.QualityBean data);
    void showPlaceInfo(BatchAllData.DataBean.BaseBean data);
    void showEnterpriseInfo(BatchAllData.DataBean.EnterpriseBean data);
    void showLogistInfo(BatchAllData.DataBean.LogistiscsBean data);
    void error(String e);
}
