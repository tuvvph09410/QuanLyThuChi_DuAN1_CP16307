package com.dung.quanlythuchi.DAO;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.dung.quanlythuchi.DTO.KhoanThu;

import java.util.Date;
import java.util.List;
import java.util.Queue;

@Dao
public interface KhoanThuDAO {
    @Query("SELECT * FROM khoanthu")
    LiveData<List<KhoanThu>> findAll();

    @Query("SELECT SUM(tienKT) FROM khoanthu WHERE dateKT BETWEEN :toDate AND :fromDate ")
    LiveData<Float> getTotalAmountDateKhoanThu(Long toDate, Long fromDate);

    @Query("SELECT * FROM khoanthu WHERE dateKT BETWEEN :toDate AND :fromDate ")
    LiveData<List<KhoanThu>> getListThu(Long toDate, Long fromDate);

    @Query("SELECT sum(tienKT) FROM khoanthu")
    LiveData<Float> getTotalThu();

    @Insert
    void insert(KhoanThu khoanThu);

    @Update
    void update(KhoanThu khoanThu);

    @Delete
    void delete(KhoanThu khoanThu);

}
