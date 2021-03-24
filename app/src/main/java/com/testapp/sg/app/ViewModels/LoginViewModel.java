package com.testapp.sg.app.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.testapp.sg.app.Api.Repository.LoginRepository;
import com.testapp.sg.app.Models.LoginModel;
import com.testapp.sg.app.Models.LoginResponse;

public class LoginViewModel extends AndroidViewModel {
    private LoginRepository mLoginRepository;

    public LoginViewModel(Application application) {
        super(application);
        mLoginRepository = new LoginRepository();
    }

    public LiveData<LoginResponse> getLoginDetails(LoginModel model) {
        return mLoginRepository.getLoginLiveData(model);
    }
}
