package com.example.hotelyuk.entity;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("phone_number")
    private String no_telp;
    @SerializedName("img_url")
    private String img_url;

    public User(String username, String email, String password, String no_telp, String img_url) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.no_telp = no_telp;
        this.img_url = img_url;
    }

    public User(){ }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
