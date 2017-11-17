package com.cdhd.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    TextView back;
    @BindView(R.id.titail)
    TextView titail;
    @BindView(R.id.righ)
    TextView righ;
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

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_myself;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.logn_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.logn_bt:

                break;
        }
    }
}
