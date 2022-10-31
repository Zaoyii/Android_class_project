package com.example.calssproject10_17;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ParamReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getStringExtra("zcyi") != null) {
            String zcyi = intent.getStringExtra("zcyi");
            Bundle bundle = new Bundle();
            bundle.putString("zcyi", zcyi);
            setResultExtras(bundle);
            MainActivity.showToast("监听到带参zcyi:" + intent.getStringExtra("zcyi"));
        }

    }
}