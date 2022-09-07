package com.revature.P1.dtos.requests;

public class NewUserRequest {
    private String id;
    private String username;
    private String password1;
    private String password2;
    private String role;
    private String email;
    private String given_name;
    private String surname;

    public NewUserRequest() {

    }

    public NewUserRequest(String username, String password1, String password2, String email, String given_name, String surname) {
        this.username = username;
        this.password1 = password1;
        this.password2 = password2;
        this.email = email;
        this.given_name = given_name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", given_name='" + given_name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
