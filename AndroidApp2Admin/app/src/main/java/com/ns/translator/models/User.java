package com.ns.translator.models;

public class User {
    //-----------Singleton------------
    private static User user;

    private User() {
        //Singleton
    }

    public static User getInstance() {
        if (user == null) {
            user = new User();
        }

        return user;
    }

    //Variables
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
