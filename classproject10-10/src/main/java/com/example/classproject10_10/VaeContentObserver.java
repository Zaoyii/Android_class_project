package com.example.classproject10_10;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;

public class VaeContentObserver extends ContentObserver {

    Handler mHandler;

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public VaeContentObserver(Handler handler) {
        super(handler);
        mHandler = handler;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
    }

    @Override
    public void onChange(boolean selfChange, @Nullable Uri uri) {
        super.onChange(selfChange, uri);
        Message message = Message.obtain();

        message.obj = uri;
        mHandler.sendMessage(message);
    }
}
