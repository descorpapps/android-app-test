package com.testapp.sg.app.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.testapp.sg.app.Models.LoginModel;
import com.testapp.sg.app.Models.UserModel;
import com.testapp.sg.app.R;
import com.testapp.sg.app.Utils.Util;
import com.testapp.sg.app.ViewModels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private EditText et_email, et_password;
    private LoginViewModel loginViewModel;
    private ProgressBar loader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        init();
        worker();
    }

    private void init() {
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        loader = findViewById(R.id.loader);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    private void worker() {
        findViewById(R.id.tv_forgot_password).setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)));

        findViewById(R.id.btn_login).setOnClickListener(v -> {
            String email = et_email.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Util.showErrorMsg(getString(R.string.email_or_password_empty));
                return;
            }

            if (!Util.isValidEmail(email)) {
                Util.showErrorMsg(getString(R.string.wrong_email));
                return;
            }

            LoginModel loginModel = new LoginModel();
            loginModel.setEmail(email);
            loginModel.setPassword(password);
            login(loginModel);
        });
    }

    private void login(LoginModel model) {
        if (loginViewModel != null) {
            loader.setVisibility(View.VISIBLE);
            loginViewModel.getLoginDetails(model).observe(this, loginResponse -> {
                loader.setVisibility(View.INVISIBLE);
                if (loginResponse != null) {
                    UserModel.setToken(loginResponse.getToken());
                    UserModel.setId(loginResponse.getApp_init().getUser().getId());
                    startActivity(new Intent(LoginActivity.this, ProjectsActivity.class));
                    finish();
                }
            });
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
