package com.cdhd.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdhd.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yanzhenjie.album.Album;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/8.
 */
public class BaseInfoActivity extends ActivityBase {
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
    public static final int MAX_PHOTO_NUM = 9;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_baseinfo;
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
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Album.startAlbum(BaseInfoActivity.this, 1, MAX_PHOTO_NUM);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data != null) {
                List<String> path = Album.parseResult(data);
                for (int i = 0; i < path.size(); i++) {
                    Uri uri = Uri.fromFile(new File(path.get(i)));
                    SimpleDraweeView simpview = new SimpleDraweeView(BaseInfoActivity.this);
                    simpview.setLayoutParams(new LinearLayout.LayoutParams(dip2px(getApplicationContext(), 70), dip2px(getApplicationContext(), 70)));
                    simpview.setImageURI(uri);
                    addimgLl.addView(simpview);
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

                break;
        }
    }
}
