package com.fibertools.models;

public class Users {

    private static Users loggedInUser;

    private int id;
    private String username;
    private String password;

    public Users(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public static void logoutUser() {
        Users.setLoggedInUser(null);
    }

    //Getters
    public static Users getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(Users loggedInUser) {
        Users.loggedInUser = loggedInUser;
    }

    public int getId() {
        return id;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    //Getters End

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //Setters End
}
