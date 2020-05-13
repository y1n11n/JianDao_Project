package com.example.seethewayproject.home.view;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seethewayproject.R;
import com.example.seethewayproject.base.BaseActivity;
import com.example.seethewayproject.base.BasePresenter;
import com.example.seethewayproject.home.bean.RecommendListBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemWebActivity extends BaseActivity {

    @BindView(R.id.item_web_toolbar)
    Toolbar mItemWebToolbar;
    @BindView(R.id.rlv_item_web)
    RecyclerView mRlvItemWeb;
    @BindView(R.id.iv_back)
    ImageView mIvBack;

    @Override
    public int getLayoutId() {
        return R.layout.activity_item_web;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initView() {
        mItemWebToolbar.setTitle("");
        setSupportActionBar(mItemWebToolbar);
        Bundle rec_item_bundle = getIntent().getBundleExtra("rec_item_bundle");
        int position = rec_item_bundle.getInt("itemData_position");
        RecommendListBean listBean = (RecommendListBean) rec_item_bundle.getSerializable("itemData");
        
        Log.d("Bundle传递数据--", "position：" + position + ",数据：" + listBean.toString());
        mRlvItemWeb.setLayoutManager(new LinearLayoutManager(this));
//        ItemWebAdapter adapter = new ItemWebAdapter();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_web, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
