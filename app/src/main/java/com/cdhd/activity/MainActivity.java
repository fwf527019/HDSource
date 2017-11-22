package com.cdhd.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cdhd.R;
import com.cdhd.presenter.GetMianPageData;
import com.cdhd.response.MainListData;
import com.cdhd.view.MainPageInterface;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.wyh.slideAdapter.ItemBind;
import com.wyh.slideAdapter.ItemView;
import com.wyh.slideAdapter.SlideAdapter;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends ActivityBase implements MainPageInterface {

    private static final int PAGE_SIZE = 10;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titail)
    TextView titail;
    @BindView(R.id.titail_right)
    LinearLayout titailRight;

    @BindView(R.id.main_recyclerview)
    RecyclerView mainRecyclerview;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    static final int REQUEST_CODE = 5;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.search_edt)
    EditText searchEdt;
    private GetMianPageData getMianPageData;
    private int pageIndex = 1;
    private String keyWord = "";

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        getMianPageData = new GetMianPageData(this);
        getMianPageData.getMainList(keyWord, pageIndex, PAGE_SIZE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        titail.setText("首页");

        // back.setText("我的");
        back.setBackgroundResource(R.mipmap.header_ico);
        titailRight.setVisibility(View.VISIBLE);

        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                getMianPageData.getMainList(keyWord, pageIndex, PAGE_SIZE);

            }
        });
        //加载
        refreshLayout.setEnableLoadmoreWhenContentNotFull(true);
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                getMianPageData.getMainList(keyWord, pageIndex, PAGE_SIZE);
            }
        });
        searchEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d("MainActivity", "v:" + v);
                Log.d("MainActivity", "actionId:" + actionId);
                if (actionId == EditorInfo.IME_ACTION_SEARCH || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (searchEdt.getText().toString().trim().length() != 0) {
                        keyWord = searchEdt.getText().toString().trim();
                        pageIndex = 1;
                        getMianPageData.getMainList(keyWord, pageIndex, PAGE_SIZE * 2);
                    } else {
                        Toast.makeText(MainActivity.this, "请输入搜索内容!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "aaaa!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

    }

    @OnClick({R.id.back, R.id.titail_right, R.id.titail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                startActivity(new Intent(MainActivity.this, MySelfActivity.class));
                break;
            case R.id.titail_right:
                //扫码
                AndPermission.with(MainActivity.this)
                        .requestCode(100)
                        .permission(
                                // Multiple permissions, array form.
                                Manifest.permission.CAMERA
                        )
                        .rationale(new RationaleListener() {
                            @Override
                            public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                                AndPermission.rationaleDialog(MainActivity.this, rationale)
                                        .show();
                            }
                        })
                        .callback(new PermissionListener() {
                            @Override
                            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                                startActivityForResult(intent, REQUEST_CODE);
                            }

                            @Override
                            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                                Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .start();
                break;

            case R.id.titail:
                //   keyWord = sherchpageEdt.getText().toString();

                startActivity(new Intent(this, ProduceEditerActivity.class));
                break;
        }
    }

    /**
     * 扫一扫的结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No Result!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private SlideAdapter adapter;
    /**
     * 列表展示
     */
    private ItemBind<MainListData.DataBean> itemBind = new ItemBind<MainListData.DataBean>() {
        @Override
        public void onBind(ItemView itemView, final MainListData.DataBean dataBean, int i) {
            //绑定数据
            itemView.setText(R.id.item_titail, dataBean.getProduct().getProductName());
            itemView.setText(R.id.item_sourcenum, "溯源码:" + dataBean.getOriginCode());
            itemView.setText(R.id.item_batchnum, "批次号:" + dataBean.getBatchCode());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ProduceEditerActivity.class);
                    intent.putExtra("id", String.valueOf(dataBean.getBatchId()));
                    intent.putExtra("productId", String.valueOf(dataBean.getProductId()));
                    startActivity(intent);
                }
            });
        }
    };

    List<MainListData.DataBean> list;

    private void initRecycler(List<MainListData.DataBean> list) {
        mainRecyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = SlideAdapter
                .load(list)
                .item(R.layout.item_main)
                .padding(2)
                .bind(itemBind)
                .into(mainRecyclerview);

    }

    @Override
    public void showData(MainListData data) {
        list = new ArrayList<>();
        list = data.getData();
//        if (pager == 1) {
//
//            dataBeans.clear()
//
//            dataBeans = dataBean as MutableList<SkillData.DataBean>
//                    initRecyclerview(dataBeans)
//        } else {
//            adapter!!.loadMore(dataBean)
//        }

        if (pageIndex == 1) {
//            list.clear();
//            list=data.getData();
            initRecycler(data.getData());
        } else {
            adapter.loadMore(data.getData());
        }
        pageIndex++;
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();

    }

    @Override
    public void error() {
        Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
    }


}
