package com.example.seethewayproject.home.adapter;

import android.webkit.WebView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.seethewayproject.R;
import com.example.seethewayproject.home.bean.ArticleListBean;

import java.util.List;

public class ItemWebAdapter extends BaseMultiItemQuickAdapter<ArticleListBean, BaseViewHolder>{

    public ItemWebAdapter(List<ArticleListBean> articleListBean) {
        addItemType(ArticleListBean.TYPE_HTML, R.layout.item_web_htel);//左图
    }


    @Override
    protected void convert(BaseViewHolder holder, ArticleListBean articleListBean) {
        switch (holder.getItemViewType()){
            case ArticleListBean.TYPE_HTML:
                initHtml(holder, articleListBean);
                break;
        }
    }

    private void initHtml(BaseViewHolder holder,ArticleListBean bean) {
        WebView wv = holder.findView(R.id.wv_item_web);
        wv.loadUrl(bean.getLead());
    }
}
