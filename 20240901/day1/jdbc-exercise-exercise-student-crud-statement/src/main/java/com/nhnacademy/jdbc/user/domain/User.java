package com.nhnacademy.jdbc.user.domain;

public class User {
    private String userId;
    private String userName;
    private String userPassword;

    public User(){

    }
    public User(String userId, String userName, String userPassword){
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public String toString(){
        return "{userId: " + userId + ", userName: " + userName + ", userPassword: " + userPassword + "}";
    }
}
