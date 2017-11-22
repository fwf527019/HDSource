package com.cdhd.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhd.App;
import com.cdhd.R;
import com.cdhd.presenter.GetLoginData;
import com.cdhd.response.LoginData;
import com.cdhd.view.LoginInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/8.
 */

public class LoginActivity extends ActivityBase implements LoginInterface {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titail)
    TextView titail;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.logn_bt)
    Button lognBt;
    private GetLoginData getLoginData;
    private String userName;
    private String psw;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        getLoginData = new GetLoginData(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        titail.setText("登录");
    }

    @OnClick({R.id.back, R.id.logn_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.logn_bt:
                if (username.getText().toString().trim().length() != 0) {
                    userName = username.getText().toString().trim();
                } else {
                    Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
                }
                if (password.getText().toString().trim().length() != 0) {
                    psw = password.getText().toString().trim();
                } else {
                    Toast.makeText(this, "请输入登录密码", Toast.LENGTH_SHORT).show();
                }
                gotoLogin();
                break;
        }
    }

    private void gotoLogin() {
        getLoginData.GotoLogin(userName, psw, true, false);


    }

    @Override
    public void showLoginResult(LoginData data) {
        if (data.isSuccess()) {
            App.setOriginToken(data.getData().getUserToken());
            //保存用户信息
            SharedPreferences sp = getSharedPreferences("user_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("name", data.getData().getUserName());
            editor.putString("RoleName", data.getData().getRoleName());
            editor.putString("UserToken", data.getData().getUserToken());
            editor.putString("psw", psw);
            editor.commit();
            startActivity(new Intent(this, MainActivity.class));
        }else {
            Toast.makeText(this, data.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void error() {
        Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }
}
