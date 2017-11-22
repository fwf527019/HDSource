package com.cdhd.view;

import com.cdhd.response.BatchDetailData;

/**
 * Created by Administrator on 2017/11/21.
 */

public interface BatchDetailInterface {
    void showBatchData(BatchDetailData data);
    void error(String e);
}
