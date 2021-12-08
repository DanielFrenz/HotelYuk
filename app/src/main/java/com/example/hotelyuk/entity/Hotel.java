package com.example.hotelyuk.entity;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.hotelyuk.R;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Hotel implements Serializable {
    @SerializedName("id")
    private long id;
    @SerializedName("nama_hotel")
    private String namaHotel;
    @SerializedName("score")
    private double score;
    @SerializedName("lokasi")
    private String location;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longtitude")
    private double longtitude;
    @SerializedName("img_url")
    private String imgUrl;

//    private int jumlahReview;
//    private long startPrice;
//    private ArrayList<String> fasilitas;
//    private ArrayList<Kamar> listKamar;
//    private ArrayList<Review> listReview;

    public Hotel(String namaHotel, double score, String location, String alamat, double latitude, double longtitude,
                 String imgUrl)
    {
        this.namaHotel = namaHotel;
        this.score = score;
        this.location = location;
        this.alamat = alamat;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.imgUrl = imgUrl;
    }

//    public void addKamar(Kamar K){
//        listKamar.add(K);
//    }
//
//    public void addReview(Review R) { listReview.add(R); }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNamaHotel() {
        return namaHotel;
    }

    public void setNamaHotel(String namaHotel) {
        this.namaHotel = namaHotel;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

//    public int getJumlahReview() {
//        return jumlahReview;
//    }
//
//    public void setJumlahReview(int jumlahReview) {
//        this.jumlahReview = jumlahReview;
//    }
//
//    public long getStartPrice() {
//        return startPrice;
//    }
//
//    public void setStartPrice(long startPrice) {
//        this.startPrice = startPrice;
//    }
//
//    public ArrayList<String> getFasilitas() {
//        return fasilitas;
//    }
//
//    public void setFasilitas(ArrayList<String> fasilitas) {
//        this.fasilitas = fasilitas;
//    }
//
//    public ArrayList<Kamar> getListKamar() {
//        return listKamar;
//    }
//
//    public void setListKamar(ArrayList<Kamar> listKamar) {
//        this.listKamar = listKamar;
//    }
//
//    public ArrayList<Review> getListReview() {
//        return listReview;
//    }
//
//    public void setListReview(ArrayList<Review> listReview) {
//        this.listReview = listReview;
//    }

    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String imgUrl){
        Glide.with(imageView)
                .load(imgUrl)
                .centerCrop()
                .circleCrop()
                .placeholder(R.drawable.ic_loop)
                .error(R.drawable.ic_error_outline)
                .into(imageView);
    }

}
