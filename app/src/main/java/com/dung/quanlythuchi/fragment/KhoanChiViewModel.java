package com.dung.quanlythuchi.fragment;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dung.quanlythuchi.DTO.KhoanChi;
import com.dung.quanlythuchi.DTO.LoaiChi;
import com.dung.quanlythuchi.Repository.KhoanChiRepository;
import com.dung.quanlythuchi.Repository.LoaiChiRepository;

import java.util.List;

public class KhoanChiViewModel extends AndroidViewModel {
    private KhoanChiRepository mKhoanChiRepository;
    private LoaiChiRepository mLoaiChiRepository;
    private LiveData<List<KhoanChi>> mAllKhoanChi;
    private LiveData<List<LoaiChi>> mAllLoaiChi;

    public KhoanChiViewModel(@NonNull Application application) {
        super(application);
        this.mKhoanChiRepository = new KhoanChiRepository(application);
        this.mAllKhoanChi = this.mKhoanChiRepository.getAllKhoanChi();
        this.mLoaiChiRepository=new LoaiChiRepository(application);
        this.mAllLoaiChi=this.mLoaiChiRepository.getAllLoaiChi();
    }

    public LiveData<List<LoaiChi>> getAllLoaiChi() {
        return this.mAllLoaiChi;
    }

    public LiveData<List<KhoanChi>> getAllKhoanChi() {
        return this.mAllKhoanChi;
    }

    public void insert(KhoanChi khoanChi) {
        this.mKhoanChiRepository.insert(khoanChi);
    }

    public void update(KhoanChi khoanChi) {
        this.mKhoanChiRepository.update(khoanChi);
    }

    public void delete(KhoanChi khoanChi) {
        this.mKhoanChiRepository.delete(khoanChi);
    }
}
