package com.dung.quanlythuchi.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.dung.quanlythuchi.fragment.ThongKeKhoanChiPagerFragment;
import com.dung.quanlythuchi.fragment.ThongkeKhoanThuPagerFragment;

public class ThongKeFramentViewpagerAdapter extends FramentViewpagerAdapter{
    public ThongKeFramentViewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if (position == 0){
            fragment= ThongkeKhoanThuPagerFragment.newInstance();
        }else {
            fragment= ThongKeKhoanChiPagerFragment.newInstance();
        }
        return fragment;
    }
}
