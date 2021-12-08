package com.example.hotelyuk.data;

import com.example.hotelyuk.entity.Hotel;
import com.example.hotelyuk.entity.Kamar;
import com.example.hotelyuk.entity.Review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaftarHotel {
    public List<Review> daftarReview;
    public List<Review> daftarReviewUser;
    public List<Kamar> daftarKamar;

    public DaftarHotel(){
        daftarReview = new ArrayList<>();
        daftarKamar = new ArrayList<>();
        daftarReviewUser = new ArrayList<>();

        daftarReview.add(r1);
        daftarReview.add(r2);
        daftarReview.add(r3);
        daftarReview.add(r4);
        daftarReview.add(r5);

        daftarKamar.add(k1);
        daftarKamar.add(k2);
        daftarKamar.add(k3);

        daftarReviewUser.add(ru1);
        daftarReviewUser.add(ru2);
        daftarReviewUser.add(ru3);
        daftarReviewUser.add(ru4);
        daftarReviewUser.add(ru5);
        daftarReviewUser.add(ru6);
        daftarReviewUser.add(ru7);
        daftarReviewUser.add(ru8);
        daftarReviewUser.add(ru9);
        daftarReviewUser.add(ru10);
    }
    public static final Review r1 = new Review("User1", "15 Oktober 2021", "Pelayanannya bagus, " +
            "staffnya ramah-ramah", 5);
    public static final Review r2 = new Review("User2", "14 Oktober 2021", "Pelayanannya luar biasa", 5);
    public static final Review r3 = new Review("User3", "13 Oktober 2021", "Recommended bgt buat " +
            "liburan dengan keluarga", 5);
    public static final Review r4 = new Review("User4", "12 Oktober 2021", "Sangat recommended.", 5);
    public static final Review r5 = new Review("User5", "11 Oktober 2021", "Lumayan bagus hotelnya", 4);

    public static final Kamar k1 = new Kamar("Kamar Single", "http://tahupedia.com/img/uploaded/post/post_2/single_room.jpg", 1000000);
    public static final Kamar k2 = new Kamar("Kamar Double", "http://tahupedia.com/img/uploaded/post/post_2/double_room.jpg", 1800000);
    public static final Kamar k3 = new Kamar("Kamar Deluxe", "http://tahupedia.com/img/uploaded/post/post_2/deluxe_room.jpg", 2500000);

    public static final Review ru1 = new Review("Phoenix Hotel Yogyakarta", "15 Oktober 2021", "Pelayanannya bagus, " +
            "staffnya ramah-ramah", 5);
    public static final Review ru2 = new Review("Grand Aston Yogyakarta", "15 Oktober 2021", "Pelayanannya bagus, " +
            "staffnya ramah-ramah", 5);
    public static final Review ru3 = new Review("Phoenix Hotel Yogyakarta", "14 Oktober 2021", "Pelayanannya luar biasa", 5);
    public static final Review ru4 = new Review("Grand Aston Yogyakarta", "14 Oktober 2021", "Pelayanannya luar biasa", 5);
    public static final Review ru5 = new Review("Phoenix Hotel Yogyakarta", "13 Oktober 2021", "Recommended bgt buat " +
            "liburan dengan keluarga", 5);
    public static final Review ru6 = new Review("Grand Aston Yogyakarta", "13 Oktober 2021", "Recommended bgt buat " +
            "liburan dengan keluarga", 5);
    public static final Review ru7 = new Review("Grand Aston Yogyakarta", "12 Oktober 2021", "Sangat recommended.", 5);
    public static final Review ru8 = new Review("Phoenix Hotel Yogyakarta", "12 Oktober 2021", "Sangat recommended.", 5);
    public static final Review ru9 = new Review("Grand Aston Yogyakarta", "11 Oktober 2021", "Lumayan bagus hotelnya", 4);
    public static final Review ru10 = new Review("Phoenix Hotel Yogyakarta", "11 Oktober 2021", "Lumayan bagus hotelnya", 4);

}
