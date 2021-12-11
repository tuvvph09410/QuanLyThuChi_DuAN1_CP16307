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
import com.dung.quanlythuchi.DTO.KhoanThu;
import com.dung.quanlythuchi.DTO.LoaiChi;
import com.dung.quanlythuchi.R;
import com.dung.quanlythuchi.adapter.ItemClickListener;
import com.dung.quanlythuchi.adapter.KhoanChiRecyclerViewAdapter;
import com.dung.quanlythuchi.adapter.KhoanThuRecyclerViewAdapter;
import com.dung.quanlythuchi.adapter.LoaiChiSpinnerAdapter;
import com.dung.quanlythuchi.dialog.KhoanChiDetailDialog;
import com.dung.quanlythuchi.dialog.KhoanChiDiaLog;
import com.dung.quanlythuchi.dialog.KhoanThuDetailDialog;
import com.dung.quanlythuchi.dialog.KhoanThuDiaLog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KhoanThuPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KhoanThuPagerFragment extends Fragment {
    private AlertDialog dialog;

    private KhoanThuViewModel khoanThuViewModel;
    private RecyclerView recyclerViewThu;
    private KhoanThuRecyclerViewAdapter thuRecyclerViewAdapter;
    private FloatingActionButton floatingActionButtonThu;

    public KhoanThuPagerFragment() {

    }

    public static KhoanThuPagerFragment newInstance() {
        KhoanThuPagerFragment fragment = new KhoanThuPagerFragment();

        return fragment;
    }

    public KhoanThuViewModel getViewModelThu() {
        return this.khoanThuViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_khoan_thu_pager, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.recyclerViewThu = view.findViewById(R.id.RV_KhoanThu);
        this.thuRecyclerViewAdapter = new KhoanThuRecyclerViewAdapter(getActivity());
        this.recyclerViewThu.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerViewThu.setAdapter(thuRecyclerViewAdapter);


        this.thuRecyclerViewAdapter.setOnItemDeleteClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                KhoanThu khoanThu= thuRecyclerViewAdapter.getItem(position);

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

                khoanThuViewModel.delete(khoanThu);
                Toast.makeText(getActivity(), "Xoá thành công", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                dialog = builder.create();
//
//                dialog.show();
            }
        });
        this.thuRecyclerViewAdapter.setOnItemUpdateClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                KhoanThu khoanThu = thuRecyclerViewAdapter.getItem(position);
                KhoanThuDiaLog khoanThuDiaLog = new KhoanThuDiaLog(getActivity(), khoanThu);
                khoanThuDiaLog.showDialog();
            }
        });
        this.thuRecyclerViewAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                KhoanThu khoanThu=thuRecyclerViewAdapter.getItem(position);

                KhoanThuDetailDialog khoanThuDetailDialog=new KhoanThuDetailDialog(getActivity(),khoanThu);
                khoanThuDetailDialog.showDialog();
            }
        });

        this.floatingActionButtonThu = view.findViewById(R.id.floatingActionButtonKhoanThu);
        this.floatingActionButtonThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhoanThuDiaLog khoanThuDiaLog = new KhoanThuDiaLog(getActivity());
                khoanThuDiaLog.showDialog();
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //tạo ra viewModel
        this.khoanThuViewModel = new ViewModelProvider(this).get(KhoanThuViewModel.class);
        //quan sát dư liệu thay đổi và cập nhật lên recyclerView
        this.khoanThuViewModel.getAllKhoanThu().observe(getActivity(), new Observer<List<KhoanThu>>() {
            @Override
            public void onChanged(List<KhoanThu> khoanThus) {
                thuRecyclerViewAdapter.setKhoanThuList(khoanThus);
            }
        });
    }
}
