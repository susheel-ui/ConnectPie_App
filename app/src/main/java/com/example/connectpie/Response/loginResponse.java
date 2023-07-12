package com.example.connectpie.Response;

import java.io.Serializable;

public class loginResponse implements Serializable {
    private int id;
    private String name,userid,password,status,email;

    public loginResponse() {
    }

    public loginResponse(String name, String userid, String password, String status, String email) {

        this.name = name;
        this.userid = userid;
        this.password = password;
        this.status = status;
        this.email = email;
    }

    public loginResponse(int id, String name, String userid, String password, String status, String email) {
        this.id = id;
        this.name = name;
        this.userid = userid;
        this.password = password;
        this.status = status;
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  getName();

    }
}
