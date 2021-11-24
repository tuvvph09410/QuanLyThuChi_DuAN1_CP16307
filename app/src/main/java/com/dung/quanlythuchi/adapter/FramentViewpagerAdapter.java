package com.dung.quanlythuchi.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dung.quanlythuchi.fragment.KhoanChiPagerFragment;
import com.dung.quanlythuchi.fragment.LoaiChiPagerFragment;

public class FramentViewpagerAdapter extends FragmentStateAdapter {

    public FramentViewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if(position == 0){
            fragment= KhoanChiPagerFragment.newInstance();
        }else {
            fragment= LoaiChiPagerFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
