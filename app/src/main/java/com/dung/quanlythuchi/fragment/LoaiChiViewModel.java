package com.dung.quanlythuchi.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dung.quanlythuchi.DTO.LoaiChi;
import com.dung.quanlythuchi.Repository.LoaiChiRepository;

import java.util.List;

public class LoaiChiViewModel extends AndroidViewModel {
    private LoaiChiRepository mloaiChiRepository;
    private LiveData<List<LoaiChi>> mAllLoaiChi;

    public LoaiChiViewModel(@NonNull Application application) {
        super(application);
        this.mloaiChiRepository = new LoaiChiRepository(application);
        this.mAllLoaiChi = this.mloaiChiRepository.getAllLoaiChi();

    }

    public LiveData<List<LoaiChi>> getAllLoaiChi() {
        return this.mAllLoaiChi;
    }

    public void insert(LoaiChi loaiChi) {
        this.mloaiChiRepository.insert(loaiChi);
    }

    public void delete(LoaiChi loaiChi) {
        this.mloaiChiRepository.delete(loaiChi);
    }

    public void update(LoaiChi loaiChi) {
        this.mloaiChiRepository.update(loaiChi);
    }
}
