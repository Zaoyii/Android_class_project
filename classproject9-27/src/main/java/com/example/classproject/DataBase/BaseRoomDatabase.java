package com.example.classproject.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.classproject.Dao.UserDao;
import com.example.classproject.Entity.User;

@Database(entities = User.class, version = 1, exportSchema = false)
public abstract class BaseRoomDatabase extends RoomDatabase {
    public abstract UserDao geUserDao();
}
