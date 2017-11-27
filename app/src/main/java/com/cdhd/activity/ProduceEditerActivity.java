package com.cdhd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhd.ApiUrl;
import com.cdhd.R;
import com.cdhd.presenter.GetBatchDetailData;
import com.cdhd.response.BatchDetailData;
import com.cdhd.utils.ToastExUtils;
import com.cdhd.view.BatchDetailInterface;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/8.
 */

public class ProduceEditerActivity extends ActivityBase implements BatchDetailInterface {
    @BindView(R.id.back)
    ImageView back;
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
    @BindView(R.id.pic_edter)
    SimpleDraweeView picEdter;
    private String batchId, productId;
    private GetBatchDetailData getBatchDetailData;

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
        titail.setText("产品信息编辑");
        Intent intent = getIntent();
        batchId = intent.getStringExtra("id");
        productId = intent.getStringExtra("productId");
        getBatchDetailData = new GetBatchDetailData(this);
        getBatchDetailData.getDetailData(batchId);

    }


    @OnClick({R.id.back, R.id.righ, R.id.base_info, R.id.produce_info, R.id.test_info, R.id.place_infon, R.id.canpany_info, R.id.logistical_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            //预览
            case R.id.righ:
                Intent intent = new Intent(ProduceEditerActivity.this, PreviewActivity.class);
                intent.putExtra("id", batchId);
                intent.putExtra("productId", productId);
                startActivity(intent);
                break;
            //基本信息
            case R.id.base_info:
                startActivity(new Intent(ProduceEditerActivity.this, BaseInfoActivity.class).putExtra("id", batchId));
                break;
            //生产信息
            case R.id.produce_info:
                startActivity(new Intent(ProduceEditerActivity.this, ProduceInfoActivity.class).putExtra("id", batchId));
                break;
            //检测信息
            case R.id.test_info:
                startActivity(new Intent(ProduceEditerActivity.this, TestInfoActivity.class).putExtra("id", batchId));
                break;
            //生产基地信息
            case R.id.place_infon:
                startActivity(new Intent(ProduceEditerActivity.this, PlaceInfoActivity.class).putExtra("id", batchId));
                break;
            //企业信息
            case R.id.canpany_info:
                startActivity(new Intent(ProduceEditerActivity.this, ConpanyInfoActivity.class).putExtra("id", batchId));
                break;
            //物流信息
            case R.id.logistical_info:
                startActivity(new Intent(ProduceEditerActivity.this, LogisticalInfoActivity.class).putExtra("id", batchId));
                break;

        }
    }

    @Override
    public void showBatchData(BatchDetailData data) {
        if (data.isSuccess()) {
            if (data.getData() != null && data.getData().getProduct() != null) {
                nameTitail.setText(data.getData().getProduct().getProductName());
                picEdter.setImageURI(ApiUrl.SERVICE_URL + data.getData().getProduct().getProductImage());
            }
        } else {
           ToastExUtils.showMassegeInfo(this,data.getMessage());
        }
    }

    @Override
    public void error(String e) {
        Log.d("ProduceEditerActivity", e);
        ToastExUtils.showError(this, e);
    }
}
