package com.example.classproject10_10;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classproject10_10.DB.BaseRoomDatabase;
import com.example.classproject10_10.DB.InstanceDatabase;
import com.example.classproject10_10.DB.User;
import com.example.classproject10_10.DB.UserDao;
import com.example.classproject10_10.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BaseRoomDatabase baseRoomDatabase;
    UserDao userDao;
    ActivityMainBinding activityMainBinding;
    ContentResolver contentResolver;

    ArrayList<User> users = new ArrayList<>();
    private static final String AUTHORITY = "com.zcyi.Rorschach";
    private static final Uri ZCYI_URI = Uri.parse("content://" + AUTHORITY + "/zcyi");

    private RecyclerView listRecycler;
    MyListAdapter adapter;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    @SuppressLint("Range")
    private int getUserList() {
        users.clear();
        Cursor cursor = userDao.selectAll();
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            int userId = cursor.getInt(cursor.getColumnIndex("UserId"));
            String username = cursor.getString(cursor.getColumnIndex("Username"));
            String phone = cursor.getString(cursor.getColumnIndex("Phone"));
            users.add(new User(userId, username, phone));

        }
        System.out.println(users.toString() + "---=====");
        if (users.size() > 0 || adapter != null) {
            adapter = new MyListAdapter(users, this, userDao);
            LinearLayoutManager memoManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            listRecycler.setAdapter(adapter);
            listRecycler.setLayoutManager(memoManager);

        }
        return users.size();
    }

    private void init() {
        activityMainBinding.insert.setOnClickListener(this);
        activityMainBinding.update.setOnClickListener(this);
        activityMainBinding.query.setOnClickListener(this);
        baseRoomDatabase = InstanceDatabase.getInstance(this);
        userDao = baseRoomDatabase.getUserDao();
        listRecycler = (RecyclerView) findViewById(R.id.list_recycler);
        contentResolver = getContentResolver();
        contentResolver.registerContentObserver(ZCYI_URI, true, new VaeContentObserver(handler));
        getUserList();
    }

    @SuppressLint({"NonConstantResourceId", "Range"})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.insert:
                String username1 = activityMainBinding.username.getText().toString().trim();
                String phone1 = activityMainBinding.phone.getText().toString().trim();
                ContentValues contentValues = new ContentValues();
                contentValues.put("userName", username1);
                contentValues.put("Phone", phone1);
                contentResolver.insert(ZCYI_URI, contentValues);
                getUserList();
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update:
                Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();

                break;
            case R.id.query:
                Cursor query = contentResolver.query(ZCYI_URI, null, null, null, null);
                if (query != null && query.getCount() > 0) {
                    query.moveToFirst();
                    while (query.moveToNext()) {
                        int userId = query.getInt(query.getColumnIndex("UserId"));
                        String username = query.getString(query.getColumnIndex("Username"));
                        String phone = query.getString(query.getColumnIndex("Phone"));
                        users.add(new User(userId, username, phone));
                    }
                    Toast.makeText(this, "Query", Toast.LENGTH_SHORT).show();
                    query.close();

                } else {
                    Toast.makeText(this, "数据库为空", Toast.LENGTH_SHORT).show();
                }
                getUserList();

                break;
        }
    }
}