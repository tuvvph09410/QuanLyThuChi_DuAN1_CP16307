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


public class LoaiChiDetailDialog {
    private LoaiChiViewModel loaiChiViewModel;
    private LayoutInflater layoutInflater;
    private AlertDialog dialog;


    private boolean EditMode;
    private TextView tvLC, tvNameLC;

    public LoaiChiDetailDialog(Context context, LoaiChi... loaiChi) {

        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dialog_detail_loaichi, null);

        this.loaiChiViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(LoaiChiViewModel.class);

        tvNameLC = view.findViewById(R.id.tv_dialogdetail_nameLC);
        tvLC = view.findViewById(R.id.tv_dialogdetail_IDLC);
        tvNameLC.setText("Tên loại chi: " + loaiChi[0].getNameLC());
        tvLC.setText("ID loại chi: " + loaiChi[0].getIdLC());

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
