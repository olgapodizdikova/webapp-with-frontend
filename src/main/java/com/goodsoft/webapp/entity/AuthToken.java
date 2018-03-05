package com.goodsoft.webapp.entity;

public class AuthToken {
    private String token;
    private String path;

    public AuthToken(){

    }

    public AuthToken(String token, String path){
        this.token = token;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
