package com.testapp.sg.app.Models;

import java.io.Serializable;
import java.util.List;

public class ProjectModelList implements Serializable {
    private List<ProjectModel> projects;

    public List<ProjectModel> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectModel> projects) {
        this.projects = projects;
    }
}
