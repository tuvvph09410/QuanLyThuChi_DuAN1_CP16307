package com.dung.quanlythuchi.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ThongKeChiViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Long toDate,fromDate;
    private Application application;

    public ThongKeChiViewModelFactory(Application application,Long toDate,Long fromDate) {
        this.toDate = toDate;
        this.fromDate = fromDate;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ThongKeChiViewModel(application,toDate,fromDate);
    }
}
