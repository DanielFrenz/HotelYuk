package com.example.hotelyuk.entity;

import java.io.Serializable;

public class Review implements Serializable {
    private String namaUser;
    private String tanggal;
    private String comment;
    private int score;

    public Review(String namaUser, String tanggal, String comment, int score){
        this.namaUser = namaUser;
        this.tanggal = tanggal;
        this.comment = comment;
        this.score = score;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
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
}
