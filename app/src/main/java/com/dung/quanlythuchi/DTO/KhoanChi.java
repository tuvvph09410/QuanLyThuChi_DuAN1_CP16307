package com.dung.quanlythuchi.DTO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dung.quanlythuchi.DAO.DateTyperConvertor;

import java.util.Date;

@Entity
public class KhoanChi {
    @PrimaryKey(autoGenerate = true)
    private int idKC;
    @ColumnInfo(name = "lCID")
    private int lcID;
    @ColumnInfo(name = "nameKC")
    private String nameKC;
    @ColumnInfo(name = "tienKC")
    private Double tienKC;
    @ColumnInfo(name = "dateKC")
    @TypeConverters(DateTyperConvertor.class)
    private Date dateKC;
    @ColumnInfo(name = "noteKC")
    private String noteKC;

    public KhoanChi(String nameKC, int lcID, Double tienKC, Date dateKC, String noteKC) {
        this.nameKC = nameKC;
        this.tienKC = tienKC;
        this.dateKC = dateKC;
        this.noteKC = noteKC;
        this.lcID = lcID;
    }

    public int getIdKC() {
        return idKC;
    }

    public void setIdKC(int idKC) {
        this.idKC = idKC;
    }

    public int getLcID() {
        return lcID;
    }

    public void setLcID(int lcID) {
        this.lcID = lcID;
    }

    public String getNameKC() {
        return nameKC;
    }

    public void setNameKC(String nameKC) {
        this.nameKC = nameKC;
    }

    public Double getTienKC() {
        return tienKC;
    }

    public void setTienKC(Double tienKC) {
        this.tienKC = tienKC;
    }

    public Date getDateKC() {
        return dateKC;
    }

    public void setDateKC(Date dateKC) {
        this.dateKC = dateKC;
    }

    public String getNoteKC() {
        return noteKC;
    }

    public void setNoteKC(String noteKC) {
        this.noteKC = noteKC;
    }
}
