package com.cdhd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhd.ApiUrl;
import com.cdhd.R;
import com.cdhd.presenter.GetEnterpriseData;
import com.cdhd.response.EnterpriseData;
import com.cdhd.utils.ToastExUtils;
import com.cdhd.view.EnterpriseInterface;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/8.
 * <p>
 * 企业信息
 */


public class ConpanyInfoActivity extends ActivityBase implements EnterpriseInterface {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titail)
    TextView titail;
    @BindView(R.id.titail_right)
    LinearLayout titailRight;
    @BindView(R.id.edt_1)
    EditText edt1;
    @BindView(R.id.edt_2)
    EditText edt2;
    @BindView(R.id.edt_3)
    EditText edt3;
    @BindView(R.id.edt_4)
    EditText edt4;
    @BindView(R.id.edt_5)
    EditText edt5;
    @BindView(R.id.edt_6)
    EditText edt6;
    @BindView(R.id.edt_7)
    EditText edt7;
    @BindView(R.id.addimg_ll)
    LinearLayout addimgLl;
    @BindView(R.id.addimg)
    ImageView addimg;
    @BindView(R.id.logn_bt)
    Button lognBt;
    GetEnterpriseData getEnterpriseData;
    private List<String> pics;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_enterprise_info;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        getEnterpriseData = new GetEnterpriseData(this);
        getEnterpriseData.getData(intent.getStringExtra("id"));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        titail.setText("基地信息");
        addimg.setVisibility(View.GONE);
    }

    @OnClick({R.id.back, R.id.logn_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.logn_bt:
                Toast.makeText(this, "企业信息不能编辑!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void error(String e) {
        ToastExUtils.showError(this, e);
    }

    @Override
    public void showData(EnterpriseData data) {

        if (data.isSuccess()) {

            edt1.setText(data.getData().getEnterpriseName());
            edt2.setText(data.getData().getLinkAddress());
            edt3.setText(data.getData().getLicenseCode());
            edt4.setText(data.getData().getFaxNumber());
            edt5.setText(data.getData().getLinkAddress());
            edt6.setText(data.getData().getWebsite());
            edt7.setText(data.getData().getEnterpriseAbstract());

            //初始化
            pics = new ArrayList<>();

            for (int i = 0; i < data.getData().getImages().size(); i++) {
                //加入List
                pics.add(data.getData().getImages().get(i));
                SimpleDraweeView simpview = new SimpleDraweeView(ConpanyInfoActivity.this);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(dip2px(getApplicationContext(), 60), dip2px(getApplicationContext(), 60));
                simpview.setLayoutParams(parms);
                simpview.setImageURI(ApiUrl.SERVICE_URL + data.getData().getImages().get(i));

                TextView textView = new TextView(this);
                textView.setText("删 除");
                textView.setBackgroundColor(getResources().getColor(R.color.black));
                FrameLayout.LayoutParams parms1 = new FrameLayout.LayoutParams(dip2px(getApplicationContext(), 60), dip2px(getApplicationContext(), 20));
                parms1.gravity = Gravity.BOTTOM;
                textView.setLayoutParams(parms1);
                textView.setAlpha((float) 0.7);
                textView.setTextColor(getResources().getColor(R.color.white));
                textView.setTextSize(8);
                textView.setGravity(Gravity.CENTER);
                final FrameLayout frameLayout = new FrameLayout(this);

                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(dip2px(getApplicationContext(), 60), dip2px(getApplicationContext(), 60));
                params2.setMargins(dip2px(getApplicationContext(), 10), dip2px(getApplicationContext(), 10), dip2px(getApplicationContext(), 10), dip2px(getApplicationContext(), 10));
                params2.gravity = Gravity.CENTER_VERTICAL;
                frameLayout.setLayoutParams(params2);
                frameLayout.addView(simpview);
                frameLayout.addView(textView);
                addimgLl.addView(frameLayout);

            }
        }else {
            ToastExUtils.showMassegeInfo(this,data.getMessage());
        }
    }

}
