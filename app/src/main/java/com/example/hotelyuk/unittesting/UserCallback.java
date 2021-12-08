package com.example.hotelyuk.unittesting;

import com.example.hotelyuk.entity.User;

public interface UserCallback {
    void onSuccess(boolean value, User user);
    void onError();
}
