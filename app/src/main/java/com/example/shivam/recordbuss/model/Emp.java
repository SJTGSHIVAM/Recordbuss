package com.example.shivam.recordbuss.model;

/**
 * Created by shivam on 23/3/19.
 */

public class Emp {
    private String Adhar;
    private String name;
    private int contact;
    private String address;
    private int sal;


    public String getAdhar() {
        return Adhar;
    }

    public void setAdhar(String adhar) {
        Adhar = adhar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }
}
