package com.example.classproject10_10;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.classproject10_10.DB.BaseRoomDatabase;
import com.example.classproject10_10.DB.InstanceDatabase;
import com.example.classproject10_10.DB.User;
import com.example.classproject10_10.DB.UserDao;

public class VaeContentProvider extends ContentProvider {
    private static UriMatcher uriMatcher;
    private static final int MATCH_CODE = 92694;
    private static final String authority = "com.zcyi.Rorschach";
    private Context context;
    BaseRoomDatabase baseRoomDatabase;
    private UserDao userDao;

    private static final Uri ZCYI_URI = Uri.parse("content://" + authority + "/zcyi");

    static {

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(authority, "zcyi", MATCH_CODE);
    }

    public VaeContentProvider() {
    }

    public VaeContentProvider(Context context) {
        this.context = context;
    }

    @Override
    public boolean onCreate() {
        baseRoomDatabase = InstanceDatabase.getInstance(getContext());
        userDao = baseRoomDatabase.getUserDao();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        if (uriMatcher.match(uri) == MATCH_CODE) {
            return userDao.selectAll();
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (uriMatcher.match(uri) == MATCH_CODE) {
            String userName = values.getAsString("userName");
            String phone = values.getAsString("Phone");
            User user = new User(userName, phone);
            userDao.addMemo(user);
            System.out.println(user+"=-===--");
            notifyChange();
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (uriMatcher.match(uri) == MATCH_CODE) {
            userDao.DeleteAllMemo();
            notifyChange();
            return 1;
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    private void notifyChange() {
        getContext().getContentResolver().notifyChange(ZCYI_URI, null);
    }
}
