package com.cdhd.view;

import com.cdhd.response.LoginData;

/**
 * Created by Administrator on 2017/11/10.
 */

public interface LoginInterface {

    void showLoginResult(LoginData data);
    void error();

}
