package com.revature.P1.models;

public class User {
    private String user_id;
    private String username;
    private String password;
    private String role_id = "100";
    private String email;
    private String given_name;
    private String surname;

    public User(String username, String password, String email, String given_name, String surname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.given_name = given_name;
        this.surname = surname;
    }

    public User(String username, String password, String email, String given_name, String surname, String user_id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.given_name = given_name;
        this.surname = surname;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role_id='" + role_id + '\'' +
                ", email='" + email + '\'' +
                ", given_name='" + given_name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
