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
import com.dung.quanlythuchi.DTO.KhoanThu;
import com.dung.quanlythuchi.DTO.LoaiChi;
import com.dung.quanlythuchi.DTO.LoaiThu;
import com.dung.quanlythuchi.R;
import com.dung.quanlythuchi.adapter.LoaiChiSpinnerAdapter;
import com.dung.quanlythuchi.fragment.KhoanChiViewModel;
import com.dung.quanlythuchi.fragment.KhoanThuViewModel;
import com.dung.quanlythuchi.fragment.LoaiChiViewModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class KhoanThuDetailDialog {
    private KhoanThuViewModel khoanThuViewModel;
    private LayoutInflater layoutInflaterThu;
    private AlertDialog dialog;


    private boolean EditMode;
    private TextView tvIdKT, tvNameKT, tvNameLT, tvAmountKT, tvDateKT, tvNoteKT;

    public KhoanThuDetailDialog(Context context, KhoanThu... khoanThu) {

        layoutInflaterThu = LayoutInflater.from(context);
        View view = layoutInflaterThu.inflate(R.layout.dialog_detail_khoanthu, null);

        this.khoanThuViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(KhoanThuViewModel.class);


        tvIdKT = view.findViewById(R.id.tv_dialogdetail_idKT);
        tvNameKT = view.findViewById(R.id.tv_dialogdetail_nameKT);
        tvNameLT = view.findViewById(R.id.tv_dialogdetail_nameLT);
        tvAmountKT = view.findViewById(R.id.tv_dialogdetail_amountKT);
        tvDateKT = view.findViewById(R.id.tv_dialogdetail_dateKT);
        tvNoteKT = view.findViewById(R.id.tv_dialogdetail_noteKT);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        tvIdKT.setText("ID kho???n thu: " + khoanThu[0].getIdKT());
        tvNameKT.setText("T??n kho???n thu: " + khoanThu[0].getNameKT());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        tvAmountKT.setText("Ti???n kho???n thu: " +decimalFormat.format( khoanThu[0].getTienKT()));
        tvDateKT.setText("Ng??y kho???n thu: " + simpleDateFormat.format(khoanThu[0].getDateKT()));
        tvNoteKT.setText("Ghi Ch?? kho???n thu: " + khoanThu[0].getNoteKT());

        this.khoanThuViewModel.getAllLoaiThu().observe((LifecycleOwner) context, new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                if (loaiThus != null) {
                    for (int i = 0; i < loaiThus.size(); i++) {
                        LoaiThu loaiThu = loaiThus.get(i);
                        if (loaiThu.getIdLT() == khoanThu[0].getLtID()) {
                            tvNameLT.setText("T??n lo???i thu: " + loaiThu.getNameLT());
                            Log.d("name",loaiThu.getNameLT());
                        }

                    }

                }


            }

        });


        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("????ng", new DialogInterface.OnClickListener() {
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
