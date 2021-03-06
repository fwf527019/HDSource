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
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.cdhd.ApiUrl;
import com.cdhd.R;
import com.cdhd.picker.DateTimePicker;
import com.cdhd.presenter.GetTestData;
import com.cdhd.response.TesttData;
import com.cdhd.utils.HelpUtil;
import com.cdhd.utils.HttpRequst;
import com.cdhd.utils.MStringCallback;
import com.cdhd.utils.ToastExUtils;
import com.cdhd.view.TestInterface;
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
 * 检测信息
 */
public class TestInfoActivity extends ActivityBase implements TestInterface {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titail)
    TextView titail;
    @BindView(R.id.titail_right)
    LinearLayout titailRight;
    @BindView(R.id.edt_1)
    TextView edt1;
    @BindView(R.id.edt_2)
    EditText edt2;
    @BindView(R.id.edt_3)
    EditText edt3;
    @BindView(R.id.edt_4)
    EditText edt4;
    @BindView(R.id.addimg_ll)
    LinearLayout addimgLl;
    @BindView(R.id.addimg)
    ImageView addimg;
    @BindView(R.id.logn_bt)
    Button lognBt;
    @BindView(R.id.test_time_ll)
    LinearLayout testTimeLl;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    private GetTestData getTestData;
    private TesttData testtData;
    private DateTimePicker picker;

    private List<String> pics;
    private int picNum = 0;
    private int MAX_PHOTO_NUM = 9;
    private int REQUESTCODE = 1;
    private List<String> path;
    private Bitmap bitmap;
    int pYear, pMonth, pDay, pHour, pMinute;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_testinfo;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        getTestData = new GetTestData(this);
        getTestData.getData(intent.getStringExtra("id"));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (picNum <= 9) {
                    Album.startAlbum(TestInfoActivity.this, REQUESTCODE, MAX_PHOTO_NUM - picNum);
                }

            }
        });
        titail.setText("质检信息");
    }

    @OnClick({R.id.back, R.id.logn_bt, R.id.test_time_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.logn_bt:
                savaData();
                break;

            case R.id.test_time_ll:
                //系统日期
                picker = new DateTimePicker(this, DateTimePicker.HOUR_24);
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
                        Toast.makeText(TestInfoActivity.this, (year + "-" + month + "-" + day + " " + hour + ":" + minute), Toast.LENGTH_SHORT).show();
                        edt1.setText(year + "-" + month + "-" + day + " " + hour + ":" + minute);
                    }
                });
                picker.show();
                break;
        }
    }

    /**
     * 保存数据
     */
    private void savaData() {
        startProgressDialog("信息提交中...");
        testtData.getData().setQualityTime(edt1.getText().toString());
        testtData.getData().setQualityOrganization(edt2.getText().toString());
        testtData.getData().setQualityMan(edt3.getText().toString());
        testtData.getData().setQualityStandard(edt4.getText().toString());
        testtData.getData().setImages(pics);

        String json = JSONObject.toJSONString(testtData.getData());
        HttpRequst.CreatPostRequst(ApiUrl.SAVETESTTDATA, json, new MStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                stopProgressDialog();
                ToastExUtils.showError(TestInfoActivity.this, e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                if (JSONObject.parseObject(response).get("Success").toString().equals("true")) {
                    Toast.makeText(TestInfoActivity.this, "信息保存成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(TestInfoActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    @Override
    public void error(String e) {

        ToastExUtils.showError(this, e);

    }

    @Override
    public void showTestInfo(TesttData data) {
        if (data.isSuccess()) {
            testtData = data;
//            int year
//            if(){
//                picker.setSelectedItem();
//            }

            if (data.getData().getQualityTime() != null && !data.getData().getQualityTime().equals("")) {
                pYear = Integer.parseInt(data.getData().getQualityTime().substring(0, 4));
                pMonth = Integer.parseInt(data.getData().getQualityTime().substring(5, 7));
                pDay = Integer.parseInt(data.getData().getQualityTime().substring(8, 10));
                pHour = Integer.parseInt(data.getData().getQualityTime().substring(11, 13));
                pMinute = Integer.parseInt(data.getData().getQualityTime().substring(14, 16));
            }
            edt1.setText(data.getData().getQualityTime());
            edt2.setText(data.getData().getQualityOrganization());
            edt3.setText(data.getData().getQualityMan());
            edt4.setText(data.getData().getQualityStandard());


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

                SimpleDraweeView simpview = new SimpleDraweeView(TestInfoActivity.this);
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
                        picNum -= 1;
                        //移除List<String>
                        //在父容器中的位置
                        int index = ((ViewGroup) v.getParent()).indexOfChild(v);
                        pics.remove(index);
                        addimgLl.removeView(frameLayout);
                        if (picNum < 9) {
                            addimg.setVisibility(View.VISIBLE);
                        } else {
                            addimg.setVisibility(View.GONE);
                        }
                        scrollView.scrollBy(0, -1);
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
                if (picNum < MAX_PHOTO_NUM) {
                    addimg.setVisibility(View.VISIBLE);
                } else {
                    addimg.setVisibility(View.GONE);
                }
                for (int i = 0; i < path.size(); i++) {
                    Uri uri = Uri.fromFile(new File(path.get(i)));
//                    try {
//                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    Bitmap bit = HelpUtil.compressImage(bitmap);
//                    String picSt = "data:image/jpeg;base64," + HelpUtil.bitmapToBase64(bit);
//                    Log.d("BaseInfoActivity", picSt);
//                    pics.add(picSt);
//
//                    SimpleDraweeView simpview = new SimpleDraweeView(TestInfoActivity.this);
//                    LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(dip2px(getApplicationContext(), 60), dip2px(getApplicationContext(), 60));
//                    parms.setMargins(dip2px(getApplicationContext(), 10), dip2px(getApplicationContext(), 10), dip2px(getApplicationContext(), 10), dip2px(getApplicationContext(), 10));
//                    simpview.setLayoutParams(parms);
//                    simpview.setImageURI(uri);
//
//                    TextView textView = new TextView(this);
//                    textView.setText("删 除");
//                    textView.setBackgroundColor(getResources().getColor(R.color.black));
//                    FrameLayout.LayoutParams parms1 = new FrameLayout.LayoutParams(dip2px(getApplicationContext(), 60), dip2px(getApplicationContext(), 20));
//                    parms1.gravity = Gravity.BOTTOM;
//                    textView.setLayoutParams(parms1);
//                    textView.setAlpha((float) 0.7);
//                    textView.setTextColor(getResources().getColor(R.color.white));
//                    textView.setTextSize(8);
//                    textView.setGravity(Gravity.CENTER);
//                    final FrameLayout frameLayout = new FrameLayout(this);
//
//                    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(dip2px(getApplicationContext(), 60), dip2px(getApplicationContext(), 60));
//                    params2.setMargins(dip2px(getApplicationContext(), 10), dip2px(getApplicationContext(), 10), dip2px(getApplicationContext(), 10), dip2px(getApplicationContext(), 10));
//                    params2.gravity = Gravity.CENTER_VERTICAL;
//                    frameLayout.setLayoutParams(params2);
//                    frameLayout.addView(simpview);
//                    frameLayout.addView(textView);
//                    addimgLl.addView(frameLayout);
//
//
//                    final int finalI = i;
//                    frameLayout.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            //在父容器中的位置
//                            int index = ((ViewGroup) v.getParent()).indexOfChild(v);
//                            pics.remove(index);
//                            addimgLl.removeView(frameLayout);
//                            picNum -= 1;
//                            if (picNum < 9) {
//                                addimg.setVisibility(View.VISIBLE);
//                            } else {
//                                addimg.setVisibility(View.GONE);
//                            }
//
//                        }
//                    });

                    setPic(uri);
                }
            }
        }
    }

    private void setPic(Uri uri) {
        //获取目标控件的大小
        int targetW = dip2px(getApplicationContext(), 70);
        int targetH = dip2px(getApplicationContext(), 70);
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        try {
            //inJustDecodeBounds为true，可以加载源图片的尺寸大小，decodeStream方法返回的bitmap为null
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(TestInfoActivity.this.getContentResolver().openInputStream(uri), null, bmOptions);
            // 得到源图片的尺寸
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            //通过比较获取较小的缩放比列
            int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
            // 将inJustDecodeBounds置为false，设置bitmap的缩放比列
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;
            //再次decode获取bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(TestInfoActivity.this.getContentResolver().openInputStream(uri), null, bmOptions);


            String picSt = "data:image/jpeg;base64," + HelpUtil.bitmapToBase64(bitmap);
            Log.d("BaseInfoActivity", picSt);
            pics.add(picSt);

            SimpleDraweeView simpview = new SimpleDraweeView(TestInfoActivity.this);
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
                    int index = ((ViewGroup) frameLayout.getParent()).indexOfChild(frameLayout);
                    addimgLl.removeView(frameLayout);
                    pics.remove(index);
                    picNum -= 1;
                    Log.d("ProduceInfoActivity", "picNum:" + picNum);
                    if (picNum < 9) {
                        addimg.setVisibility(View.VISIBLE);
                    } else {
                        addimg.setVisibility(View.GONE);
                    }
                    scrollView.scrollBy(0, -1);
                }
            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
