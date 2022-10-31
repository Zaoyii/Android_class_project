package com.example.classproject9_29.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.classproject9_29.Dao.UserDao;
import com.example.classproject9_29.Entity.User;


@Database(entities = User.class, version = 1, exportSchema = false)
public abstract class BaseRoomDatabase extends RoomDatabase {
    public abstract UserDao geUserDao();
}
