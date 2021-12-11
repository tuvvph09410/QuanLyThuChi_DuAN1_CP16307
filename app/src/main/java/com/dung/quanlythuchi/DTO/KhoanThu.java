package com.dung.quanlythuchi.DTO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dung.quanlythuchi.DAO.DateTyperConvertor;

import java.util.Date;

@Entity
public class KhoanThu {
    //trường id là khoá chính và tự tăng;
    @PrimaryKey(autoGenerate = true)
    private int idKT;
    //định nghĩa tên của trường;
    @ColumnInfo(name = "lTID")
    private int ltID;

    @ColumnInfo(name = "nameKT")
    private String nameKT;

    @ColumnInfo(name = "tienKT")
    private Double tienKT;

    @ColumnInfo(name = "dateKT")
    //được sử dụng chuyển đổi các kiểu tuỳ chỉnh thành các kiểu hộ trợ bở Database
    @TypeConverters({DateTyperConvertor.class})
    private Date dateKT;
    
    @ColumnInfo(name = "noteKT")
    private String noteKT;

    public KhoanThu(String nameKT, int ltID, Double tienKT, Date dateKT, String noteKT) {
        this.nameKT = nameKT;
        this.tienKT = tienKT;
        this.dateKT = dateKT;
        this.noteKT = noteKT;
        this.ltID = ltID;
    }

    public int getIdKT() {
        return idKT;
    }

    public void setIdKT(int idKT) {
        this.idKT = idKT;
    }

    public int getLtID() {
        return ltID;
    }

    public void setLtID(int ltID) {
        this.ltID = ltID;
    }

    public String getNameKT() {
        return nameKT;
    }

    public void setNameKT(String nameKT) {
        this.nameKT = nameKT;
    }

    public Double getTienKT() {
        return tienKT;
    }

    public void setTienKT(Double tienKT) {
        this.tienKT = tienKT;
    }

    public Date getDateKT() {
        return dateKT;
    }

    public void setDateKT(Date dateKT) {
        this.dateKT = dateKT;
    }

    public String getNoteKT() {
        return noteKT;
    }

    public void setNoteKT(String noteKT) {
        this.noteKT = noteKT;
    }
}
