package com.dung.quanlythuchi.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dung.quanlythuchi.DTO.KhoanChi;
import com.dung.quanlythuchi.DTO.LoaiChi;
import com.dung.quanlythuchi.R;
import com.dung.quanlythuchi.adapter.ItemClickListener;
import com.dung.quanlythuchi.adapter.KhoanChiRecyclerViewAdapter;
import com.dung.quanlythuchi.adapter.LoaiChiSpinnerAdapter;
import com.dung.quanlythuchi.dialog.KhoanChiDetailDialog;
import com.dung.quanlythuchi.dialog.KhoanChiDiaLog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KhoanChiPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KhoanChiPagerFragment extends Fragment {
    private AlertDialog dialog;

    private KhoanChiViewModel khoanChiViewModel;
    private RecyclerView recyclerView;
    private KhoanChiRecyclerViewAdapter chiRecyclerViewAdapter;
    private FloatingActionButton floatingActionButton;

    public KhoanChiPagerFragment() {

    }

    public static KhoanChiPagerFragment newInstance() {
        KhoanChiPagerFragment fragment = new KhoanChiPagerFragment();

        return fragment;
    }

    public KhoanChiViewModel getViewModel() {
        return this.khoanChiViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_khoan_chi_pager, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.recyclerView = view.findViewById(R.id.RV_KhoanChi);
        this.chiRecyclerViewAdapter = new KhoanChiRecyclerViewAdapter(getActivity());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(chiRecyclerViewAdapter);


        this.chiRecyclerViewAdapter.setOnItemDeleteClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                KhoanChi khoanChi = chiRecyclerViewAdapter.getItem(position);

//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
//                        .setView(view)
//                        .setTitle("Bạn có muốn bản ghi này không ?")
//                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        }).setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {

                                khoanChiViewModel.delete(khoanChi);
                                Toast.makeText(getActivity(), "Xoá thành công", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                dialog = builder.create();
//
//                dialog.show();
            }
        });
        this.chiRecyclerViewAdapter.setOnItemUpdateClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                KhoanChi khoanChi = chiRecyclerViewAdapter.getItem(position);
                KhoanChiDiaLog khoanChiDiaLog = new KhoanChiDiaLog(getActivity(), khoanChi);

                khoanChiDiaLog.showDialog();
            }
        });
        this.chiRecyclerViewAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
            KhoanChi khoanChi=chiRecyclerViewAdapter.getItem(position);

                KhoanChiDetailDialog khoanChiDetailDialog=new KhoanChiDetailDialog(getActivity(),khoanChi);
                khoanChiDetailDialog.showDialog();
            }
        });

        this.floatingActionButton = view.findViewById(R.id.floatingActionButtonKhoanChi);
        this.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhoanChiDiaLog khoanChiDiaLog = new KhoanChiDiaLog(getActivity());
                khoanChiDiaLog.showDialog();
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.khoanChiViewModel = new ViewModelProvider(this).get(KhoanChiViewModel.class);
        this.khoanChiViewModel.getAllKhoanChi().observe(getActivity(), new Observer<List<KhoanChi>>() {
            @Override
            public void onChanged(List<KhoanChi> khoanChis) {
                chiRecyclerViewAdapter.setKhoanChiList(khoanChis);
            }
        });
    }
}
