package com.example.annuaire_tp;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Contact")
public class Contact implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String photo;
    private String job;
    private String email;
    private String phone;

    public Contact() {
    }

    public Contact(String name, String photo, String job, String email, String phone) {
        this.name = name;
        this.photo = photo;
        this.job = job;
        this.email = email;
        this.phone = phone;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
