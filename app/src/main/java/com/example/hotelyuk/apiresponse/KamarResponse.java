package com.example.hotelyuk.apiresponse;

import com.example.hotelyuk.entity.Kamar;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KamarResponse {
    private String message;

    @SerializedName("data")
    private List<Kamar> kamarList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Kamar> getKamarList() {
        return kamarList;
    }

    public void setKamarList(List<Kamar> kamarList) {
        this.kamarList = kamarList;
    }
}
