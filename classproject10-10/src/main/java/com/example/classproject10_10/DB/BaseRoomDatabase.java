package com.example.classproject10_10.DB;


import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = User.class, version = 1,exportSchema = false)
public abstract class BaseRoomDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

}
