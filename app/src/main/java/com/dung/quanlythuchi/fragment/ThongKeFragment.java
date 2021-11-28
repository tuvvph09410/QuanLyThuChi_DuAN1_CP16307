package com.dung.quanlythuchi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.widget.ViewPager2;


import com.dung.quanlythuchi.R;
import com.dung.quanlythuchi.adapter.ThongKeFramentViewpagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class ThongKeFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewPager2;

    ThongKeFramentViewpagerAdapter thongKeFramentViewpagerAdapter;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        viewPager2 = view.findViewById(R.id.viewPager2ThongKe);
        tabLayout = view.findViewById(R.id.tapLayoutThongKe);
        thongKeFramentViewpagerAdapter = new ThongKeFramentViewpagerAdapter(getActivity());
        viewPager2.setAdapter(thongKeFramentViewpagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Thông Kê Thu");

                } else {
                    tab.setText("Thông Kê Chi");
                }
            }
        }).attach();

        return view;

    }


}
