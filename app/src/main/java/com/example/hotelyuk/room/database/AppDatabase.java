package com.example.hotelyuk.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.hotelyuk.room.dao.HistoryDao;
import com.example.hotelyuk.room.dao.UserDao;
import com.example.hotelyuk.room.model.History;
import com.example.hotelyuk.entity.User;

@Database(entities = {History.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HistoryDao historyDao();
//    public abstract UserDao userDao();
}
