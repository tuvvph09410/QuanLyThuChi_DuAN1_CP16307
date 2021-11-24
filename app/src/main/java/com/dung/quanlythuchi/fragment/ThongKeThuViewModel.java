package com.dung.quanlythuchi.fragment;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dung.quanlythuchi.DTO.KhoanThu;
import com.dung.quanlythuchi.Repository.KhoanThuRepository;

import java.util.Date;
import java.util.List;

public class ThongKeThuViewModel extends AndroidViewModel {
    private KhoanThuRepository khoanThuRepository;
    private LiveData<Float> mGetAllAmountKhoanThu;
    private LiveData<List<KhoanThu>> getListTKThu;



    public ThongKeThuViewModel(@NonNull Application application, Long toDate, Long fromDate) {
        super(application);
        this.khoanThuRepository = new KhoanThuRepository(application);
        this.mGetAllAmountKhoanThu = this.khoanThuRepository.getAllAmountKhoanThu(toDate, fromDate);
        this.getListTKThu =this.khoanThuRepository.getListTkThu(toDate,fromDate);
    }

    public LiveData<Float> getAllAmountKhoanThu() {
        return this.mGetAllAmountKhoanThu;
    }

    public LiveData<List<KhoanThu>> getAllListDateTKThu() {
        return this.getListTKThu;
    }
}
