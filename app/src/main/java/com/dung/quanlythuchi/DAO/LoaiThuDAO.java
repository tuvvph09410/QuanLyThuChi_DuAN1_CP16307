package com.dung.quanlythuchi.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.dung.quanlythuchi.DTO.LoaiThu;

import java.util.List;

@Dao
public interface LoaiThuDAO {
    @Query("SELECT * FROM loaithu")
    LiveData<List<LoaiThu>> findAll();

    @Insert
    void insert(LoaiThu loaiThu);

    @Update
    void update(LoaiThu loaiThu);

    @Delete
    void delete(LoaiThu loaiThu);

}
