package com.example.nutrx;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class step_sensor extends AppCompatActivity implements SensorEventListener{
    private TextView steps,steps_2;
    private SensorManager sensorManager;
    private Sensor mStepCounter, mStepDetector;
    boolean isCounterSensorPresent,isDetectorSensorPresent;
    int stepCount = 0,stepDetector = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_sensor);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        steps = findViewById(R.id.steps);
        steps_2 = findViewById(R.id.steps_2);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){

            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = true;
        }
        else {
            steps.setText("Counter sensor not present");
            isCounterSensorPresent = false;
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!=null)
        {
            mStepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            isDetectorSensorPresent = true;
        }
        else{
                steps_2.setText("Detector sensor not present");
                isDetectorSensorPresent = false;
        }
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        if(sensorEvent.sensor == mStepCounter){
            stepCount = (int) sensorEvent.values[0];
            steps.setText(String.valueOf(stepCount));
        }
        else if(sensorEvent.sensor == mStepDetector){
            stepDetector = (int) (stepDetector+sensorEvent.values[0]);
            steps_2.setText(String.valueOf(stepDetector));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor , int i){
    }

    @Override
    public void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null){
            sensorManager.registerListener(this,mStepCounter,SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!=null){
            sensorManager.registerListener(this,mStepDetector,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            sensorManager.unregisterListener(this,mStepCounter);
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!=null){
            sensorManager.unregisterListener(this,mStepDetector);
    }
}
    public void bck_0079(View view){
        Intent i = new Intent(step_sensor.this,profile.class);
        startActivity(i);
    }
    public void onBackPressed(){
        Intent j = new Intent(step_sensor.this,profile.class);
        startActivity(j);
    }
}