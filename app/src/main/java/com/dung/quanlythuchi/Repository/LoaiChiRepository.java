package com.dung.quanlythuchi.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.dung.quanlythuchi.DAO.LoaiChiDAO;
import com.dung.quanlythuchi.DTO.LoaiChi;
import com.dung.quanlythuchi.database.AppDatabase;

import java.util.List;

public class LoaiChiRepository {
    private LoaiChiDAO mloaiChiDAO;
    private LiveData<List<LoaiChi>> mAllLoaiChi;

    public LoaiChiRepository(Application application) {
        this.mloaiChiDAO = AppDatabase.getDatabase(application).loaiChiDAO();

        mAllLoaiChi = mloaiChiDAO.findAll();
    }

    public LiveData<List<LoaiChi>> getAllLoaiChi() {
        return this.mAllLoaiChi;
    }

    public void insert(LoaiChi loaiChi) {
        new InsertAsyncTask(this.mloaiChiDAO).execute(loaiChi);
    }

    public void delete(LoaiChi loaiChi) {
        new DeleteAsyncTask(this.mloaiChiDAO).execute(loaiChi);
    }

    public void update(LoaiChi loaiChi) {
        new UpdateAsyncTask(this.mloaiChiDAO).execute(loaiChi);
    }

    class InsertAsyncTask extends AsyncTask<LoaiChi, Void, Void> {
        private LoaiChiDAO mloaiChiDAO;

        public InsertAsyncTask(LoaiChiDAO loaiChiDAO) {
            this.mloaiChiDAO = loaiChiDAO;

        }

        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mloaiChiDAO.insert(loaiChis[0]);
            return null;
        }
    }

    class DeleteAsyncTask extends AsyncTask<LoaiChi, Void, Void> {
        private LoaiChiDAO mloaiChiDAO;

        public DeleteAsyncTask(LoaiChiDAO loaiChiDAO) {
            this.mloaiChiDAO = loaiChiDAO;

        }

        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            this.mloaiChiDAO.delete(loaiChis[0]);
            return null;
        }
    }

    class UpdateAsyncTask extends AsyncTask<LoaiChi, Void, Void> {
        private LoaiChiDAO mloaiChiDAO;

        public UpdateAsyncTask(LoaiChiDAO loaiChiDAO) {
            this.mloaiChiDAO = loaiChiDAO;

        }

        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            this.mloaiChiDAO.update(loaiChis[0]);
            return null;
        }
    }
}
