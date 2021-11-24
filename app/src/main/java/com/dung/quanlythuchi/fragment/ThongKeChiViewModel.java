package com.dung.quanlythuchi.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dung.quanlythuchi.DTO.KhoanChi;
import com.dung.quanlythuchi.Repository.KhoanChiRepository;

import java.util.List;

public class ThongKeChiViewModel extends AndroidViewModel {
    private KhoanChiRepository mkhoanChiRepository;
    private LiveData<Float> mGetAllTotalDateKhoanChi;
    private LiveData<List<KhoanChi>> mGetAllDateKhoanChi;
    public ThongKeChiViewModel(@NonNull Application application,Long toDate,Long fromDate) {
        super(application);
        this.mkhoanChiRepository=new KhoanChiRepository(application);
        this.mGetAllTotalDateKhoanChi=mkhoanChiRepository.getAllTotalDateKhoanChi(toDate,fromDate);
        this.mGetAllDateKhoanChi=mkhoanChiRepository.getAllDateKhoanChi(toDate,fromDate);

    }

    public LiveData<Float> getAllTotalDateKhoanChi() {
        return mGetAllTotalDateKhoanChi;
    }

    public LiveData<List<KhoanChi>> getAllDateKhoanChi() {
        return mGetAllDateKhoanChi;
    }
}
