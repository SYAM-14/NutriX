package com.example.nutrx;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class timer_above_25 extends AppCompatActivity {
    String buttonValue1;
    Button startButton1;
    private CountDownTimer countDownTimer;
    TextView mTextView;
    private boolean MTimeRunning;
    private long MTimeLeftInMills;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_above25);

        Intent intent = getIntent();
        buttonValue1 = intent.getStringExtra("value1");

        int intValue1 = Integer.parseInt(buttonValue1);

        switch(intValue1){
            case 1:
                setContentView(R.layout.activity_squat1);
                break;
            case 2:
                setContentView(R.layout.activity_bend_1);
                break;
            case 3:
                setContentView(R.layout.activity_cossak);
                break;
            case 4:
                setContentView(R.layout.activity_ankle_1);
                break;
            case 5:
                setContentView(R.layout.activity_ankle_2);
                break;
            case 6:
                setContentView(R.layout.activity_diamond);
                break;
            case 7:
                setContentView(R.layout.activity_diamond_2);
                break;
            case 8:
                setContentView(R.layout.activity_situp_1);
                break;
            case 9:
                setContentView(R.layout.activity_scissor);
                break;
            case 10:
                setContentView(R.layout.activity_fullleg);
                break;

        }
        startButton1 = findViewById(R.id.startButton1);
        mTextView = findViewById(R.id.time_1);

        startButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MTimeRunning){
                    stopTimer();
                }
                else{
                    startTimer();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void stopTimer(){
        countDownTimer.cancel();
        MTimeRunning=false;
        startButton1.setText("Start");
    }
    @SuppressLint("SetTextI18n")
    private void startTimer(){
        final CharSequence value1 = mTextView.getText();
        String num1 = value1.toString();
        String[] parts = num1.split(":");
        String num2 = parts[0]; // 004
        String num3 = parts[1]; // 034556

        final int number = Integer.parseInt(num2)*60 + Integer.parseInt(num3);
        MTimeLeftInMills = number* 1000L;

        countDownTimer = new CountDownTimer(MTimeLeftInMills,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                MTimeLeftInMills = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

                int newValue = Integer.parseInt(buttonValue1)-1;
                if(newValue<=7){
                    Intent intent = new Intent(timer_above_25.this,timer_above_25.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value1",String.valueOf(newValue));
                    startActivity(intent);
                }
                else {
                    newValue = 1;
                    Intent intent = new Intent(timer_above_25.this,timer_above_25.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value1",String.valueOf(newValue));
                    startActivity(intent);
                }
            }
        }.start();
        startButton1.setText("Pause");
        MTimeRunning=true;
    }

    private void updateTimer(){

        int minutes =(int) MTimeLeftInMills/60000;
        int seconds =(int) MTimeLeftInMills%60000/1000;


        String timeLeftText = "";
        if(minutes<10)
            timeLeftText = "0";
        timeLeftText = timeLeftText + minutes + ":";
        if(seconds<10)
            timeLeftText+="0";
        timeLeftText+=seconds;
        mTextView.setText(timeLeftText);

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}