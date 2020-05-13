package com.example.seethewayproject.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseLayFragment extends Fragment {

    private boolean isViewCreate = false;
    public boolean isDataLoad = false;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //true/false 可见/不可见

        lazyLoad();
    }

    private void lazyLoad(){
        //1.当前页面是否可见  2.当前页面是否已经加载 3. 当前页面数据是否已经加载
        //可见
        if (getUserVisibleHint() && isViewCreate && !isDataLoad){
            //加载数据
            initData();
            isDataLoad = true;
        }else {
            //不加载数据
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(getLayoutID(), container, false);
        isViewCreate = true;
        initView();
//        initData();
        initListener();
        return inflate;
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    public abstract int getLayoutID();
}
