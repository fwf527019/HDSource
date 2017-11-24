package com.cdhd.view;

import com.cdhd.response.EnterpriseData;

/**
 * Created by Administrator on 2017/11/23.
 */

public interface EnterpriseInterface {
    void error(String e);
    void showData(EnterpriseData data);
}
