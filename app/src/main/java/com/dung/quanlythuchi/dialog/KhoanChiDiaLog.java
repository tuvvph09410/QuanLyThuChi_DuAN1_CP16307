package com.dung.quanlythuchi.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import com.dung.quanlythuchi.DTO.LoaiChi;
import com.dung.quanlythuchi.R;
import com.dung.quanlythuchi.adapter.LoaiChiSpinnerAdapter;
import com.dung.quanlythuchi.fragment.KhoanChiViewModel;
import com.dung.quanlythuchi.fragment.LoaiChiViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class KhoanChiDiaLog {
    private KhoanChiViewModel khoanChiViewModel;

    private LayoutInflater layoutInflater;
    private AlertDialog mDialog;
    private LoaiChiSpinnerAdapter spinnerAdapter;
    private EditText ed_nameKC, ed_amountKC, ed_dateKC, ed_noteKC;
    private Spinner sp_typeIDLC;
    private boolean EditMode;
    private TextView tv_idKC;

    private Calendar calendar;

    public KhoanChiDiaLog(Context context, KhoanChi... khoanChi) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.khoan_chi_dialog, null);
        this.khoanChiViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(KhoanChiViewModel.class);

        this.ed_nameKC = view.findViewById(R.id.ed_nameKC);
        this.ed_amountKC = view.findViewById(R.id.ed_amountKC);
        this.ed_dateKC = view.findViewById(R.id.ed_dateKC);
        this.ed_noteKC = view.findViewById(R.id.ed_noteKC);
        this.sp_typeIDLC = view.findViewById(R.id.sp_TypeIDLC);
        this.tv_idKC = view.findViewById(R.id.tV_idKC);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        this.spinnerAdapter = new LoaiChiSpinnerAdapter(context);

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                this.updateCalendar();
            }

            private void updateCalendar() {

                ed_dateKC.setText(simpleDateFormat.format(calendar.getTime()));


            }
        };
        this.ed_dateKC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        khoanChiViewModel.getAllLoaiChi().observe((LifecycleOwner) context, new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> loaiChis) {
                spinnerAdapter.setLoaiChiList(loaiChis);

            }
        });
        sp_typeIDLC.setAdapter(spinnerAdapter);

        if (khoanChi != null && khoanChi.length > 0) {
            tv_idKC.setText(String.valueOf(khoanChi[0].getIdKC()));
            ed_nameKC.setText(khoanChi[0].getNameKC());
            ed_amountKC.setText(String.valueOf(khoanChi[0].getTienKC()));
            ed_dateKC.setText(simpleDateFormat.format(khoanChi[0].getDateKC()));
            ed_noteKC.setText(khoanChi[0].getNoteKC());

            if (String.valueOf(khoanChi[0].getLcID()) != null) {
                int spPosition = (int) spinnerAdapter.getItemId(khoanChi[0].getLcID() - 1);
                sp_typeIDLC.setSelection(spPosition);

            }


            EditMode = true;
        } else {
            EditMode = false;
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
                        if (ed_nameKC.getText().length() != 0 && ed_amountKC.getText().length() != 0 && ed_dateKC.getText().length() != 0) {
                            String nameKC = ed_nameKC.getText().toString();

                            String date = ed_dateKC.getText().toString();
                            Date dateKC = null;
                            try {
                                dateKC = simpleDateFormat.parse(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String noteKC = ed_noteKC.getText().toString();

                            try {
                                Double amountKC = Double.parseDouble(ed_amountKC.getText().toString());
                                ed_amountKC.setTextColor(Color.WHITE);
                                int nameLC = ((LoaiChi) spinnerAdapter.getItem(sp_typeIDLC.getSelectedItemPosition())).getIdLC();
                                KhoanChi khoanChi = new KhoanChi(nameKC, nameLC, amountKC, dateKC, noteKC);

                                if (EditMode) {
                                    khoanChi.setIdKC(Integer.parseInt(tv_idKC.getText().toString()));
                                    khoanChiViewModel.update(khoanChi);
                                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    khoanChiViewModel.insert(khoanChi);
                                    Toast.makeText(context, "Lưu thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                ed_amountKC.setTextColor(Color.RED);
                                Toast.makeText(context, "Vui lòng nhập đúng định dạng tiền", Toast.LENGTH_SHORT).show();

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
