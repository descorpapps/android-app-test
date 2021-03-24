package com.testapp.sg.app.Api.Repository;

import androidx.lifecycle.MutableLiveData;

import com.testapp.sg.app.Api.Controller;
import com.testapp.sg.app.Models.LoginModel;
import com.testapp.sg.app.Models.LoginResponse;
import com.testapp.sg.app.Utils.Util;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private MutableLiveData<LoginResponse> mLoginLiveData = new MutableLiveData<>();

    public LoginRepository() {
    }

    public MutableLiveData<LoginResponse> getLoginLiveData(LoginModel model) {

        Controller.getApi().login(model).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    mLoginLiveData.postValue(response.body());
                } else {
                    try {
                        Util.showErrorMsg(new JSONObject(response.errorBody().string()).getJSONObject("first_errors").getString("email"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mLoginLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mLoginLiveData.postValue(null);
            }
        });
        return mLoginLiveData;
    }

}
