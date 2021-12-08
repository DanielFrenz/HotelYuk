package com.example.hotelyuk.apiresponse;

import com.example.hotelyuk.entity.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    private String message;

    @SerializedName("user")
    private User user;

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("image")
    private String img_url;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
