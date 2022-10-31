package com.example.classproject10_4;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "t_user")
public class User {
    @PrimaryKey(autoGenerate = true)
    Integer UserId;
    String Username;
    String Phone;

    public User() {
    }

    public User(String username, String phone) {
        Username = username;
        Phone = phone;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", Username='" + Username + '\'' +
                ", Phone='" + Phone + '\'' +
                '}';
    }
}
