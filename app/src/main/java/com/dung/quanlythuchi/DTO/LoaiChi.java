package com.dung.quanlythuchi.DTO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LoaiChi {

    @PrimaryKey(autoGenerate = true)
    private int idLC;
    @ColumnInfo(name = "nameLC")
    private String nameLC;

    public LoaiChi( String nameLC) {
        this.nameLC = nameLC;
    }

    public int getIdLC() {
        return idLC;
    }

    public void setIdLC(int idLC) {
        this.idLC = idLC;
    }

    public String getNameLC() {
        return nameLC;
    }

    public void setNameLC(String nameLC) {
        this.nameLC = nameLC;
    }
}
