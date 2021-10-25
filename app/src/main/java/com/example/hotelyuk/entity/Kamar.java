package com.example.hotelyuk.entity;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.hotelyuk.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Kamar implements Serializable {
    private String namaKamar;
    private String imgUrl;
    private long harga;
    private ArrayList<String> listSpek;

    public Kamar(String namaKamar, String imgUrl, long harga, ArrayList<String> listSpek)
    {
        this.namaKamar = namaKamar;
        this.imgUrl = imgUrl;
        this.harga = harga;
        this.listSpek = listSpek;
    }

    public String getNamaKamar() {
        return namaKamar;
    }

    public void setNamaKamar(String namaKamar) {
        this.namaKamar = namaKamar;
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

    public ArrayList<String> getListSpek() {
        return listSpek;
    }

    public void setListSpek(ArrayList<String> listSpek) {
        this.listSpek = listSpek;
    }

    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String imgUrl){
        Glide.with(imageView)
                .load(imgUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_loop)
                .error(R.drawable.ic_error_outline)
                .into(imageView);
    }
}
