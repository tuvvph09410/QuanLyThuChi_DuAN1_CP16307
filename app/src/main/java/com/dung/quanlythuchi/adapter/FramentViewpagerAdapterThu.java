package com.dung.quanlythuchi.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.dung.quanlythuchi.fragment.KhoanThuPagerFragment;

import com.dung.quanlythuchi.fragment.LoaiThuPagerFragment;

public class FramentViewpagerAdapterThu extends FragmentStateAdapter {

    public FramentViewpagerAdapterThu(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if(position == 0){
            fragment= KhoanThuPagerFragment.newInstance();
        }else {
            fragment= LoaiThuPagerFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
