package com.dung.quanlythuchi.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.dung.quanlythuchi.DTO.LoaiThu;

import com.dung.quanlythuchi.Repository.LoaiThuRepository;

import java.util.List;

public class LoaiThuViewModel extends AndroidViewModel {
    private LoaiThuRepository mloaiThuRepository;
    private LiveData<List<LoaiThu>> mAllLoaiThu;

    public LoaiThuViewModel(@NonNull Application application) {
        super(application);
        this.mloaiThuRepository = new LoaiThuRepository(application);
        this.mAllLoaiThu = this.mloaiThuRepository.getAllLoaiThu();

    }

    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return this.mAllLoaiThu;
    }

    public void insert(LoaiThu loaiThu) {
        this.mloaiThuRepository.insert(loaiThu);
    }

    public void delete(LoaiThu loaiThu) {
        this.mloaiThuRepository.delete(loaiThu);
    }

    public void update(LoaiThu loaiThu) {
        this.mloaiThuRepository.update(loaiThu);
    }
}
