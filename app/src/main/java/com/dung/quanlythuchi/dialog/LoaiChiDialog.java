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
import com.dung.quanlythuchi.R;

import com.dung.quanlythuchi.fragment.LoaiChiViewModel;


public class LoaiChiDialog {
    private LoaiChiViewModel loaiChiViewModel;

    private LayoutInflater layoutInflater;
    private AlertDialog dialog;

    private EditText edName;
    private boolean EditMode;
    private TextView tvLC;

    public LoaiChiDialog(Context context, LoaiChi... loaiChi) {

        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dialog_loaichi, null);

        this.loaiChiViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(LoaiChiViewModel.class);

        edName = view.findViewById(R.id.edLC);
        tvLC = view.findViewById(R.id.tV_idLC);
        if (loaiChi != null && loaiChi.length > 0) {
            edName.setText(loaiChi[0].getNameLC());
            tvLC.setText("" + loaiChi[0].getIdLC());
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

                        String nameLC = edName.getText().toString();
                        LoaiChi loaiChi = new LoaiChi(nameLC);

                        if (EditMode) {
                            loaiChi.setIdLC(Integer.parseInt(tvLC.getText().toString()));
                            loaiChiViewModel.update(loaiChi);
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            loaiChiViewModel.insert(loaiChi);
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
