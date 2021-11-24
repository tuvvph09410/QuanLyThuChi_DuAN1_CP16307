package com.dung.quanlythuchi.DTO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LoaiThu {

    @PrimaryKey(autoGenerate = true)
    private int idLT;
    @ColumnInfo(name = "nameLT")
    private String nameLT;

    public LoaiThu( String nameLT) {
        this.nameLT = nameLT;
    }

    public int getIdLT() {
        return idLT;
    }

    public void setIdLT(int idLT) {
        this.idLT = idLT;
    }

    public String getNameLT() {
        return nameLT;
    }

    public void setNameLT(String nameLT) {
        this.nameLT = nameLT;
    }
}
