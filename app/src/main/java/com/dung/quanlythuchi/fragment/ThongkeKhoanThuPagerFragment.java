package com.dung.quanlythuchi.fragment;

import android.app.Application;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.dung.quanlythuchi.DTO.KhoanThu;
import com.dung.quanlythuchi.R;
import com.dung.quanlythuchi.adapter.ItemClickListener;
import com.dung.quanlythuchi.adapter.ThongKeThuRecyclerViewApdater;
import com.dung.quanlythuchi.dialog.KhoanThuDetailDialog;
import com.dung.quanlythuchi.dialog.KhoanThuDiaLog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongkeKhoanThuPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongkeKhoanThuPagerFragment extends Fragment {
    private EditText edToDateThu, edFromDateThu, edTotalThu;
    private RecyclerView rvThu;
    private Button btnTKThu;
    private Calendar calendar;
    private ThongKeThuRecyclerViewApdater thongKeThuRecyclerViewApdater;
    private ThongKeThuViewModel thongKeThuViewModel;


    public ThongkeKhoanThuPagerFragment() {

    }


    public static ThongkeKhoanThuPagerFragment newInstance() {
        ThongkeKhoanThuPagerFragment fragment = new ThongkeKhoanThuPagerFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thongke_khoan_thu_pager, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        this.edToDateThu = view.findViewById(R.id.ed_DateTuThu);
        this.edFromDateThu = view.findViewById(R.id.ed_DateDenThu);
        this.edTotalThu = view.findViewById(R.id.ed_TongThu);
        this.rvThu = view.findViewById(R.id.rv_TongThu);
        this.btnTKThu = view.findViewById(R.id.btn_TKThu);

        Calendar calendarToDate = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener toDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarToDate.set(Calendar.YEAR, year);
                calendarToDate.set(Calendar.MONTH, month);
                calendarToDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                this.updateCalendar();


            }

            private void updateCalendar() {
                edToDateThu.setText(simpleDateFormat.format(calendarToDate.getTime()));
            }
        };

        edToDateThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), toDate, calendarToDate.get(Calendar.YEAR), calendarToDate.get(Calendar.MONTH), calendarToDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        Calendar calendarfromDate = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener fromDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarfromDate.set(Calendar.YEAR, year);
                calendarfromDate.set(Calendar.MONTH, month);
                calendarfromDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                this.updateCalendar();

            }

            private void updateCalendar() {
                edFromDateThu.setText(simpleDateFormat.format(calendarfromDate.getTime()));
            }
        };

        edFromDateThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), fromDate, calendarfromDate.get(Calendar.YEAR), calendarfromDate.get(Calendar.MONTH), calendarfromDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        btnTKThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date toDateThu = calendarToDate.getTime();
                Date fromDateThu = calendarfromDate.getTime();

                if (edFromDateThu.getText().length() == 0 || edToDateThu.getText().length() == 0) {
                    Toast.makeText(getContext(), "Bạn chưa chọn ngày", Toast.LENGTH_SHORT).show();
                } else {

                    Long toDateLong = toDateThu.getTime();
                    Long fromDateLong = fromDateThu.getTime();


//                    Log.d("Time", toDateLong + " ----- " + fromDateLong);

                    thongKeThuViewModel.getAllAmountKhoanThu( toDateLong, fromDateLong).observe(getActivity(), new Observer<Float>() {
                        @Override
                        public void onChanged(Float floats) {

                            edTotalThu.setText(String.valueOf(floats));

                        }

                    });


                    thongKeThuRecyclerViewApdater = new ThongKeThuRecyclerViewApdater(getActivity());
                    rvThu.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rvThu.setAdapter(thongKeThuRecyclerViewApdater);

                    thongKeThuViewModel.getAllListDateTKThu( toDateLong, fromDateLong).observe(getActivity(), new Observer<List<KhoanThu>>() {
                        @Override
                        public void onChanged(List<KhoanThu> khoanThus) {
                            thongKeThuRecyclerViewApdater.setList(khoanThus);

                        }
                    });
                    thongKeThuRecyclerViewApdater.setOnItemClickListener(new ItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            KhoanThu khoanThu = thongKeThuRecyclerViewApdater.getItem(position);
                            KhoanThuDetailDialog khoanThuDetailDialog = new KhoanThuDetailDialog(getActivity(), khoanThu);
                            khoanThuDetailDialog.showDialog();
                        }
                    });
                }


            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        thongKeThuViewModel = new ViewModelProvider(ThongkeKhoanThuPagerFragment.this).get(ThongKeThuViewModel.class);
    }
}