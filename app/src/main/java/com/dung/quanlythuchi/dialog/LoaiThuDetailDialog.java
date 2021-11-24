package com.dung.quanlythuchi.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.dung.quanlythuchi.DTO.LoaiChi;
import com.dung.quanlythuchi.DTO.LoaiThu;
import com.dung.quanlythuchi.R;
import com.dung.quanlythuchi.fragment.LoaiChiViewModel;
import com.dung.quanlythuchi.fragment.LoaiThuViewModel;


public class LoaiThuDetailDialog {
    private LoaiThuViewModel loaiThuViewModel;
    private LayoutInflater layoutInflaterThu;
    private AlertDialog dialog;


    private boolean EditMode;
    private TextView tvLT, tvNameLT;

    public LoaiThuDetailDialog(Context context, LoaiThu... loaiThu) {

        layoutInflaterThu = LayoutInflater.from(context);
        View view = layoutInflaterThu.inflate(R.layout.dialog_detail_loaithu, null);

        this.loaiThuViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(LoaiThuViewModel.class);

        tvNameLT = view.findViewById(R.id.tv_dialogdetail_nameLT);
        tvLT = view.findViewById(R.id.tv_dialogdetail_IDLT);
        tvNameLT.setText("Tên loại thu: " + loaiThu[0].getNameLT());
        tvLT.setText("ID loại thu: " + loaiThu[0].getIdLT());

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
