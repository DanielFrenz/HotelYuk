package com.example.hotelyuk.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hotelyuk.room.model.History;

import java.util.List;

@Dao
public interface HistoryDao {
    @Query("SELECT * FROM histories")
    List<History> getAll();

    @Insert
    void insertHistory(History history);

    @Delete
    void deleteHistory(History history);

    @Query("SELECT * FROM histories where user_id = :user_id")
    List<History> getTodosByUserId(int user_id);
}
