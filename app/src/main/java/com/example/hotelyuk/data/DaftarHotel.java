package com.example.hotelyuk.data;

import com.example.hotelyuk.entity.Hotel;
import com.example.hotelyuk.entity.Kamar;
import com.example.hotelyuk.entity.Review;

import java.util.ArrayList;
import java.util.Arrays;

public class DaftarHotel {
    public ArrayList<Hotel> daftarHotel;
    public DaftarHotel(){
        daftarHotel = new ArrayList();
        hotel1.addKamar(hotel1_kamar1);
        hotel1.addReview(hotel1_review1);
        hotel1.addReview(hotel1_review2);
        daftarHotel.add(hotel1);

        hotel2.addKamar(hotel2_kamar1);
        hotel2.addReview(hotel2_review1);
        hotel2.addReview(hotel2_review2);
        daftarHotel.add(hotel2);
    }

    // Data dummy hotel 1
    public static final Hotel hotel1 = new Hotel("Phoenix Hotel Yogyakarta", 5.0, "Jetis, Yogyakarta",
            "Jl. Jend. Sudirman No.9, Cokrodiningratan, Kec. Jetis, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55233",
            -7.782615,110.368511, "https://www.ahstatic.com/photos/5451_ho_00_p_1024x768.jpg", 250, 1000000,
            new ArrayList<String>(Arrays.asList("Kamar AC", "Sauna", "Pool")));
    public static final Kamar hotel1_kamar1 = new Kamar("Kamar Single",
            "https://petualang.travelingyuk.com/uploads/2020/01/Ilustrasi-Single-Rooms-via-shutterstock.jpg",
            1000000, new ArrayList<String>(Arrays.asList("1 orang", "Single Bed", "Wi-Fi")));
    public static final Review hotel1_review1 = new Review("User1", "12 Oktober 2021",
            "Pelayanannya lumayan, fasilitasnya oke.", 5);
    public static final Review hotel1_review2 = new Review("User2", "10 Oktober 2021",
            "Pelayanannya luar biasa.", 5);

    public static final Hotel hotel2 = new Hotel("Grand Aston Yogyakarta", 4.8, "Gondokusuman, Yogyakarta",
            "Jl. Urip Sumoharjo No.37, Klitren, Kec. Gondokusuman, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55222",
            -7.782349, 110.382120, "https://visitingjogja.com/wp-content/uploads/2017/01/grand-aston-1.jpg", 128, 1200000,
            new ArrayList<String>(Arrays.asList("Kamar AC", "Ballroom", "Fitness Room")));
    public static final Kamar hotel2_kamar1 = new Kamar("Kamar Standard",
            "https://petualang.travelingyuk.com/uploads/2020/01/Ilustrasi-Single-Rooms-via-shutterstock.jpg",
            1200000, new ArrayList<String>(Arrays.asList("1 orang", "Single Bed", "Wi-Fi")));
    public static final Review hotel2_review1 = new Review("User1", "21 Oktober 2021",
            "Pelayanannya lumayan bagus.", 5);
    public static final Review hotel2_review2 = new Review("User2", "18 Oktober 2021",
            "Kamarnya bersih, staffnya ramah-ramah.", 5);
}
