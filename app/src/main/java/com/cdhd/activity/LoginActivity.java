package com.cdhd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
                gotoLogin();
                break;
        }
    }

    private void gotoLogin() {
        getLoginData.GotoLogin("13688479997","123456",true,false);


    }

    @Override
    public void showLoginResult(LoginData data) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void error() {
        Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }
}
