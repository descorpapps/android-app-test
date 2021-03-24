package com.testapp.sg.app.Models;

public class UserModel {
    private static String token;
    private static int id;
    private static String name;
    private static String nick;
    private static int id_company;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        UserModel.token = token;
    }

    public static String getBearer() {
        return "Bearer " + token;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UserModel.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        UserModel.name = name;
    }

    public static String getNick() {
        return nick;
    }

    public static void setNick(String nick) {
        UserModel.nick = nick;
    }

    public static int getId_company() {
        return id_company;
    }

    public static void setId_company(int id_company) {
        UserModel.id_company = id_company;
    }
}
