package com.example.classproject10_4;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BaseRoomDatabase baseRoomDatabase;
    static UserDao userDao;
    private EditText username;
    private EditText phone;
    private Button search;
    private Button add;
    private Button update;
    private RecyclerView listRecycler;
    MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private int getUserList() {
        List<User> users = userDao.selectAll();
        System.out.println(users.toString() + "---=====");
        if (users.size() > 0 || adapter != null) {
            adapter = new MyListAdapter((ArrayList<User>) users, this, userDao);
            LinearLayoutManager memoManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            listRecycler.setAdapter(adapter);
            listRecycler.setLayoutManager(memoManager);

        }
        return users.size();
    }

    private void initView() {

        username = (EditText) findViewById(R.id.username);
        phone = (EditText) findViewById(R.id.phone);
        search = (Button) findViewById(R.id.search);
        add = (Button) findViewById(R.id.add);
        update = (Button) findViewById(R.id.update);
        search.setOnClickListener(this);
        add.setOnClickListener(this);
        update.setOnClickListener(this);
        listRecycler = (RecyclerView) findViewById(R.id.list_recycler);
        baseRoomDatabase = InstanceDatabase.getInstance(this);
        userDao = baseRoomDatabase.getUserDao();
        getUserList();

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                getUserList();
                Toast.makeText(getApplicationContext(), "搜索成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                String username1 = username.getText().toString().trim();
                String phone1 = phone.getText().toString().trim();
                userDao.addMemo(new User(username1, phone1));
                Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                getUserList();
                break;
            case R.id.update:

                Toast.makeText(getApplicationContext(), "更新成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}