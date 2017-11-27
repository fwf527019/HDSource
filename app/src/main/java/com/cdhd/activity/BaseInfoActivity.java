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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdhd.ApiUrl;
import com.cdhd.R;
import com.cdhd.presenter.GetEssentialInfo;
import com.cdhd.response.EssentialData;
import com.cdhd.utils.HelpUtil;
import com.cdhd.utils.HttpRequst;
import com.cdhd.utils.MStringCallback;
import com.cdhd.utils.ToastExUtils;
import com.cdhd.view.EssentialInfoInterface;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yanzhenjie.album.Album;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/11/8.
 */
public class BaseInfoActivity extends ActivityBase implements EssentialInfoInterface {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titail)
    TextView titail;
    @BindView(R.id.logn_bt)
    Button lognBt;
    @BindView(R.id.titail_right)
    LinearLayout titailRight;
    @BindView(R.id.addimg_ll)
    LinearLayout addimgLl;
    @BindView(R.id.addimg)
    ImageView addimg;
    @BindView(R.id.produce_name)
    EditText produceName;
    @BindView(R.id.produce_content)
    EditText produceContent;

    private GetEssentialInfo getEssentialInfo;
    private int MAX_PHOTO_NUM = 1;
    private String batchId;
    private EssentialData essentialdata;
    private String picSt;
    private List<String> path;
    private int picNum = 0;
    private String originalPic;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_baseinfo;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        batchId = intent.getStringExtra("id");
        getEssentialInfo = new GetEssentialInfo(this);
        getEssentialInfo.GetEssentialData(batchId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (picNum <= 1) {
                    Album.startAlbum(BaseInfoActivity.this, 1, MAX_PHOTO_NUM);
                }

            }
        });
        titail.setText("基本信息");

    }

    private Bitmap bitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data != null) {
                path = Album.parseResult(data);
                picNum += path.size();
                if (picNum < 1) {
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
                    Bitmap bit = compressImage(bitmap);
                    picSt = "data:image/jpeg;base64," + HelpUtil.bitmapToBase64(bit);
                    Log.d("BaseInfoActivity", picSt);
                    SimpleDraweeView simpview = new SimpleDraweeView(BaseInfoActivity.this);
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
                            picNum -= 1;
                            picSt = null;
                            if (picNum < 1) {
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

    @OnClick({R.id.back, R.id.logn_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.logn_bt:
                pushData();
                break;
        }
    }

    @Override
    public void error(String e) {

    }

    //获取到的数据
    @Override
    public void showEssentialInfo(EssentialData data) {
        if (data.isSuccess()) {
            essentialdata = new EssentialData();
            essentialdata = data;
            produceName.setText(data.getData().getProductName());
            produceContent.setText(data.getData().getProductDesc());
            if (data.getData().getProductImage() != null && !data.getData().getProductImage().equals("")) {
                originalPic = data.getData().getProductImage();
                picSt = data.getData().getProductImage();
                picNum = 1;
                addimg.setVisibility(View.GONE);
                SimpleDraweeView simpview = new SimpleDraweeView(BaseInfoActivity.this);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(dip2px(getApplicationContext(), 60), dip2px(getApplicationContext(), 60));
                simpview.setLayoutParams(parms);
                simpview.setImageURI(ApiUrl.SERVICE_URL + data.getData().getProductImage());


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
                        picNum -= 1;
                        picSt = null;
                        if (picNum < 1) {
                            addimg.setVisibility(View.VISIBLE);
                        } else {
                            addimg.setVisibility(View.GONE);
                        }
                    }
                });


            } else {
                addimg.setVisibility(View.VISIBLE);
            }


        }else {
            if(data.getMessage().contains("缺少验证口令")||data.getMessage().contains("无效的口令")){
                Toast.makeText(this, "登录信息失效，请重新登录", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,data.getMessage() , Toast.LENGTH_SHORT).show();
            }

        }
    }

    /**
     * 提交数据
     */
    private void pushData() {
        startProgressDialog("信息提交中...");
        essentialdata.getData().setProductName(produceName.getText().toString());
        essentialdata.getData().setProductDesc(produceContent.getText().toString());
        if (picSt != null) {
            essentialdata.getData().setProductImage(picSt);
        }

        //fast Json 实体对象json化
        String jsonString = JSON.toJSONString(essentialdata.getData());
        Log.d("BaseInfoActivity", jsonString);
        HttpRequst.CreatPostRequst(ApiUrl.SAVEESSENTIALDATA, jsonString, new MStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                stopProgressDialog();
                ToastExUtils.showError(BaseInfoActivity.this, e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                if (JSONObject.parseObject(response).get("Success").toString().equals("true")) {
                    Toast.makeText(BaseInfoActivity.this, "信息保存成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                   ToastExUtils.showMassegeInfo(BaseInfoActivity.this,JSONObject.parseObject(response).get("Message").toString());
                }
            }
        });
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;

        while ((baos.toByteArray().length / 1024 > 1024) && (options > 10)) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }
}
