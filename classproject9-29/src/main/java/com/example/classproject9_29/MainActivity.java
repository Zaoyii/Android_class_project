package com.example.classproject9_29;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.classproject9_29.Dao.UserDao;
import com.example.classproject9_29.DataBase.BaseRoomDatabase;
import com.example.classproject9_29.DataBase.InstanceDatabase;
import com.example.classproject9_29.Entity.User;
import com.example.classproject9_29.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding activityMainBinding;

    BaseRoomDatabase baseRoomDatabase;
    UserDao userDao;

    Button save;
    Button jiaZai;
    RadioButton chars;
    RadioButton file;
    EditText et_name;
    EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.etUsername.setOnClickListener(v -> {

        });
        init();

    }

    private void init() {

        baseRoomDatabase = InstanceDatabase.getInstance(getApplicationContext());
        userDao = baseRoomDatabase.geUserDao();
        save.setOnClickListener(this);
        jiaZai.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                if (TextUtils.isEmpty(et_name.getText().toString().trim()) || TextUtils.isEmpty(et_password.getText().toString().trim()))
                    Toast.makeText(getApplicationContext(), "账号或密码为空", Toast.LENGTH_SHORT).show();
                else if (file.isChecked()) {
                    Toast.makeText(getApplicationContext(), "未选中存储方式", Toast.LENGTH_SHORT).show();
                } else {
                    userDao.DeleteAllMemo();
                    userDao.addMemo(new User(et_name.getText().toString().trim(), et_password.getText().toString().trim()));

                }
                break;
            case R.id.jiazai:
                List<User> users = userDao.selectAll();
                if (users.get(0) != null) {
                    et_name.setText(users.get(0).getUserName());
                    et_password.setText(users.get(0).getPassWord());
                }
                break;
        }
    }
}