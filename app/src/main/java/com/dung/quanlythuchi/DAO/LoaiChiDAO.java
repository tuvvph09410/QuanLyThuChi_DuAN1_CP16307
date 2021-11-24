package com.dung.quanlythuchi.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dung.quanlythuchi.DTO.LoaiChi;

import java.util.List;

@Dao
public interface LoaiChiDAO {
    @Query("SELECT * FROM loaichi")
    LiveData<List<LoaiChi>> findAll();

    @Insert
    void insert(LoaiChi loaiChi);

    @Update
    void update(LoaiChi loaiChi);

    @Delete
    void delete(LoaiChi loaiChi);

}
