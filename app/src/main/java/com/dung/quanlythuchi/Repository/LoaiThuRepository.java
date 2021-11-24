package com.dung.quanlythuchi.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.dung.quanlythuchi.DAO.LoaiThuDAO;

import com.dung.quanlythuchi.DTO.LoaiThu;

import com.dung.quanlythuchi.database.AppDatabaseThu;

import java.util.List;

public class LoaiThuRepository {
    private LoaiThuDAO mloaiThuDAO;
    private LiveData<List<LoaiThu>> mAllLoaiThu;

    public LoaiThuRepository(Application application) {
        this.mloaiThuDAO = AppDatabaseThu.getDatabaseThu(application).loaiThuDAO();

        mAllLoaiThu = mloaiThuDAO.findAll();
    }

    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return this.mAllLoaiThu;
    }

    public void insert(LoaiThu loaiThu) {
        new LoaiThuRepository.InsertAsyncTask(this.mloaiThuDAO).execute(loaiThu);
    }

    public void delete(LoaiThu loaiThu) {
        new LoaiThuRepository.DeleteAsyncTask(this.mloaiThuDAO).execute(loaiThu);
    }

    public void update(LoaiThu loaiThu) {
        new LoaiThuRepository.UpdateAsyncTask(this.mloaiThuDAO).execute(loaiThu);
    }

    class InsertAsyncTask extends AsyncTask<LoaiThu, Void, Void> {
        private LoaiThuDAO mloaiThuDAO;

        public InsertAsyncTask(LoaiThuDAO loaiThuDAO) {
            this.mloaiThuDAO = loaiThuDAO;

        }

        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mloaiThuDAO.insert(loaiThus[0]);
            return null;
        }
    }

    class DeleteAsyncTask extends AsyncTask<LoaiThu, Void, Void> {
        private LoaiThuDAO mloaiThuDAO;

        public DeleteAsyncTask(LoaiThuDAO loaiThuDAO) {
            this.mloaiThuDAO = loaiThuDAO;

        }

        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            this.mloaiThuDAO.delete(loaiThus[0]);
            return null;
        }
    }

    class UpdateAsyncTask extends AsyncTask<LoaiThu, Void, Void> {
        private LoaiThuDAO mloaiThuDAO;

        public UpdateAsyncTask(LoaiThuDAO loaiThuDAO) {
            this.mloaiThuDAO = loaiThuDAO;

        }

        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            this.mloaiThuDAO.update(loaiThus[0]);
            return null;
        }
    }
}
