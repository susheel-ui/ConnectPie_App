package com.example.connectpie.Response;

public class sendRequestmodule {
//"firstperson":"kapil20",
//        "secondpersion":"sunny12",
//        "status":"pending"

    private  String  firstperson;
    private  String secondpersion;
    private  String status;

    public sendRequestmodule(String firstperson, String secondpersion, String status) {
        this.firstperson = firstperson;
        this.secondpersion = secondpersion;
        this.status = status;
    }

    public sendRequestmodule() {
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
        return "sendRequestmodule{" +
                "firstperson='" + firstperson + '\'' +
                ", secondpersion='" + secondpersion + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
