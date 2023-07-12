package com.example.connectpie.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InboxFriendRequestmodule {
//      "id": 4,
//              "firstperson": "susheel5",
//              "secondpersion": "susheel5",
//              "status": "pending"
//    int id ;
//    String firstperson,secondpersion,status;
@SerializedName("id")
@Expose
private Integer id;
    @SerializedName("firstperson")
    @Expose
    private String firstperson;
    @SerializedName("secondpersion")
    @Expose
    private String secondpersion;
    @SerializedName("status")
    @Expose
    private String status;

    public InboxFriendRequestmodule(int id, String firstperson, String secondpersion, String status) {
        this.id = id;
        this.firstperson = firstperson;
        this.secondpersion = secondpersion;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstperson() {
        return firstperson;
    }

    public void setFirstperson(String firstperson) {
        this.firstperson = firstperson;
    }

    public String getSecondpersion() {
        return secondpersion;
    }

    public void setSecondpersion(String secondpersion) {
        this.secondpersion = secondpersion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "InboxFriendRequestmodule{" +
                "id=" + id +
                ", firstperson='" + firstperson + '\'' +
                ", secondpersion='" + secondpersion + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
