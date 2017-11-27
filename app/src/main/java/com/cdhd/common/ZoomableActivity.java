package com.cdhd.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cdhd.R;
import com.cdhd.activity.ActivityBase;
import com.cdhd.common.adapter.ZoomableViewPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by idisfkj on 17/3/5.
 * Email : idisfkj@gmail.com.
 */

public class ZoomableActivity extends ActivityBase implements ViewPager.OnPageChangeListener {
    private static final String EXTRA_ZOOMABLE_PATHS = "extra_zoomable_paths";
    private static final String EXTRA_ZOOMABLE_INDEX = "extra_zoomable_index";
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.zoomable_index)
    TextView zoomableIndex;
    @BindView(R.id.zoomable_back)
    TextView zoomableBack;

    private ViewPager mViewPager;
    private TextView mZoomableIndex;
    private ArrayList<String> mPaths;
    private int mIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getExtraData();
        initView();
        setupViewPager();
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.zoommable_activity;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    private void getExtraData() {
        mPaths = getIntent().getStringArrayListExtra(EXTRA_ZOOMABLE_PATHS);
        mIndex = getIntent().getIntExtra(EXTRA_ZOOMABLE_INDEX, 0);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mZoomableIndex = (TextView) findViewById(R.id.zoomable_index);
    }

    private void setupViewPager() {
        mViewPager.setAdapter(new ZoomableViewPagerAdapter(this, mPaths));
        mViewPager.setCurrentItem(mIndex);
        mZoomableIndex.setText(mIndex + 1 + "/" + mPaths.size());
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mZoomableIndex.setText(position + 1 + "/" + mPaths.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static void goToPage(Context context, ArrayList<String> paths, int index) {
        Intent intent = new Intent(context, ZoomableActivity.class);
        intent.putStringArrayListExtra(EXTRA_ZOOMABLE_PATHS, paths);
        intent.putExtra(EXTRA_ZOOMABLE_INDEX, index);
        context.startActivity(intent);
    }

    @OnClick(R.id.zoomable_back)
    public void onViewClicked() {
        finish();
    }
}
