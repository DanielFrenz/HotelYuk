package com.example.hotelyuk.apiresponse;

import com.example.hotelyuk.entity.Hotel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelResponse {
    private String message;

    @SerializedName("data")
    private List<Hotel> hotelList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }
}
