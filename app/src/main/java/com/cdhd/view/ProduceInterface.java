package com.cdhd.view;

import com.cdhd.response.ProduceData;

/**
 * Created by Administrator on 2017/11/23.
 */

public interface ProduceInterface {
    void error(String e);

    void showProduceData(ProduceData data);

}
