package com.testapp.sg.app.Models;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private String token;
    private AppInit app_init;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AppInit getApp_init() {
        return app_init;
    }

    public void setApp_init(AppInit app_init) {
        this.app_init = app_init;
    }

    public class AppInit {
        private User user;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public class User {
            private int id;
            private String name;
            private String nick;
            private int id_company;

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

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public int getId_company() {
                return id_company;
            }

            public void setId_company(int id_company) {
                this.id_company = id_company;
            }
        }
    }

    public class Errors {
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }


}
