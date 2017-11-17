package com.cdhd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdhd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/8.
 */

public class ProduceEditerActivity extends ActivityBase {
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.titail)
    TextView titail;
    @BindView(R.id.righ)
    TextView righ;
    @BindView(R.id.titail_right)
    LinearLayout titailRight;
    @BindView(R.id.base_info)
    LinearLayout baseInfo;
    @BindView(R.id.produce_info)
    LinearLayout produceInfo;
    @BindView(R.id.test_info)
    LinearLayout testInfo;
    @BindView(R.id.place_infon)
    LinearLayout placeInfon;
    @BindView(R.id.canpany_info)
    LinearLayout canpanyInfo;
    @BindView(R.id.logistical_info)
    LinearLayout logisticalInfo;
    @BindView(R.id.name_titail)
    TextView nameTitail;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_produce_editer;
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
        nameTitail.setAlpha((float) 0.8);
    }

    @OnClick({R.id.back, R.id.righ, R.id.base_info, R.id.produce_info, R.id.test_info, R.id.place_infon, R.id.canpany_info, R.id.logistical_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.righ:
                startActivity(new Intent(ProduceEditerActivity.this, PreviewActivity.class));
                break;
            case R.id.base_info:
                startActivity(new Intent(ProduceEditerActivity.this, BaseInfoActivity.class));
                break;
            case R.id.produce_info:
                startActivity(new Intent(ProduceEditerActivity.this, ProduceInfoActivity.class));
                break;
            case R.id.test_info:
                startActivity(new Intent(ProduceEditerActivity.this, TestInfoActivity.class));
                break;
            case R.id.place_infon:
                startActivity(new Intent(ProduceEditerActivity.this, PlaceInfoActivity.class));
                break;
            case R.id.canpany_info:
                startActivity(new Intent(ProduceEditerActivity.this, ConpanyInfoActivity.class));

                break;
            case R.id.logistical_info:
                startActivity(new Intent(ProduceEditerActivity.this, LogisticalInfoActivity.class));
                break;

        }
    }
}
