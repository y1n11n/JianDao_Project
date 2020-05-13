package com.example.seethewayproject.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class Vp2Adapter extends FragmentStateAdapter {
    List<Fragment> mFragments;

    public Vp2Adapter(@NonNull FragmentActivity fragmentActivity,List<Fragment> mFragments) {
        super(fragmentActivity);
        this.mFragments = mFragments;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }
}
