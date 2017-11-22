package com.cdhd.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdhd.App;
import com.cdhd.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/8.
 */
public class MySelfActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titail)
    TextView titail;

    @BindView(R.id.titail_right)
    LinearLayout titailRight;
    @BindView(R.id.myself_photo_img)
    SimpleDraweeView myselfPhotoImg;
    @BindView(R.id.myself_name)
    TextView myselfName;
    @BindView(R.id.myself_work)
    TextView myselfWork;
    @BindView(R.id.logn_bt)
    Button lognBt;
    private String name;
    private String roleName;
    private SharedPreferences sp;

    protected int getContentViewResId() {
        return R.layout.activity_myself;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        sp = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        name = sp.getString("name", "");
        roleName = sp.getString("RoleName", "");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        titail.setText("个人中心");
        myselfName.setText(name);
        myselfWork.setText(roleName);
    }

    @OnClick({R.id.back, R.id.logn_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            //退出登录
            case R.id.logn_bt:
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                App.setOriginToken("");
                startActivity(new Intent(MySelfActivity.this,LoginActivity.class));
                break;
        }
    }
}
