package com.dung.quanlythuchi.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.dung.quanlythuchi.adapter.LoaiThuSpinnerAdapter;
import com.dung.quanlythuchi.fragment.KhoanChiViewModel;
import com.dung.quanlythuchi.fragment.KhoanThuViewModel;
import com.dung.quanlythuchi.fragment.LoaiChiViewModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class KhoanThuDiaLog {
    private KhoanThuViewModel khoanThuViewModel;

    private LayoutInflater layoutInflaterThu;
    private AlertDialog mDialog;
    private LoaiThuSpinnerAdapter spinnerAdapterThu;
    private EditText ed_nameKT, ed_amountKT, ed_dateKT, ed_noteKT;
    private Spinner sp_typeIDLT;
    private boolean EditModeThu;
    private TextView tv_idKT;


    public KhoanThuDiaLog(Context context, KhoanThu... khoanThu) {
        layoutInflaterThu = LayoutInflater.from(context);
        View view = layoutInflaterThu.inflate(R.layout.khoan_thu_dialog, null);
        this.khoanThuViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(KhoanThuViewModel.class);

        this.ed_nameKT = view.findViewById(R.id.ed_nameKT);
        this.ed_amountKT = view.findViewById(R.id.ed_amountKT);
        this.ed_dateKT = view.findViewById(R.id.ed_dateKT);
        this.ed_noteKT = view.findViewById(R.id.ed_noteKT);
        this.sp_typeIDLT = view.findViewById(R.id.sp_TypeIDLT);
        this.tv_idKT = view.findViewById(R.id.tV_idKT);
        this.spinnerAdapterThu = new LoaiThuSpinnerAdapter(context);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateCalendar();
            }

            private void updateCalendar() {
                ed_dateKT.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        ed_dateKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        khoanThuViewModel.getAllLoaiThu().observe((LifecycleOwner) context, new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                spinnerAdapterThu.setLoaiThuList(loaiThus);

            }
        });
        sp_typeIDLT.setAdapter(spinnerAdapterThu);

        if (khoanThu != null && khoanThu.length > 0) {
            tv_idKT.setText("" + khoanThu[0].getIdKT());
            ed_nameKT.setText(khoanThu[0].getNameKT());
            DecimalFormat decimalFormat = new DecimalFormat("#########");
            ed_amountKT.setText("" + decimalFormat.format(khoanThu[0].getTienKT()));

            ed_dateKT.setText(simpleDateFormat.format(khoanThu[0].getDateKT()));
            ed_noteKT.setText(khoanThu[0].getNoteKT());


            sp_typeIDLT.setSelection(0);


            EditModeThu = true;
        } else {
            EditModeThu = false;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context).setView(view).setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("Lưu", null);
        mDialog = builder.create();
        mDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button positiveButton = mDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ed_nameKT.getText().length() != 0 && ed_amountKT.getText().length() != 0 && ed_dateKT.getText().length() != 0) {
                            String nameKT = ed_nameKT.getText().toString();

                            String date = ed_dateKT.getText().toString();
                            Date dateKT = null;
                            try {
                                dateKT = simpleDateFormat.parse(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String noteKT = ed_noteKT.getText().toString();
                            try {
                                Double amountKT = Double.parseDouble(ed_amountKT.getText().toString());
                                int nameLT = ((LoaiThu) spinnerAdapterThu.getItem(sp_typeIDLT.getSelectedItemPosition())).getIdLT();
                                ed_amountKT.setTextColor(Color.WHITE);
                                KhoanThu khoanThu = new KhoanThu(nameKT, nameLT, amountKT, dateKT, noteKT);

                                if (EditModeThu) {
                                    khoanThu.setIdKT(Integer.parseInt(tv_idKT.getText().toString()));
                                    khoanThuViewModel.update(khoanThu);
                                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    khoanThuViewModel.insert(khoanThu);
                                    Toast.makeText(context, "Lưu thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "Nhập sai định dang tiền, Vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                                ed_amountKT.setTextColor(Color.RED);
                            }

                        } else {
                            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void showDialog() {
        mDialog.show();
    }
}
