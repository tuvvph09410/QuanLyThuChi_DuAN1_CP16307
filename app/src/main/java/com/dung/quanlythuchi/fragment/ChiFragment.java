package com.dung.quanlythuchi.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.dung.quanlythuchi.R;
import com.dung.quanlythuchi.adapter.FramentViewpagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ChiFragment extends Fragment {

    View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi, container, false);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager);
        TabLayout tabLayout=view.findViewById(R.id.tapLayout);
        FramentViewpagerAdapter fragmentViewPagerAdapter = new FramentViewpagerAdapter(getActivity());
        viewPager2.setAdapter(fragmentViewPagerAdapter);

       new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
           @Override
           public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            if (position == 0){

                tab.setText("Khoản Chi");

            }else {

                tab.setText("Loại Chi");
            }
           }
       }
       ).attach();
        return view;

    }


}
