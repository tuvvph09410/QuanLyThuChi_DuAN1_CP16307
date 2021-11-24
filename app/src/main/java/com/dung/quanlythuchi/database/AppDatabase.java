package com.dung.quanlythuchi.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.dung.quanlythuchi.DAO.KhoanChiDAO;
import com.dung.quanlythuchi.DAO.LoaiChiDAO;
import com.dung.quanlythuchi.DTO.KhoanChi;
import com.dung.quanlythuchi.DTO.LoaiChi;

import java.util.Date;

@Database(entities = {LoaiChi.class, KhoanChi.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LoaiChiDAO loaiChiDAO();

    public abstract KhoanChiDAO khoanChiDAO();

    public static AppDatabase INSTANCE;
    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateData(INSTANCE).execute();
        }
    };

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "QLThuChi_DB")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build();
            }
        }
        return INSTANCE;
    }

    public static class PopulateData extends AsyncTask<Void, Void, Void> {
        private LoaiChiDAO loaiChiDAO;
        private KhoanChiDAO khoanChiDAO;

        public PopulateData(AppDatabase database) {
            loaiChiDAO = database.loaiChiDAO();
            khoanChiDAO=database.khoanChiDAO();
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
