package com.testapp.sg.app.Models;

import java.io.Serializable;

public class ForgotPasswordModel implements Serializable {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
