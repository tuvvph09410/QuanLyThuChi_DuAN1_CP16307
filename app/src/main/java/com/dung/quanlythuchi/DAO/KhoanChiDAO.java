package com.dung.quanlythuchi.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dung.quanlythuchi.DTO.KhoanChi;

import java.util.List;

@Dao
public interface KhoanChiDAO {
    @Query("SELECT * FROM khoanchi")
    LiveData<List<KhoanChi>> findAll();

    @Query("SELECT SUM(tienKC) FROM khoanchi WHERE dateKC BETWEEN :toDate AND :fromDate")
    LiveData<Float> getTotalDateChi(Long toDate, Long fromDate);

    @Query("SELECT * FROM khoanchi WHERE dateKC BETWEEN :toDate AND :fromDate")
    LiveData<List<KhoanChi>> getAllDateChi(Long toDate, Long fromDate);

    @Insert
    void insert(KhoanChi khoanChi);

    @Update
    void update(KhoanChi khoanChi);

    @Delete
    void delete(KhoanChi khoanChi);

}
