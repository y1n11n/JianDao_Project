package com.example.seethewayproject.home.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.seethewayproject.R;
import com.example.seethewayproject.home.bean.SpecialBean;

import java.util.List;

public class SpecialAdapter extends BaseQuickAdapter<SpecialBean.DataBean.ListBean, BaseViewHolder> implements LoadMoreModule {

    public SpecialAdapter(int layoutResId, List<SpecialBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, SpecialBean.DataBean.ListBean listBean) {
        ImageView iv = holder.findView(R.id.iv_special);
        Glide.with(getContext()).load(listBean.getImage_url()).into(iv);
        holder.setText(R.id.tv_title,listBean.getTheme());
        holder.setText(R.id.tv_column_name, listBean.getColumn_name());
    }
}
