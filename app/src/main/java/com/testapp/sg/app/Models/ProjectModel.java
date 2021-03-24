package com.testapp.sg.app.Models;

import java.io.Serializable;
import java.util.List;

public class ProjectModel implements Serializable {
    private List<User> users;
    private int id;
    private String name;
    private String logo_url;
    private int is_active;
    private boolean is_owner_watcher;

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public boolean is_owner_watcher() {
        return is_owner_watcher;
    }

    public void setOwner_watcher(boolean is_owner_watcher) {
        this.is_owner_watcher = is_owner_watcher;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public class User implements Serializable {
        private String name;
        private String avatar_url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }

}
