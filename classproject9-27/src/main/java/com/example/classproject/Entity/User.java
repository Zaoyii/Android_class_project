package com.example.classproject.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "t_user")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int userId;

    private String userName;
    private String passWord;

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
