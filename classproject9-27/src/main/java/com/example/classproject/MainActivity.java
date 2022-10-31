package com.example.classproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.classproject.Dao.UserDao;
import com.example.classproject.DataBase.BaseRoomDatabase;
import com.example.classproject.DataBase.InstanceDatabase;
import com.example.classproject.Entity.User;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    BaseRoomDatabase baseRoomDatabase;
    UserDao userDao;

    Button save;
    Button jiaZai;
    RadioButton pre;
    RadioButton file;
    EditText et_name;
    EditText et_password;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        baseRoomDatabase = InstanceDatabase.getInstance(getApplicationContext());
        userDao = baseRoomDatabase.geUserDao();
        save = findViewById(R.id.save);
        jiaZai = findViewById(R.id.jiazai);
        save.setOnClickListener(this);
        jiaZai.setOnClickListener(this);
        pre = findViewById(R.id.btn_file);
        file = findViewById(R.id.btn_pre);
        et_name = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        sharedPreferences=getApplicationContext().getSharedPreferences("zcyi",MODE_PRIVATE);
        edit = sharedPreferences.edit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                if (TextUtils.isEmpty(et_name.getText().toString().trim()) || TextUtils.isEmpty(et_password.getText().toString().trim()))
                    Toast.makeText(getApplicationContext(), "账号或密码为空", Toast.LENGTH_SHORT).show();
                else if (!file.isChecked()) {
                    System.out.println("file存储成功");
                    userDao.DeleteAllMemo();
                    userDao.addMemo(new User(et_name.getText().toString().trim(), et_password.getText().toString().trim()));
                } else if (!pre.isChecked()){

                    edit.putString("username",et_name.getText().toString().trim());
                    edit.putString("password",et_password.getText().toString().trim());
                    edit.apply();
                    System.out.println("pre存储成功");
                }else{
                    Toast.makeText(getApplicationContext(), "未选中存储方式", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.jiazai:
                if (!file.isChecked()){
                    List<User> users = userDao.selectAll();
                    if (users.size()>0) {
                        et_name.setText(users.get(0).getUserName());
                        et_password.setText(users.get(0).getPassWord());
                        System.out.println("file获取成功");
                    }
                }else if (!pre.isChecked()){
                    String username = sharedPreferences.getString("username", "unKnow");
                    String password = sharedPreferences.getString("password", "unKnow");
                    et_name.setText(username);
                    et_password.setText(password);
                    System.out.println("pre获取成功");
                }

                break;
        }
    }
}