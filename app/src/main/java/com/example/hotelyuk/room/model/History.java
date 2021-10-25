package com.example.hotelyuk.room.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity(tableName = "histories")
public class History {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "imgUrl")
    private String imgUrl;

    @ColumnInfo(name = "hotelName")
    private String hotelName;

    @ColumnInfo(name = "score")
    private int score;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "tanggalPesan")
    private String tanggalPesan;

    @ColumnInfo(name = "tanggalCheckIn")
    private String tanggalCheckIn;

    @ColumnInfo(name = "tanggalCheckOut")
    private String tanggalCheckOut;

    @ColumnInfo(name = "user_id")
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public History(int id, String imgUrl, String hotelName, int score, String location,
                   String tanggalPesan, String tanggalCheckIn, String tanggalCheckOut)
    {
        this.id = id;
        this.imgUrl = imgUrl;
        this.hotelName = hotelName;
        this.score = score;
        this.location = location;
        this.tanggalPesan = tanggalPesan;
        this.tanggalCheckIn = tanggalCheckIn;
        this.tanggalCheckOut = tanggalCheckOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTanggalPesan() {
        return tanggalPesan;
    }

    public void setTanggalPesan(String tanggalPesan) {
        this.tanggalPesan = tanggalPesan;
    }

    public String getTanggalCheckIn() {
        return tanggalCheckIn;
    }

    public void setTanggalCheckIn(String tanggalCheckIn) {
        this.tanggalCheckIn = tanggalCheckIn;
    }

    public String getTanggalCheckOut() {
        return tanggalCheckOut;
    }

    public void setTanggalCheckOut(String tanggalCheckOut) {
        this.tanggalCheckOut = tanggalCheckOut;
    }
}
