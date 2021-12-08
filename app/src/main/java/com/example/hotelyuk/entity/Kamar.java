package com.example.hotelyuk.entity;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.hotelyuk.R;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Kamar implements Serializable {
    @SerializedName("nama_kamar")
    private String namaKamar;
    @SerializedName("hotel_id")
    private long hotelId;
    @SerializedName("img_url")
    private String imgUrl;
    @SerializedName("harga")
    private long harga;

//    public Kamar(String namaKamar, long hotelId, String imgUrl, long harga)
//    {
//        this.namaKamar = namaKamar;
//        this.hotelId = hotelId;
//        this.imgUrl = imgUrl;
//        this.harga = harga;
//    }

    public Kamar(String namaKamar, String imgUrl, long harga)
    {
        this.namaKamar = namaKamar;
        this.imgUrl = imgUrl;
        this.harga = harga;
    }

    public String getNamaKamar() {
        return namaKamar;
    }

    public void setNamaKamar(String namaKamar) {
        this.namaKamar = namaKamar;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public long getHarga() {
        return harga;
    }

    public void setHarga(long harga) {
        this.harga = harga;
    }

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
