package com.example.calssproject10_17;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {

            String resultData = getResultData();
            System.out.println("电话号码为:" + resultData);

            setResultData(null);
            System.out.println(getResultData()+"---=-=-=-=--=");

            Log.e("zcyi", "getResultData");
            abortBroadcast();
        }

    }
}
