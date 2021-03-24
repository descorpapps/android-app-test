package com.testapp.sg.app.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.testapp.sg.app.Adapters.CompanyAdapter;
import com.testapp.sg.app.Api.Controller;
import com.testapp.sg.app.Models.ProjectModel;
import com.testapp.sg.app.Models.UserModel;
import com.testapp.sg.app.R;
import com.testapp.sg.app.ViewModels.ProjectsViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectsActivity extends AppCompatActivity {
    private ProjectsViewModel mProjectsViewModel;
    private List<ProjectModel> mProjectModels = new ArrayList<>();
    private CompanyAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_layout);
        init();
        workers();
        getProjects();
    }

    private void init() {
        mProjectsViewModel = ViewModelProviders.of(this).get(ProjectsViewModel.class);
        toolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.rv_projects);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CompanyAdapter(mProjectModels, position -> {
            startActivity(new Intent(ProjectsActivity.this, ProjectDetailsActivity.class).putExtra("Model", mProjectModels.get(position)));
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void workers() {
        toolbar.setNavigationOnClickListener(v -> logout());
    }

    private void getProjects() {
        mProjectsViewModel.getData().observe(this, projectModel -> {
            if (projectModel != null) {
                mProjectModels.clear();
                mProjectModels.addAll(projectModel.getProjects());
                mAdapter.addProjects(mProjectModels, true);
                mRecyclerView.post(() -> mAdapter.notifyDataSetChanged());
            }
        });
    }

    private void logout() {
        Controller.getApi().logout(UserModel.getBearer()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    UserModel.setToken(null);
                    finish();
                    startActivity(new Intent(ProjectsActivity.this, LoginActivity.class));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getProjects();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
