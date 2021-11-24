package com.dung.quanlythuchi.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.dung.quanlythuchi.DAO.KhoanChiDAO;
import com.dung.quanlythuchi.DTO.KhoanChi;
import com.dung.quanlythuchi.database.AppDatabase;

import java.util.List;

public class KhoanChiRepository {
    private KhoanChiDAO khoanChiDAO;
    private LiveData<List<KhoanChi>> mAllKhoanChi;

    public KhoanChiRepository(Application application) {
        this.khoanChiDAO = AppDatabase.getDatabase(application).khoanChiDAO();
        this.mAllKhoanChi = khoanChiDAO.findAll();
    }

    public LiveData<List<KhoanChi>> getAllKhoanChi() {
        return this.mAllKhoanChi;
    }

    public LiveData<Float> getAllTotalDateKhoanChi(Long toDate,Long fromDate){
        return this.khoanChiDAO.getTotalDateChi(toDate,fromDate);
    }
    public LiveData<List<KhoanChi>> getAllDateKhoanChi(Long toDate,Long fromDate){
        return this.khoanChiDAO.getAllDateChi(toDate,fromDate);
    }

    public void insert(KhoanChi khoanChi) {
        new InsertAsyncTask(this.khoanChiDAO).execute(khoanChi);
    }

    public void update(KhoanChi khoanChi) {
        new UpdateAsyncTask(this.khoanChiDAO).execute(khoanChi);
    }

    public void delete(KhoanChi khoanChi) {
        new DeleteAsyncTask(this.khoanChiDAO).execute(khoanChi);
    }

    class InsertAsyncTask extends AsyncTask<KhoanChi, Void, Void> {
        private KhoanChiDAO khoanChiDAO;

        public InsertAsyncTask(KhoanChiDAO khoanChiDAO) {
            this.khoanChiDAO = khoanChiDAO;
        }

        @Override
        protected Void doInBackground(KhoanChi... khoanChis) {
            khoanChiDAO.insert(khoanChis[0]);
            return null;
        }
    }

    class UpdateAsyncTask extends AsyncTask<KhoanChi, Void, Void> {
        private KhoanChiDAO khoanChiDAO;

        public UpdateAsyncTask(KhoanChiDAO khoanChiDAO) {
            this.khoanChiDAO = khoanChiDAO;
        }

        @Override
        protected Void doInBackground(KhoanChi... khoanChis) {
            khoanChiDAO.update(khoanChis[0]);
            return null;
        }
    }

    class DeleteAsyncTask extends AsyncTask<KhoanChi, Void, Void> {
        private KhoanChiDAO khoanChiDAO;

        public DeleteAsyncTask(KhoanChiDAO khoanChiDAO) {
            this.khoanChiDAO = khoanChiDAO;
        }

        @Override
        protected Void doInBackground(KhoanChi... khoanChis) {
            khoanChiDAO.delete(khoanChis[0]);
            return null;
        }
    }
}
