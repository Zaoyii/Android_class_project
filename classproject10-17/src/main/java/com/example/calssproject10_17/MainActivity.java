package com.example.calssproject10_17;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MyReceiver myReceiver;
    ParamReceiver paramReceiver;
    CallReceiver callReceiver;

    private Button btn;
    private EditText etParam;
    private Button btnParam;
    static Context context;
    IntentFilter filter;
    IntentFilter filter2;
    private Button btnToCall;

    private int REQUEST_CODE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);

        filter = new IntentFilter("zcyi");
        filter2 = new IntentFilter(Intent.ACTION_NEW_OUTGOING_CALL);
        myReceiver = new MyReceiver();
        paramReceiver = new ParamReceiver();
        callReceiver = new CallReceiver();
        registerReceiver(myReceiver, filter);
        registerReceiver(paramReceiver, filter);
        registerReceiver(callReceiver, filter2);


        etParam = (EditText) findViewById(R.id.et_param);
        btnParam = (Button) findViewById(R.id.btn_param);
        btnParam.setOnClickListener(this);
        context = getApplicationContext();
        btnToCall = (Button) findViewById(R.id.btn_to_call);
        btnToCall.setOnClickListener(this);
        getPermission();
    }

    private void getPermission() {
        int i = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE);
        if (i!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_CODE&&grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            showToast("已获取权限");

        }else {
            showToast("未获取到权限");
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {

            case R.id.btn:
                i = new Intent();
                i.setAction("zcyi");
                sendOrderedBroadcast(i, null);
                break;

            case R.id.btn_param:

                if (!etParam.getText().toString().trim().isEmpty()) {
                    System.out.println("==-=-=-=");
                    i = new Intent();
                    i.setAction("zcyi");
                    i.putExtra("zcyi", etParam.getText().toString().trim());
                    sendOrderedBroadcast(i, null);
                } else {
                    showToast("未输入参数");
                }

                break;
            case R.id.btn_to_call:
                i = new Intent(Intent.ACTION_CALL_BUTTON);
                //i.setAction(Intent.ACTION_NEW_OUTGOING_CALL);
                startActivity(i);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
        unregisterReceiver(paramReceiver);
    }

    public static void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}