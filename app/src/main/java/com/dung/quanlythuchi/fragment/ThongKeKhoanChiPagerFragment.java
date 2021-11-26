package com.dung.quanlythuchi.fragment;

import android.app.DatePickerDialog;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.dung.quanlythuchi.DTO.KhoanChi;
import com.dung.quanlythuchi.R;
import com.dung.quanlythuchi.adapter.ItemClickListener;
import com.dung.quanlythuchi.adapter.ThongKeChiRecyclerViewApdater;
import com.dung.quanlythuchi.dialog.KhoanChiDetailDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongKeKhoanChiPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongKeKhoanChiPagerFragment extends Fragment {
    private EditText edToDate, edFromDate, edTotalChi;
    private RecyclerView rvChi;
    private Button btn_TKChi;

    private ThongKeChiViewModel thongKeChiViewModel;
    private ThongKeChiRecyclerViewApdater thongKeChiRecyclerViewApdater;

    public ThongKeKhoanChiPagerFragment() {
        // Required empty public constructor
    }


    public static ThongKeKhoanChiPagerFragment newInstance() {
        ThongKeKhoanChiPagerFragment fragment = new ThongKeKhoanChiPagerFragment();

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
        View view = inflater.inflate(R.layout.fragment_thong_ke_khoan_chi_pager, container, false);
        edToDate = view.findViewById(R.id.ed_DateTuChi);
        edFromDate = view.findViewById(R.id.ed_DateDenChi);
        edTotalChi = view.findViewById(R.id.ed_TongChi);
        rvChi = view.findViewById(R.id.rv_TongChi);
        btn_TKChi = view.findViewById(R.id.btn_TKChi);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Calendar calendarToDate = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener datePickerDialog = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarToDate.set(Calendar.YEAR, year);
                calendarToDate.set(Calendar.MONTH, month);
                calendarToDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                this.updateDatePicker();

            }

            private void updateDatePicker() {
                edToDate.setText(simpleDateFormat.format(calendarToDate.getTime()));
            }
        };
        this.edToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(), datePickerDialog, calendarToDate.get(Calendar.YEAR), calendarToDate.get(Calendar.MONTH), calendarToDate.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        Calendar calendarFromDate = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarFromDate.set(Calendar.YEAR, year);
                calendarFromDate.set(Calendar.MONTH, month);
                calendarFromDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                this.updateDatePicker();
            }

            private void updateDatePicker() {
                edFromDate.setText(simpleDateFormat.format(calendarFromDate.getTime()));

            }
        };
        this.edFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(), dateSetListener, calendarFromDate.get(Calendar.YEAR), calendarFromDate.get(Calendar.MONTH), calendarFromDate.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        this.btn_TKChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date toDateThu = calendarToDate.getTime();
                Date fromDateThu = calendarFromDate.getTime();
                if (edToDate.getText().length() == 0 || edFromDate.getText().length() == 0) {
                    Toast.makeText(getContext(), "Bạn chưa chọn ngày", Toast.LENGTH_SHORT).show();

                } else {
                    Long toDateLong = toDateThu.getTime();
                    Long fromDateLong = fromDateThu.getTime();


                    thongKeChiViewModel.getAllTotalDateKhoanChi(toDateLong, fromDateLong).observe(getActivity(), new Observer<Float>() {
                        @Override
                        public void onChanged(Float aFloat) {
                            edTotalChi.setText(String.valueOf(aFloat));
                        }
                    });
                    thongKeChiRecyclerViewApdater = new ThongKeChiRecyclerViewApdater(getActivity());
                    rvChi.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rvChi.setAdapter(thongKeChiRecyclerViewApdater);
                    thongKeChiViewModel.getAllDateKhoanChi(toDateLong, fromDateLong).observe(getActivity(), new Observer<List<KhoanChi>>() {
                        @Override
                        public void onChanged(List<KhoanChi> khoanChis) {
                            thongKeChiRecyclerViewApdater.setList(khoanChis);
                        }
                    });
                    thongKeChiRecyclerViewApdater.setOnItemClickListener(new ItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            KhoanChi khoanChi = thongKeChiRecyclerViewApdater.getItem(position);
                            KhoanChiDetailDialog khoanChiDetailDialog = new KhoanChiDetailDialog(getActivity(), khoanChi);
                            khoanChiDetailDialog.showDialog();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        thongKeChiViewModel = new ViewModelProvider(ThongKeKhoanChiPagerFragment.this).get(ThongKeChiViewModel.class);
    }
}