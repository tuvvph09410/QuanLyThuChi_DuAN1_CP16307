package com.dung.quanlythuchi.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dung.quanlythuchi.DTO.LoaiChi;
import com.dung.quanlythuchi.DTO.LoaiThu;
import com.dung.quanlythuchi.R;
import com.dung.quanlythuchi.adapter.ItemClickListener;
import com.dung.quanlythuchi.adapter.LoaiChiRecyclerViewAdapter;
import com.dung.quanlythuchi.adapter.LoaiThuRecyclerViewAdapter;
import com.dung.quanlythuchi.dialog.LoaiChiDetailDialog;
import com.dung.quanlythuchi.dialog.LoaiChiDialog;
import com.dung.quanlythuchi.dialog.LoaiThuDetailDialog;
import com.dung.quanlythuchi.dialog.LoaiThuDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoaiThuPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoaiThuPagerFragment extends Fragment {
    private FloatingActionButton floatingActionButtonThu;
    private LoaiThuViewModel loaiThuViewModel;
    private RecyclerView recyclerViewThu;
    private LoaiThuRecyclerViewAdapter loaiThuRecyclerViewAdapter;

    public LoaiThuPagerFragment() {
        // Required empty public constructor
    }


    public static LoaiThuPagerFragment newInstance() {
        LoaiThuPagerFragment fragment = new LoaiThuPagerFragment();

        return fragment;
    }

    public LoaiThuViewModel getViewModelThu() {
        return loaiThuViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loai_thu_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.recyclerViewThu = view.findViewById(R.id.RV_LoaiThu);
        this.loaiThuRecyclerViewAdapter = new LoaiThuRecyclerViewAdapter(getActivity());
        this.recyclerViewThu.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerViewThu.setAdapter(loaiThuRecyclerViewAdapter);

        this.loaiThuRecyclerViewAdapter.setOnItemUpdateClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiThu loaiThu = loaiThuRecyclerViewAdapter.getItem(position);
                LoaiThuDialog dialog = new LoaiThuDialog(getActivity(), loaiThu);
                dialog.showDialog();
            }
        });
        this.loaiThuRecyclerViewAdapter.setOnItemDeleteClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiThu loaiThu = loaiThuRecyclerViewAdapter.getItem(position);
                loaiThuViewModel.delete(loaiThu);
                Toast.makeText(getActivity(), "Xoá thành công", Toast.LENGTH_SHORT).show();
            }
        });
        this.loaiThuRecyclerViewAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiThu loaiThu = loaiThuRecyclerViewAdapter.getItem(position);
                LoaiThuDetailDialog loaiThuDetailDialog = new LoaiThuDetailDialog(getActivity(), loaiThu);
                loaiThuDetailDialog.showDialog();
            }
        });
        this.floatingActionButtonThu = view.findViewById(R.id.floatingActionButtonLoaiThu);
        this.floatingActionButtonThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiThuDialog dialog = new LoaiThuDialog(getActivity());
                dialog.showDialog();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.loaiThuViewModel = new ViewModelProvider(this).get(LoaiThuViewModel.class);
        this.loaiThuViewModel.getAllLoaiThu().observe(getActivity(), new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                loaiThuRecyclerViewAdapter.setLoaiThuList(loaiThus);
            }
        });
    }
}