package com.cdhd.activity;

import android.content.Intent;
import android.graphics.Bitmap;
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
import com.cdhd.presenter.GetLogistData;
import com.cdhd.presenter.GetPlaceData;
import com.cdhd.response.LogistData;
import com.cdhd.response.PlaceData;
import com.cdhd.utils.ToastExUtils;
import com.cdhd.view.LogistInterface;
import com.cdhd.view.PlaceInterface;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/8.
 * 基地信息
 */
public class PlaceInfoActivity extends ActivityBase implements PlaceInterface {
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
    @BindView(R.id.addimg_ll)
    LinearLayout addimgLl;
    @BindView(R.id.addimg)
    ImageView addimg;
    @BindView(R.id.logn_bt)
    Button lognBt;
    private GetPlaceData getPlaceData;
    private LogistData logistData;
    private List<String> pics;
    private int picNum = 0;
    private int MAX_PHOTO_NUM = 9;
    private int REQUESTCODE = 1;
    private List<String> path;
    private Bitmap bitmap;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_baselace_info;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        getPlaceData = new GetPlaceData(this);
        getPlaceData.getData(intent.getStringExtra("id"));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        addimg.setVisibility(View.GONE);
        titail.setText("企业信息");
    }

    @OnClick({R.id.back, R.id.logn_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.logn_bt:
                finish();
           //     Toast.makeText(this, "基地信息不能编辑", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void error(String e) {
        ToastExUtils.showError(this, e);
    }


    @Override
    public void showPlaceInfo(PlaceData data) {

        if (data.isSuccess()) {
            edt1.setText(data.getData().getBaseName());
            edt2.setText(data.getData().getBaseAddress());
            edt3.setText(data.getData().getBaseType());
            edt4.setText(data.getData().getBaseArea());
            edt5.setText(data.getData().getCreateTime());
            edt6.setText(data.getData().getSourceInfo());


            //初始化
            pics = new ArrayList<>();

            for (int i = 0; i < data.getData().getImages().size(); i++) {
                //加入List
                pics.add(data.getData().getImages().get(i));
                picNum = 1;
                SimpleDraweeView simpview = new SimpleDraweeView(PlaceInfoActivity.this);
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
             //   frameLayout.addView(textView);
                addimgLl.addView(frameLayout);

            }
        }else {
            ToastExUtils.showMassegeInfo(this,data.getMessage());
        }
    }


}
