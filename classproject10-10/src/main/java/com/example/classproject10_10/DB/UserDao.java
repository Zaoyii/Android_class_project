package com.example.classproject10_10.DB;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface UserDao {
    @Query("select * from t_user")
    Cursor selectAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] addMemo(User... users);

    @Update
    int updateMemo(User user);

    @Delete
    int DeleteMemo(User user);

    @Query("Delete from t_user")
    int DeleteAllMemo();
}
