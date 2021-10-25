package com.example.hotelyuk.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hotelyuk.room.model.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users WHERE email=:email AND password=:password")
    User attemptLogin(String email, String password);

    @Insert
    void registerUser(User user);

    @Update
    void updateUser(User user);
}
