package com.example.billapp;

public class UserModel {

    String cname, fname, email, address;

    public UserModel() {

    }

    public UserModel(String cname, String fname, String email, String address) {
        this.cname = cname;
        this.fname = fname;
        this.email = email;
        this.address = address;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}


