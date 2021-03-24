package com.testapp.sg.app.Api.Repository;

import androidx.lifecycle.MutableLiveData;

import com.testapp.sg.app.Api.Controller;
import com.testapp.sg.app.Models.ProjectModelList;
import com.testapp.sg.app.Models.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyRepo {

    private MutableLiveData<ProjectModelList> liveData = new MutableLiveData<>();

    public CompanyRepo() {
    }

    public MutableLiveData<ProjectModelList> getLiveData() {

        Controller.getApi().getCompanies(UserModel.getBearer()).enqueue(new Callback<ProjectModelList>() {
            @Override
            public void onResponse(Call<ProjectModelList> call, Response<ProjectModelList> response) {
                if (response.isSuccessful()) {
                    liveData.postValue(response.body());
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ProjectModelList> call, Throwable t) {
                liveData.postValue(null);
            }
        });
        return liveData;
    }

}
