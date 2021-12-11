package com.dung.quanlythuchi.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;

import androidx.lifecycle.LiveData;


import com.dung.quanlythuchi.DAO.KhoanThuDAO;

import com.dung.quanlythuchi.DTO.KhoanThu;

import com.dung.quanlythuchi.database.AppDatabaseThu;

import java.util.Date;
import java.util.List;

public class KhoanThuRepository {
    private KhoanThuDAO khoanThuDAO;
    //livedata là bộ lưu giữ dữ liệu có thể quan sát được ( observable data holder)
    //livedata cảnh báo khi dữ liệu thay đổi trên giao diện người dùng cập nhật
    private LiveData<List<KhoanThu>> mAllKhoanThu;

    public KhoanThuRepository(Application application) {
        this.khoanThuDAO = AppDatabaseThu.getDatabaseThu(application).khoanThuDAO();
        this.mAllKhoanThu = khoanThuDAO.findAll();
    }
    //livedata trả về tất cả dự liệu có trong bảng
    public LiveData<List<KhoanThu>> getAllKhoanThu() {
        return this.mAllKhoanThu;
    }

    //livedata trả về dự liệu tiền khi được đưa vào ngày thông kê
    public LiveData<Float> getAllAmountKhoanThu(Long toDate, Long fromDate) {

        return this.khoanThuDAO.getTotalAmountDateKhoanThu(toDate, fromDate);

    }

    public LiveData<List<KhoanThu>> getListTkThu(Long toDate, Long fromDate) {
        return this.khoanThuDAO.getListThu(toDate, fromDate);
    }


    public LiveData<Float> getAllTotalAmount() {
        return this.khoanThuDAO.getTotalThu();
    }


    public void insert(KhoanThu khoanThu) {
        new KhoanThuRepository.InsertAsyncTask(this.khoanThuDAO).execute(khoanThu);
    }

    public void update(KhoanThu khoanThu) {
        new KhoanThuRepository.UpdateAsyncTask(this.khoanThuDAO).execute(khoanThu);
    }

    public void delete(KhoanThu khoanThu) {
        new KhoanThuRepository.DeleteAsyncTask(this.khoanThuDAO).execute(khoanThu);
    }
    //thực hiện class bất đồng bộ để gọi phương thức insert để thêm vào database
    class InsertAsyncTask extends AsyncTask<KhoanThu, Void, Void> {
        private KhoanThuDAO khoanThuDAO;

        public InsertAsyncTask(KhoanThuDAO khoanThuDAO) {
            this.khoanThuDAO = khoanThuDAO;
        }

        @Override
        protected Void doInBackground(KhoanThu... khoanThus) {
            khoanThuDAO.insert(khoanThus[0]);
            return null;
        }
    }

    class UpdateAsyncTask extends AsyncTask<KhoanThu, Void, Void> {
        private KhoanThuDAO khoanThuDAO;

        public UpdateAsyncTask(KhoanThuDAO khoanThuDAO) {
            this.khoanThuDAO = khoanThuDAO;
        }

        @Override
        protected Void doInBackground(KhoanThu... khoanThus) {
            khoanThuDAO.update(khoanThus[0]);
            return null;
        }
    }

    class DeleteAsyncTask extends AsyncTask<KhoanThu, Void, Void> {
        private KhoanThuDAO khoanThuDAO;

        public DeleteAsyncTask(KhoanThuDAO khoanThuDAO) {
            this.khoanThuDAO = khoanThuDAO;
        }

        @Override
        protected Void doInBackground(KhoanThu... khoanThus) {
            khoanThuDAO.delete(khoanThus[0]);
            return null;
        }
    }
}
