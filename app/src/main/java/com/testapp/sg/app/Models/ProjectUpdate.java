package com.testapp.sg.app.Models;

import java.io.Serializable;

public class ProjectUpdate implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
