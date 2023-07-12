package com.example.connectpie.Entities;

import android.media.Image;

public class Friendlist_withstatus {
    String user_name,name,status;

    public Friendlist_withstatus(String user_name, String name, String status) {
        this.user_name = user_name;
        this.name = name;
        this.status = status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Friendlist_withstatus{" +
                "user_name='" + user_name + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
