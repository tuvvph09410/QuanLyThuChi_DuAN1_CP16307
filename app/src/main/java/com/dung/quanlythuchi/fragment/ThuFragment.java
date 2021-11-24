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
import com.dung.quanlythuchi.adapter.FramentViewpagerAdapterThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ThuFragment extends Fragment {

    View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thu, container, false);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPagerThu);
        TabLayout tabLayout=view.findViewById(R.id.tapLayoutThu);
        FramentViewpagerAdapterThu fragmentViewPagerAdapterThu = new FramentViewpagerAdapterThu(getActivity());
        viewPager2.setAdapter(fragmentViewPagerAdapterThu);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0){

                    tab.setText("Khoản Thu");

                }else {

                    tab.setText("Loại Thu");
                }
            }
        }
        ).attach();
        return view;

    }


}
