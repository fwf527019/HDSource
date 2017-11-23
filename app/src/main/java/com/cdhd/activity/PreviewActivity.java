package com.cdhd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdhd.ApiUrl;
import com.cdhd.R;
import com.cdhd.presenter.GetPreviewData;
import com.cdhd.response.BatchAllData;
import com.cdhd.utils.MyTextView;
import com.cdhd.utils.ToastExUtils;
import com.cdhd.view.PreviewInterface;
import com.facebook.drawee.view.SimpleDraweeView;

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
    @BindView(R.id.product_pic)
    SimpleDraweeView productPic;
    @BindView(R.id.produce_name)
    MyTextView produceName;
    @BindView(R.id.eseential_content)
    MyTextView eseentialContent;
    @BindView(R.id.produce_content)
    MyTextView produceContent;
    @BindView(R.id.test_content)
    MyTextView testContent;
    @BindView(R.id.place_content)
    MyTextView placeContent;
    @BindView(R.id.enterprise_content)
    MyTextView enterpriseContent;
    @BindView(R.id.pic_produce_ll)
    LinearLayout picProduceLl;
    @BindView(R.id.pic_test_ll)
    LinearLayout picTestLl;
    @BindView(R.id.pic_place_ll)
    LinearLayout picPlaceLl;
    @BindView(R.id.pic_enterprise_ll)
    LinearLayout picEnterpriseLl;
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
        titail.setText(data.getProductName());
        produceName.setText(data.getProductName());
        eseentialContent.setText("  " + data.getProductDesc());
        productPic.setImageURI(ApiUrl.SERVICE_URL + data.getProductImage());
    }

    @Override
    public void showProduceInfo(BatchAllData.DataBean.ProdutionBean data) {
        //内容设置
        produceContent.setText("产品名称: " + data.getProductName() + "\n" +
                "品牌: " + data.getBrandName() + "\n" +
                "产品等级: " + data.getProductGrade() + "\n" +
                "生产企业: " + data.getProductionEnterprise() + "\n" +
                "溯源码:  " + data.getOriginCode() + "\n" +
                "批次号: " + data.getBatchCode() + "\n" +
                "生产日期: " + data.getProductionDate() + "\n" +
                "保质期: " + data.getExpireDate()
        );
        //图片设置
        if (data.getImages() != null && data.getImages().size() != 0) {
            picProduceLl.setVisibility(View.VISIBLE);
            for (int i = 0; i < data.getImages().size(); i++) {
                SimpleDraweeView imgview = new SimpleDraweeView(PreviewActivity.this);

                LinearLayout ll = new LinearLayout(PreviewActivity.this);
                ll.setBackgroundColor(getResources().getColor(R.color.e6));
                LinearLayout.LayoutParams pams1 = new LinearLayout.LayoutParams(dip2px(70), LinearLayout.LayoutParams.MATCH_PARENT);
                pams1.setMargins(dip2px(5), 0, 0, 0);
                ll.setLayoutParams(pams1);

                LinearLayout.LayoutParams pams = new LinearLayout.LayoutParams(dip2px(60), LinearLayout.LayoutParams.MATCH_PARENT);
                pams.setMargins(dip2px(5), dip2px(5), dip2px(5), dip2px(5));
                imgview.setLayoutParams(pams);
                imgview.setImageURI(ApiUrl.SERVICE_URL + data.getImages().get(i));
                ll.addView(imgview);
                picProduceLl.addView(ll);
            }
        } else {
            picProduceLl.setVisibility(View.GONE);
        }
    }


    @Override
    public void showTestInfo(BatchAllData.DataBean.QualityBean data) {
        testContent.setText("质检时间: " + data.getQualityTime() + "\n" +
                "质检机构: " + data.getQualityOrganization() + "\n" +
                "质检人: " + data.getQualityMan() + "\n" +
                "质检结果判定标准: " + data.getQualityStandard()
        );

        //图片设置
        if (data.getImages() != null && data.getImages().size() != 0) {
            picProduceLl.setVisibility(View.VISIBLE);
            for (int i = 0; i < data.getImages().size(); i++) {
                SimpleDraweeView imgview = new SimpleDraweeView(PreviewActivity.this);
                LinearLayout ll = new LinearLayout(PreviewActivity.this);
                ll.setBackgroundColor(getResources().getColor(R.color.e6));
                LinearLayout.LayoutParams pams1 = new LinearLayout.LayoutParams(dip2px(70), LinearLayout.LayoutParams.MATCH_PARENT);
                pams1.setMargins(dip2px(5), 0, 0, 0);
                ll.setLayoutParams(pams1);
                LinearLayout.LayoutParams pams = new LinearLayout.LayoutParams(dip2px(60), LinearLayout.LayoutParams.MATCH_PARENT);
                pams.setMargins(dip2px(5), dip2px(5), dip2px(5), dip2px(5));
                imgview.setLayoutParams(pams);
                imgview.setImageURI(ApiUrl.SERVICE_URL + data.getImages().get(i));
                ll.addView(imgview);
                picTestLl.addView(ll);
            }
        } else {
            picTestLl.setVisibility(View.GONE);
        }


    }

    @Override
    public void showPlaceInfo(BatchAllData.DataBean.BaseBean data) {
        placeContent.setText(
                "基地名称: " + data.getBaseName() + "\n" +
                        "基地地址: " + data.getBaseAddress() + "\n" +
                        "基地类型: " + data.getBaseType() + "\n" +
                        "基地面积: " + data.getBaseArea() + "\n" +
                        "建成时间: " + data.getBuiltTime() + "\n" +
                        "资源情况: " + data.getSourceInfo()
        );

        //图片设置
        if (data.getImages() != null && data.getImages().size() != 0) {
            picProduceLl.setVisibility(View.VISIBLE);
            for (int i = 0; i < data.getImages().size(); i++) {
                SimpleDraweeView imgview = new SimpleDraweeView(PreviewActivity.this);
                LinearLayout ll = new LinearLayout(PreviewActivity.this);
                ll.setBackgroundColor(getResources().getColor(R.color.e6));
                LinearLayout.LayoutParams pams1 = new LinearLayout.LayoutParams(dip2px(70), LinearLayout.LayoutParams.MATCH_PARENT);
                pams1.setMargins(dip2px(5), 0, 0, 0);
                ll.setLayoutParams(pams1);
                LinearLayout.LayoutParams pams = new LinearLayout.LayoutParams(dip2px(60), LinearLayout.LayoutParams.MATCH_PARENT);
                pams.setMargins(dip2px(5), dip2px(5), dip2px(5), dip2px(5));
                imgview.setLayoutParams(pams);
                imgview.setImageURI(ApiUrl.SERVICE_URL + data.getImages().get(i));
                ll.addView(imgview);
                picPlaceLl.addView(ll);
            }
        } else {
            picPlaceLl.setVisibility(View.GONE);
        }

    }

    @Override
    public void showEnterpriseInfo(BatchAllData.DataBean.EnterpriseBean data) {
        enterpriseContent.setText(
                "企业名称: "+data.getEnterpriseName()+"\n"+
                "主营产品: "+data.getMainProduct()+"\n"+
                "营业执照号码: "+data.getLicenseCode()+"\n"+
                "联系电话: "+data.getLinkPhone()+"\n"+
                "传真号码: "+data.getFaxNumber()+"\n"+
                "联系地址: "+data.getLinkAddress()+"\n"+
                "网址: "+data.getWebsite()+"\n"+
                "企业概况: "+data.getEnterpriseAbstract()
        );
        //图片设置
        if (data.getImages() != null && data.getImages().size() != 0) {
            picProduceLl.setVisibility(View.VISIBLE);
            for (int i = 0; i < data.getImages().size(); i++) {
                SimpleDraweeView imgview = new SimpleDraweeView(PreviewActivity.this);
                LinearLayout ll = new LinearLayout(PreviewActivity.this);
                ll.setBackgroundColor(getResources().getColor(R.color.e6));
                LinearLayout.LayoutParams pams1 = new LinearLayout.LayoutParams(dip2px(70), LinearLayout.LayoutParams.MATCH_PARENT);
                pams1.setMargins(dip2px(5), 0, 0, 0);
                ll.setLayoutParams(pams1);
                LinearLayout.LayoutParams pams = new LinearLayout.LayoutParams(dip2px(60), LinearLayout.LayoutParams.MATCH_PARENT);
                pams.setMargins(dip2px(5), dip2px(5), dip2px(5), dip2px(5));
                imgview.setLayoutParams(pams);
                imgview.setImageURI(ApiUrl.SERVICE_URL + data.getImages().get(i));
                ll.addView(imgview);
                picEnterpriseLl.addView(ll);
            }
        } else {
            picEnterpriseLl.setVisibility(View.GONE);
        }


    }

    @Override
    public void showLogistInfo(BatchAllData.DataBean.LogistiscsBean data) {

    }

    @Override
    public void error(String e) {
        ToastExUtils.showError(this, e);
    }


    /**
     * dip转像素
     */
    public int dip2px(float dpValue) {
        float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
