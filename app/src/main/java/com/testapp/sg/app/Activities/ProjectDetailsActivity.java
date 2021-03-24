package com.testapp.sg.app.Activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.testapp.sg.app.Api.Controller;
import com.testapp.sg.app.App;
import com.testapp.sg.app.Models.ProjectModel;
import com.testapp.sg.app.Models.ProjectUpdate;
import com.testapp.sg.app.Models.UserModel;
import com.testapp.sg.app.R;
import com.testapp.sg.app.Utils.Util;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectDetailsActivity extends AppCompatActivity {
    AlertDialog editDialog;
    private ProjectModel projectModel;
    private TextView projectName;
    private CircleImageView projectAvatar;
    private Toolbar toolbar;
    private CardView workersContainer;
    private SwitchCompat activeSwitch, activeWatcher;
    private ListView listViewWorkers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_details_layout);
        projectModel = (ProjectModel) getIntent().getSerializableExtra("Model");
        init();
        workers();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        projectName = findViewById(R.id.tv_project_name);
        projectAvatar = findViewById(R.id.project_avatar);
        activeSwitch = findViewById(R.id.project_active_switch);
        activeWatcher = findViewById(R.id.watcher_switch);
        workersContainer = findViewById(R.id.workersContainer);
        listViewWorkers = findViewById(R.id.listViewWorkers);
    }

    private void workers() {
        toolbar.setTitle(projectModel.getName());
        toolbar.setNavigationOnClickListener(v -> finish());
        Glide.with(App.getContext()).load(projectModel.getLogo_url()).placeholder(R.drawable.default_project_avatar).centerCrop().into(projectAvatar);
        projectName.setText(projectModel.getName());
        if (projectModel.getIs_active() == 1) {
            activeSwitch.setChecked(true);
        } else {
            activeSwitch.setChecked(false);
        }
        activeWatcher.setChecked(projectModel.is_owner_watcher());
        findViewById(R.id.tv_edit).setOnClickListener(v -> showDialog());
        if (projectModel.getUsers().size() > 0) {
            workersContainer.setVisibility(View.VISIBLE);
            showWorkers();
        }
    }

    private void showDialog() {
        final EditText et_project_name = new EditText(this);
        et_project_name.setText(projectModel.getName());
        et_project_name.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        editDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.edit_project_name_text)
                .setView(et_project_name)
                .setPositiveButton(R.string.dialog_save_text, (dialog1, which) -> updateProjectName(et_project_name.getText().toString().trim()))
                .setNegativeButton(R.string.dialog_cancel_text, null)
                .create();
        editDialog.show();
    }

    private void updateProjectName(String newName) {
        if (TextUtils.isEmpty(newName)) {
            Util.showErrorMsg("Field can't be empty");
            return;
        }
        ProjectUpdate update = new ProjectUpdate();
        update.setName(newName);
        Controller.getApi().update(UserModel.getBearer(), projectModel.getId(), update).enqueue(new Callback<ProjectModel>() {
            @Override
            public void onResponse(Call<ProjectModel> call, Response<ProjectModel> response) {
                if (response.code() == 200) {
                    projectModel.setName(newName);
                    projectName.setText(newName);
                    toolbar.setTitle(projectModel.getName());
                    editDialog.cancel();
                    Util.showErrorMsg(getString(R.string.project_updated_text));
                }
            }

            @Override
            public void onFailure(Call<ProjectModel> call, Throwable t) {

            }
        });
    }

    private void showWorkers() {
        List<String> workersList = new ArrayList<>(projectModel.getUsers().size());
        for (ProjectModel.User user : projectModel.getUsers()) {
            workersList.add(user.getName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                workersList);
        listViewWorkers.setAdapter(arrayAdapter);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
