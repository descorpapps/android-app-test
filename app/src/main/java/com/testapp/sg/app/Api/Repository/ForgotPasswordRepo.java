package com.testapp.sg.app.Api.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.testapp.sg.app.Api.Controller;
import com.testapp.sg.app.Models.ForgotPasswordModel;
import com.testapp.sg.app.Utils.Util;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordRepo {

    private MutableLiveData<Boolean> mPasswordLiveData = new MutableLiveData<>();

    public ForgotPasswordRepo() {
    }

    public MutableLiveData<Boolean> getStatus(ForgotPasswordModel model) {
        Controller.getApi().forgotPassword(model).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    mPasswordLiveData.postValue(true);
                } else {
                    try {
                        Util.showErrorMsg(new JSONObject(response.errorBody().string()).getJSONObject("first_errors").getString("email"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mPasswordLiveData.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mPasswordLiveData.postValue(false);
            }
        });
        return mPasswordLiveData;
    }

}
