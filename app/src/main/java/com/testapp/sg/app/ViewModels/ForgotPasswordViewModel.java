package com.testapp.sg.app.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.testapp.sg.app.Api.Repository.ForgotPasswordRepo;
import com.testapp.sg.app.Models.ForgotPasswordModel;

public class ForgotPasswordViewModel extends AndroidViewModel {
    private ForgotPasswordRepo mForgotPasswordRepo;

    public ForgotPasswordViewModel(Application application) {
        super(application);
        mForgotPasswordRepo = new ForgotPasswordRepo();
    }

    public LiveData<Boolean> getStatus(ForgotPasswordModel model) {
        return mForgotPasswordRepo.getStatus(model);
    }
}
