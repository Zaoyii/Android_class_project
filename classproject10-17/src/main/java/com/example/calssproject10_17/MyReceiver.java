package com.example.calssproject10_17;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle resultExtras = getResultExtras(true);
        String zcyi = resultExtras.getString("zcyi");
        System.out.println(zcyi+"-==--=-=-");
        if (zcyi != null) {
            MainActivity.showToast("监听到上一广播者zcyi参数：" + zcyi);

        } else {
            MainActivity.showToast("监听到zcyi");
        }
    }
}
