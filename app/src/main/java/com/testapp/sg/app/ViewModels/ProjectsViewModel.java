package com.testapp.sg.app.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.testapp.sg.app.Api.Repository.CompanyRepo;
import com.testapp.sg.app.Models.ProjectModelList;

public class ProjectsViewModel extends AndroidViewModel {
    private CompanyRepo mCompanyRepo;

    public ProjectsViewModel(Application application) {
        super(application);
        mCompanyRepo = new CompanyRepo();
    }

    public LiveData<ProjectModelList> getData() {
        return mCompanyRepo.getLiveData();
    }
}
