package com.dung.quanlythuchi.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.dung.quanlythuchi.DTO.KhoanChi;
import com.dung.quanlythuchi.DTO.LoaiChi;
import com.dung.quanlythuchi.R;
import com.dung.quanlythuchi.adapter.LoaiChiSpinnerAdapter;
import com.dung.quanlythuchi.fragment.KhoanChiViewModel;
import com.dung.quanlythuchi.fragment.LoaiChiViewModel;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class KhoanChiDetailDialog {
    private KhoanChiViewModel khoanChiViewModel;
    private LayoutInflater layoutInflater;
    private AlertDialog dialog;


    private boolean EditMode;
    private TextView tvIdKC, tvNameKC, tvNameLC, tvAmountKC, tvDateKC, tvNoteKC;

    public KhoanChiDetailDialog(Context context, KhoanChi... khoanChi) {

        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dialog_detail_khoanchi, null);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        this.khoanChiViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(KhoanChiViewModel.class);


        tvIdKC = view.findViewById(R.id.tv_dialogdetail_idKC);
        tvNameKC = view.findViewById(R.id.tv_dialogdetail_nameKC);
        tvNameLC = view.findViewById(R.id.tv_dialogdetail_nameLC);
        tvAmountKC = view.findViewById(R.id.tv_dialogdetail_amountKC);
        tvDateKC = view.findViewById(R.id.tv_dialogdetail_dateKC);
        tvNoteKC = view.findViewById(R.id.tv_dialogdetail_noteKC);

        tvIdKC.setText("ID khoản chi: " + khoanChi[0].getIdKC());
        tvNameKC.setText("Tên khoản chi: " + khoanChi[0].getNameKC());
        tvAmountKC.setText("Tiền khoản chi: " + khoanChi[0].getTienKC());
        tvDateKC.setText("Ngày khoản chi: " + simpleDateFormat.format(khoanChi[0].getDateKC()));
        tvNoteKC.setText("Ghi Chú khoản chi: " + khoanChi[0].getNoteKC());


        this.khoanChiViewModel.getAllLoaiChi().observe((LifecycleOwner) context, new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> loaiChis) {
                if (loaiChis != null) {
                    for (int i = 0; i < loaiChis.size(); i++) {
                        LoaiChi loaiChi = loaiChis.get(i);
                        if (loaiChi.getIdLC() == khoanChi[0].getLcID()) {
                            tvNameLC.setText("Tên loại chi: " + loaiChi.getNameLC());
                            Log.d("name",loaiChi.getNameLC());
                        }

                    }

                }


            }

        });


        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        dialog = builder.create();
    }

    public void showDialog() {
        dialog.show();
    }
}
