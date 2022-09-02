package com.revature.P1.dtos.requests;

public class NewUserRequest {
    private String id;
    private String username;
    private String password1;
    //private String password2;
    private String role;

    public NewUserRequest() {

    }

    public NewUserRequest(String id, String username, String password1, String role) {
        this.id = id;
        this.username = username;
        this.password1 = password1;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
