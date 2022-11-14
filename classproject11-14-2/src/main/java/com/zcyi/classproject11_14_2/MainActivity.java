package com.zcyi.classproject11_14_2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SensorManager systemService;
    SensorEventListener lightEventListener;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        systemService = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor = systemService.getDefaultSensor(Sensor.TYPE_LIGHT);
        textView = findViewById(R.id.text);
        lightEventListener = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent event) {
                textView.setText(event.values[0]+"");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        systemService.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (systemService != null) {
            systemService.unregisterListener(lightEventListener);
        }
    }
}