package com.cdhd.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhd.App;
import com.cdhd.R;
import com.cdhd.presenter.GetLoginData;
import com.cdhd.response.LoginData;
import com.cdhd.utils.ToastExUtils;
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
    @BindView(R.id.titail_right)
    LinearLayout titailRight;
    @BindView(R.id.remenberpsw_box)
    CheckBox remenberpswBox;
    @BindView(R.id.isauto_box)
    CheckBox isautoBox;
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
        back.setVisibility(View.GONE);
        titail.setText("登录");
        SharedPreferences sp = getSharedPreferences("user_data", Context.MODE_PRIVATE);

        if (sp.getString("remPsw", "0").equals("1")) {
            username.setText(sp.getString("account", ""));
            password.setText(sp.getString("psw", ""));
            remenberpswBox.setChecked(true);
        }
        if (sp.getString("auto", "0").equals("1")) {
            userName = username.getText().toString().trim();
            psw = password.getText().toString().trim();
            startProgressDialog("登录中...");
            isautoBox.setChecked(true);
            getLoginData.GotoLogin(userName, psw, true, false);
        }

        final SharedPreferences.Editor editor = sp.edit();
        isautoBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putString("auto", "1");
                    editor.commit();
                } else {
                    editor.putString("auto", "0");
                    editor.commit();
                }
            }
        });
        remenberpswBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putString("remPsw", "1");
                    editor.commit();
                } else {
                    editor.putString("remPsw", "0");
                    editor.commit();
                }

            }
        });
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
        startProgressDialog(" 登录中...");
        getLoginData.GotoLogin(userName, psw, true, false);

    }

    @Override
    public void showLoginResult(LoginData data) {
        stopProgressDialog();
        if (data.isSuccess()) {
            App.setOriginToken(data.getData().getUserToken());
            //保存用户信息
            SharedPreferences sp = getSharedPreferences("user_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("name", data.getData().getUserName());
            editor.putString("RoleName", data.getData().getRoleName());
            editor.putString("UserToken", data.getData().getUserToken());
            editor.putString("account", userName);
            editor.putString("psw", psw);
            editor.commit();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            ToastExUtils.showMassegeInfo(this, data.getMessage());
        }
    }

    @Override
    public void error() {
        stopProgressDialog();
        Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
    }
    private long exitTime = 0;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if ((System.currentTimeMillis() - exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
            {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);

    }

}
