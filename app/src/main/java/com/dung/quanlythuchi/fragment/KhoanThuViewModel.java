package com.dung.quanlythuchi.fragment;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dung.quanlythuchi.DTO.KhoanChi;
import com.dung.quanlythuchi.DTO.KhoanThu;
import com.dung.quanlythuchi.DTO.LoaiChi;
import com.dung.quanlythuchi.DTO.LoaiThu;
import com.dung.quanlythuchi.Repository.KhoanChiRepository;
import com.dung.quanlythuchi.Repository.KhoanThuRepository;
import com.dung.quanlythuchi.Repository.LoaiChiRepository;
import com.dung.quanlythuchi.Repository.LoaiThuRepository;

import java.util.List;

public class KhoanThuViewModel extends AndroidViewModel {
    private KhoanThuRepository mKhoanThuRepository;
    private LoaiThuRepository mLoaiThuRepository;
    private LiveData<List<KhoanThu>> mAllKhoanThu;
    private LiveData<List<LoaiThu>> mAllLoaiThu;

    public KhoanThuViewModel(@NonNull Application application) {
        super(application);
        this.mKhoanThuRepository = new KhoanThuRepository(application);
        this.mAllKhoanThu = this.mKhoanThuRepository.getAllKhoanThu();
        this.mLoaiThuRepository=new LoaiThuRepository(application);
        this.mAllLoaiThu=this.mLoaiThuRepository.getAllLoaiThu();
    }

    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return this.mAllLoaiThu;
    }

    public LiveData<List<KhoanThu>> getAllKhoanThu() {
        return this.mAllKhoanThu;
    }

    public void insert(KhoanThu khoanThu) {
        this.mKhoanThuRepository.insert(khoanThu);
    }

    public void update(KhoanThu khoanThu) {
        this.mKhoanThuRepository.update(khoanThu);
    }

    public void delete(KhoanThu khoanThu) {
        this.mKhoanThuRepository.delete(khoanThu);
    }
}
