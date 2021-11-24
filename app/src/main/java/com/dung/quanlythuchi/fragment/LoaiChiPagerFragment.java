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
import com.dung.quanlythuchi.R;
import com.dung.quanlythuchi.adapter.ItemClickListener;
import com.dung.quanlythuchi.adapter.LoaiChiRecyclerViewAdapter;
import com.dung.quanlythuchi.dialog.LoaiChiDetailDialog;
import com.dung.quanlythuchi.dialog.LoaiChiDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoaiChiPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoaiChiPagerFragment extends Fragment {
    private FloatingActionButton floatingActionButton;
    private LoaiChiViewModel loaiChiViewModel;
    private RecyclerView recyclerView;
    private LoaiChiRecyclerViewAdapter loaiChiRecyclerViewAdapter;

    public LoaiChiPagerFragment() {
        // Required empty public constructor
    }


    public static LoaiChiPagerFragment newInstance() {
        LoaiChiPagerFragment fragment = new LoaiChiPagerFragment();

        return fragment;
    }

    public LoaiChiViewModel getViewModel() {
        return loaiChiViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loai_chi_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.recyclerView = view.findViewById(R.id.RV_LoaiChi);
        this.loaiChiRecyclerViewAdapter = new LoaiChiRecyclerViewAdapter(getActivity());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(loaiChiRecyclerViewAdapter);

        this.loaiChiRecyclerViewAdapter.setOnItemUpdateClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiChi loaiChi = loaiChiRecyclerViewAdapter.getItem(position);
                LoaiChiDialog dialog = new LoaiChiDialog(getActivity(), loaiChi);
                dialog.showDialog();
            }
        });
        this.loaiChiRecyclerViewAdapter.setOnItemDeleteClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiChi loaiChi = loaiChiRecyclerViewAdapter.getItem(position);
                loaiChiViewModel.delete(loaiChi);
                Toast.makeText(getActivity(), "Xoá thành công", Toast.LENGTH_SHORT).show();
            }
        });
        this.loaiChiRecyclerViewAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiChi loaiChi = loaiChiRecyclerViewAdapter.getItem(position);
                LoaiChiDetailDialog loaiChiDetailDialog = new LoaiChiDetailDialog(getActivity(), loaiChi);
                loaiChiDetailDialog.showDialog();
            }
        });
        this.floatingActionButton = view.findViewById(R.id.floatingActionButtonLoaiChi);
        this.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiChiDialog dialog = new LoaiChiDialog(getActivity());
                dialog.showDialog();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.loaiChiViewModel = new ViewModelProvider(this).get(LoaiChiViewModel.class);
        this.loaiChiViewModel.getAllLoaiChi().observe(getActivity(), new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> loaiChis) {
                loaiChiRecyclerViewAdapter.setLoaiChiList(loaiChis);
            }
        });
    }
}