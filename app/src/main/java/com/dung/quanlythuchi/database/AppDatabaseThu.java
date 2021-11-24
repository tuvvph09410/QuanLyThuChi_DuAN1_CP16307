package com.dung.quanlythuchi.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.dung.quanlythuchi.DAO.KhoanThuDAO;

import com.dung.quanlythuchi.DAO.LoaiThuDAO;

import com.dung.quanlythuchi.DTO.KhoanThu;

import com.dung.quanlythuchi.DTO.LoaiThu;

import java.util.Date;

@Database(entities = {LoaiThu.class, KhoanThu.class}, version = 3)
public abstract class AppDatabaseThu extends RoomDatabase {
    public abstract LoaiThuDAO loaiThuDAO();

    public abstract KhoanThuDAO khoanThuDAO();

    public static AppDatabaseThu INSTANCE;
    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateData(INSTANCE).execute();
        }
    };

    public static AppDatabaseThu getDatabaseThu(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabaseThu.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabaseThu.class, "QLThu_DB")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build();
            }
        }
        return INSTANCE;
    }

    public static class PopulateData extends AsyncTask<Void, Void, Void> {
        private LoaiThuDAO loaiThuDAO;
        private KhoanThuDAO khoanThuDAO;

        public PopulateData(AppDatabaseThu database) {
            loaiThuDAO = database.loaiThuDAO();
            khoanThuDAO=database.khoanThuDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            String[] loaichis = new String[]{"Mua quần áo"};
//            for (String chi : loaichis) {
//                LoaiChi lt = new LoaiChi(chi);
//                loaiChiDAO.insert(lt);
//            }
//        KhoanChi khoanChi=new KhoanChi("Lương Tháng 11",2, 3000000.0, "02/12/2021", "Đã trả tiền");
//
//        khoanChiDAO.insert(khoanChi);
            return null;
        }
    }
}
