package com.cdhd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdhd.R;
import com.cdhd.presenter.GetPreviewData;
import com.cdhd.response.BatchAllData;
import com.cdhd.view.PreviewInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/9.
 */

public class PreviewActivity extends ActivityBase implements PreviewInterface {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titail)
    TextView titail;
    @BindView(R.id.titail_right)
    LinearLayout titailRight;
    private String batchId, produceId;
    private GetPreviewData getPreviewData;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_preview;

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
        Intent intent = getIntent();
        batchId = intent.getStringExtra("id");
        produceId = intent.getStringExtra("productId");
        getPreviewData = new GetPreviewData(this);
        getPreviewData.GetAllData(batchId, produceId);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void showBaseInfo(BatchAllData.DataBean.EssentialBean data) {

    }

    @Override
    public void showProduceInfo(BatchAllData.DataBean.ProdutionBean data) {

    }

    @Override
    public void showTestInfo(BatchAllData.DataBean.QualityBean data) {

    }

    @Override
    public void showPlaceInfo(BatchAllData.DataBean.BaseBean data) {

    }

    @Override
    public void showEnterpriseInfo(BatchAllData.DataBean.EnterpriseBean data) {

    }

    @Override
    public void showLogistInfo(BatchAllData.DataBean.LogistiscsBean data) {

    }

    @Override
    public void error(String e) {

    }

}
