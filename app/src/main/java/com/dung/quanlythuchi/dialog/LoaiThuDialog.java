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


import com.dung.quanlythuchi.DTO.LoaiThu;
import com.dung.quanlythuchi.R;


import com.dung.quanlythuchi.fragment.LoaiThuViewModel;


public class LoaiThuDialog {
    private LoaiThuViewModel loaiThuViewModel;

    private LayoutInflater layoutInflaterThu;
    private AlertDialog dialog;

    private EditText edName;
    private boolean EditMode;
    private TextView tvLT;

    public LoaiThuDialog(Context context, LoaiThu... loaiThu) {

        layoutInflaterThu = LayoutInflater.from(context);
        View view = layoutInflaterThu.inflate(R.layout.dialog_loaithu, null);

        this.loaiThuViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(LoaiThuViewModel.class);

        edName = view.findViewById(R.id.edLT);
        tvLT = view.findViewById(R.id.tV_idLT);
        if (loaiThu != null && loaiThu.length > 0) {
            edName.setText(loaiThu[0].getNameLT());
            tvLT.setText("" + loaiThu[0].getIdLT());
            EditMode = true;
        } else {
            EditMode = false;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String nameLT = edName.getText().toString();
                        LoaiThu loaiThu = new LoaiThu(nameLT);
                        if (EditMode) {
                            loaiThu.setIdLT(Integer.parseInt(tvLT.getText().toString()));
                            loaiThuViewModel.update(loaiThu);
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            loaiThuViewModel.insert(loaiThu);
                            Toast.makeText(context, "Lưu thành công", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        dialog = builder.create();
    }

    public void showDialog() {
        dialog.show();
    }
}
