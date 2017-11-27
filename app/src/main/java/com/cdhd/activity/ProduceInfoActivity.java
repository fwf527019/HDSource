package com.cdhd.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.cdhd.ApiUrl;
import com.cdhd.R;
import com.cdhd.picker.DateTimePicker;
import com.cdhd.presenter.GetProduceData;
import com.cdhd.response.ProduceData;
import com.cdhd.utils.HelpUtil;
import com.cdhd.utils.HttpRequst;
import com.cdhd.utils.MStringCallback;
import com.cdhd.utils.ToastExUtils;
import com.cdhd.view.ProduceInterface;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yanzhenjie.album.Album;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/11/8.
 * 生产信息
 */
public class ProduceInfoActivity extends ActivityBase implements ProduceInterface {
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
    TextView edt7;
    @BindView(R.id.addimg_ll)
    LinearLayout addimgLl;
    @BindView(R.id.addimg)
    ImageView addimg;
    @BindView(R.id.logn_bt)
    Button lognBt;
    @BindView(R.id.edt_8)
    EditText edt8;
    @BindView(R.id.time_ll)
    LinearLayout timeLl;
    @BindView(R.id.spnner)
    Spinner spnner;
    private GetProduceData getProduceData;
    private String batchId;
    private List<String> pics;
    private int picNum = 0;
    private int MAX_PHOTO_NUM = 9;
    private int REQUESTCODE = 1;
    private List<String> path;
    private Bitmap bitmap;
    private ProduceData produceData;
    private DateTimePicker picker;
    private String year_month = "年";

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_produceinfo;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        batchId = intent.getStringExtra("id");
        getProduceData = new GetProduceData(this);
        getProduceData.GetData(batchId);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ProduceInfoActivity", "picNum:" + picNum);
                if (picNum <= 9) {
                    Album.startAlbum(ProduceInfoActivity.this, REQUESTCODE, MAX_PHOTO_NUM - picNum);
                }

            }
        });
        titail.setText("生产信息");


        spnner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String[] languages = getResources().getStringArray(R.array.year_month);
                year_month = languages[pos];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    @OnClick({R.id.back, R.id.logn_bt, R.id.time_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.logn_bt:
                saveData();
                break;
            case R.id.time_ll:
                //系统日期
                picker = new DateTimePicker(this, DateTimePicker.YEAR_MONTH_DAY, DateTimePicker.HOUR_24);
                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String date = sDateFormat.format(new Date());
                Log.d("TestInfoActivity", date);


                picker.setDateRangeStart(1900, 1, 1);
                picker.setDateRangeEnd(2100, 1, 1);
                picker.setTimeRangeStart(0, 0);
                picker.setTimeRangeEnd(23, 59);
                picker.setSelectedItem(pYear, pMonth, pDay, pHour, pMinute);

                picker.setWeightEnable(true);
                picker.setCancelText("取消");
                picker.setSubmitText("确认");
                picker.setLineColor(getResources().getColor(R.color.main_green));
                picker.setWheelModeEnable(true);
                picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        Toast.makeText(ProduceInfoActivity.this, (year + "-" + month + "-" + day + " " + hour + ":" + minute), Toast.LENGTH_SHORT).show();
                        edt7.setText(year + "-" + month + "-" + day + " " + hour + ":" + minute);
                    }
                });
                picker.show();
        }
    }

    /**
     * 提交数据
     */
    private void saveData() {
        startProgressDialog("信息提交中...");
        produceData.getData().setProductName(edt1.getText().toString());
        produceData.getData().setBrandName(edt2.getText().toString());
        produceData.getData().setProductGrade(edt3.getText().toString());
        produceData.getData().setProductionEnterprise(edt4.getText().toString());
        produceData.getData().setOriginCode(edt5.getText().toString());
        produceData.getData().setBatchCode(edt6.getText().toString());
        produceData.getData().setProductionDate(edt7.getText().toString());
        produceData.getData().setExpireDate(edt8.getText().toString() + year_month);
        produceData.getData().setImages(pics);
        String json = JSONObject.toJSONString(produceData.getData());
        HttpRequst.CreatPostRequst(ApiUrl.SAVEPRODUCEDATA, json, new MStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                stopProgressDialog();
                ToastExUtils.showError(ProduceInfoActivity.this, e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                if (JSONObject.parseObject(response).get("Success").toString().equals("true")) {
                    Toast.makeText(ProduceInfoActivity.this, "信息保存成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    ToastExUtils.showMassegeInfo(ProduceInfoActivity.this, JSONObject.parseObject(response).get("Message").toString());
                }
            }
        });

    }

    int pYear, pMonth, pDay, pHour, pMinute;
    int cour = 999;
    int clickNum = 1;

    @Override
    public void error(String e) {
        ToastExUtils.showError(this, e);
    }

    @Override
    public void showProduceData(ProduceData data) {
        if (data.isSuccess()) {
            produceData = data;
            String expireDate = "";
            if (data.getData().getExpireDate().contains("年")) {
                spnner.setSelection(0, true);
                expireDate = data.getData().getExpireDate().replaceAll("年", "");

            } else if (data.getData().getExpireDate().contains("月")) {
                expireDate = data.getData().getExpireDate().replaceAll("月", "");
                spnner.setSelection(1, true);
            }

            if (data.getData().getProductionDate() != null && !data.getData().getProductionDate().equals("")) {
                pYear = Integer.parseInt(data.getData().getProductionDate().substring(0, 4));
                pMonth = Integer.parseInt(data.getData().getProductionDate().substring(5, 7));
                pDay = Integer.parseInt(data.getData().getProductionDate().substring(8, 10));
                pHour = Integer.parseInt(data.getData().getProductionDate().substring(11, 13));
                pMinute = Integer.parseInt(data.getData().getProductionDate().substring(14, 16));
            }
            edt1.setText(data.getData().getProductName());
            edt2.setText(data.getData().getBrandName());
            edt3.setText(data.getData().getProductGrade());
            edt4.setText(data.getData().getProductionEnterprise());
            edt5.setText(data.getData().getOriginCode());
            edt5.setFocusable(false);
            edt6.setText(data.getData().getBatchCode());
            edt6.setFocusable(false);
            edt7.setText(data.getData().getProductionDate());
            edt8.setText(expireDate);
            //初始化
            pics = new ArrayList<>();

            if (data.getData().getImages().size() < 9) {
                addimg.setVisibility(View.VISIBLE);
            } else {
                addimg.setVisibility(View.GONE);
            }
            picNum = data.getData().getImages().size();
            for (int i = 0; i < data.getData().getImages().size(); i++) {

                //加入List
                pics.add(data.getData().getImages().get(i));
                SimpleDraweeView simpview = new SimpleDraweeView(ProduceInfoActivity.this);
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


                frameLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //在父容器中的位置
                        int index = ((ViewGroup) v.getParent()).indexOfChild(v);
                        pics.remove(index);
                        addimgLl.removeView(frameLayout);


                        Log.d("ProduceInfoActivity", "picNum:" + picNum);
                        //移除List<String>
                        picNum -= 1;
                        if (picNum < 9) {
                            addimg.setVisibility(View.VISIBLE);
                        } else {
                            addimg.setVisibility(View.GONE);
                        }
                    }
                });

            }
        } else {
            ToastExUtils.showMassegeInfo(this, data.getMessage());
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data != null) {
                path = Album.parseResult(data);
                picNum += path.size();
                Log.d("ProduceInfoActivity", "picNum:" + picNum);
                if (picNum < MAX_PHOTO_NUM) {
                    addimg.setVisibility(View.VISIBLE);
                } else {
                    addimg.setVisibility(View.GONE);
                }
                for (int i = 0; i < path.size(); i++) {
                    Uri uri = Uri.fromFile(new File(path.get(i)));
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap bit = HelpUtil.compressImage(bitmap);
                    String picSt = "data:image/jpeg;base64," + HelpUtil.bitmapToBase64(bit);
                    Log.d("BaseInfoActivity", picSt);
                    pics.add(picSt);

                    SimpleDraweeView simpview = new SimpleDraweeView(ProduceInfoActivity.this);
                    LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(dip2px(getApplicationContext(), 60), dip2px(getApplicationContext(), 60));
                    parms.setMargins(dip2px(getApplicationContext(), 10), dip2px(getApplicationContext(), 10), dip2px(getApplicationContext(), 10), dip2px(getApplicationContext(), 10));
                    simpview.setLayoutParams(parms);
                    simpview.setImageURI(uri);

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

                    frameLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addimgLl.removeView(frameLayout);
                            int index = ((ViewGroup) v.getParent()).indexOfChild(v);
                            pics.remove(index);
                            picNum -= 1;
                            Log.d("ProduceInfoActivity", "picNum:" + picNum);
                            if (picNum < 9) {
                                addimg.setVisibility(View.VISIBLE);
                            } else {
                                addimg.setVisibility(View.GONE);
                            }

                        }
                    });


                }
            }
        }
    }


}
