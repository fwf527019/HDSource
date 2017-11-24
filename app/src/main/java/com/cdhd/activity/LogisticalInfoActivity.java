package com.cdhd.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.cdhd.ApiUrl;
import com.cdhd.R;
import com.cdhd.presenter.GetLogistData;
import com.cdhd.response.LogistData;
import com.cdhd.utils.HelpUtil;
import com.cdhd.utils.HttpRequst;
import com.cdhd.utils.MStringCallback;
import com.cdhd.utils.ToastExUtils;
import com.cdhd.view.LogistInterface;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yanzhenjie.album.Album;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/11/8.
 * 物流信息
 */
public class LogisticalInfoActivity extends ActivityBase implements LogistInterface {
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
    @BindView(R.id.addimg_ll)
    LinearLayout addimgLl;
    @BindView(R.id.addimg)
    ImageView addimg;
    @BindView(R.id.logn_bt)
    Button lognBt;
    @BindView(R.id.edt_6)
    EditText edt6;
    private GetLogistData getLogistData;
    private LogistData logistData;
    private List<String> pics;
    private int picNum = 0;
    private int MAX_PHOTO_NUM = 9;
    private int REQUESTCODE = 1;
    private List<String> path;
    private Bitmap bitmap;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_logistical;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        getLogistData = new GetLogistData(this);
        getLogistData.getData(intent.getStringExtra("id"));
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
                    Album.startAlbum(LogisticalInfoActivity.this, REQUESTCODE, MAX_PHOTO_NUM - picNum);
                }

            }
        });
        titail.setText("物流信息");
    }

    @OnClick({R.id.back, R.id.logn_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.logn_bt:
                saveData();
                break;
        }
    }

    /**
     * 提交物流信息
     */
    private void saveData() {

        logistData.getData().setStorageTime(edt1.getText().toString());
        logistData.getData().setStorageNum(edt2.getText().toString());
        logistData.getData().setStorageMan(edt3.getText().toString());
        logistData.getData().setOutputTime(edt4.getText().toString());
        logistData.getData().setOutputNum(edt5.getText().toString());
        logistData.getData().setOutputMan(edt6.getText().toString());
        logistData.getData().setImages(pics);

        String json= JSONObject.toJSONString(logistData.getData());
        HttpRequst.CreatPostRequst(ApiUrl.SAVELOGISTDATA, json, new MStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastExUtils.showError(LogisticalInfoActivity.this,e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Toast.makeText(LogisticalInfoActivity.this, response, Toast.LENGTH_SHORT).show();
//                if(JSONObject.parseObject(response).get("")){
//
//                }
            }
        });



    }


    @Override
    public void error(String e) {
        ToastExUtils.showError(this, e);
    }

    @Override
    public void showLogistInfo(LogistData data) {
        logistData = data;

        edt1.setText(data.getData().getStorageTime());
        edt2.setText(data.getData().getStorageNum());
        edt3.setText(data.getData().getStorageMan());
        edt4.setText(data.getData().getOutputTime());
        edt5.setText(data.getData().getOutputNum());
        edt6.setText(data.getData().getOutputMan());


        //初始化
        pics = new ArrayList<>();

        if (data.getData().getImages().size() < 9) {
            addimg.setVisibility(View.VISIBLE);
        } else {
            addimg.setVisibility(View.GONE);
        }

        for (int i = 0; i < data.getData().getImages().size(); i++) {

            //加入List
            pics.add(data.getData().getImages().get(i));

            picNum = 1;
            SimpleDraweeView simpview = new SimpleDraweeView(LogisticalInfoActivity.this);
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

            final int finalI = i;
            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addimgLl.removeView(frameLayout);
                    picNum -= 1;
                    //移除List<String>
                    pics.remove(finalI);
                    if (picNum < 9) {
                        addimg.setVisibility(View.VISIBLE);
                    } else {
                        addimg.setVisibility(View.GONE);
                    }
                }
            });


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
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap bit = HelpUtil.compressImage(bitmap);
                    String picSt = "data:image/jpeg;base64," + HelpUtil.bitmapToBase64(bit);
                    Log.d("BaseInfoActivity", picSt);
                    pics.add(picSt);

                    SimpleDraweeView simpview = new SimpleDraweeView(LogisticalInfoActivity.this);
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


                    final int finalI = i;
                    frameLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addimgLl.removeView(frameLayout);
                            pics.remove(picNum - path.size() + finalI);
                            picNum -= 1;
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