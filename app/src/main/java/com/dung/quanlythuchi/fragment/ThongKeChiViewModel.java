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
    public ThongKeChiViewModel(@NonNull Application application) {
        super(application);
        this.mkhoanChiRepository=new KhoanChiRepository(application);


    }

    public LiveData<Float> getAllTotalDateKhoanChi(Long toDate,Long fromDate) {
        return mkhoanChiRepository.getAllTotalDateKhoanChi(toDate,fromDate);
    }

    public LiveData<List<KhoanChi>> getAllDateKhoanChi(Long toDate,Long fromDate) {
        return mkhoanChiRepository.getAllDateKhoanChi(toDate,fromDate);
    }
}
