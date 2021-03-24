package com.testapp.sg.app.Utils;

import android.util.Patterns;
import android.widget.Toast;

import com.testapp.sg.app.App;


public class Util {
    public static void showErrorMsg(String msg) {
        Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
