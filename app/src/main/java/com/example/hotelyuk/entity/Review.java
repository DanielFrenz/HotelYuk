package com.example.hotelyuk.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
    @SerializedName("id")
    private long id;
    @SerializedName("user_id")
    private long userId;
    @SerializedName("hotel_id")
    private long hotelId;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("comment")
    private String comment;
    @SerializedName("score")
    private int score;

    private String namaUser;

    public Review(String namaUser, String tanggal, String comment, int score){
        this.namaUser = namaUser;
        this.tanggal = tanggal;
        this.comment = comment;
        this.score = score;
    }

    public Review(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }
}
