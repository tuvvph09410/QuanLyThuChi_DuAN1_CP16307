package com.dung.quanlythuchi.fragment;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dung.quanlythuchi.Repository.KhoanThuRepository;

import java.util.Date;
import java.util.List;

public class ThongKeThuViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Long fromDate, toDate;
    private Application application;

    public ThongKeThuViewModelFactory(Application application, Long toDate, Long fromDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.application = application;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        return (T) new ThongKeThuViewModel(application, toDate, fromDate);
        

    }
}
