package com.testapp.sg.app.Activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.testapp.sg.app.Models.ForgotPasswordModel;
import com.testapp.sg.app.R;
import com.testapp.sg.app.Utils.Util;
import com.testapp.sg.app.ViewModels.ForgotPasswordViewModel;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText et_email;
    private ProgressBar loader;
    private ForgotPasswordViewModel forgotPasswordViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_layout);
        init();
        worker();
    }

    private void init() {
        et_email = findViewById(R.id.et_email);
        loader = findViewById(R.id.loader);
        forgotPasswordViewModel = ViewModelProviders.of(this).get(ForgotPasswordViewModel.class);
    }

    private void worker() {
        findViewById(R.id.back).setOnClickListener(v -> finish());
        sendRequest();
    }

    private void sendRequest() {
        findViewById(R.id.btn_send).setOnClickListener(v -> {
            String email = et_email.getText().toString().trim();

            if (email.isEmpty() || !Util.isValidEmail(email)) {
                Util.showErrorMsg(getResources().getString(R.string.wrong_or_empty_email));
                return;
            }

            ForgotPasswordModel model = new ForgotPasswordModel();
            model.setEmail(email);
            sendData(model);
        });
    }

    private void sendData(ForgotPasswordModel model) {
        if (forgotPasswordViewModel != null) {
            loader.setVisibility(View.VISIBLE);
            forgotPasswordViewModel.getStatus(model).observe(this, forgotPasswordModel -> {
                loader.setVisibility(View.INVISIBLE);
                if (forgotPasswordModel) {
                    Util.showErrorMsg(getString(R.string.instructions));
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
