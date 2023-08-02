package com.example.nutrx;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class timer_under_25 extends AppCompatActivity {
    String buttonValue;
    Button startButton2;
    private CountDownTimer countDownTimer1;
    TextView mTextView1;
    private boolean MTimeRunning1;
    private long MTimeLeftInMills1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_under25);


        Intent intent1 = getIntent();
        buttonValue = intent1.getStringExtra("value");


        int intValue =  Integer.parseInt(buttonValue);

        switch(intValue){


            case 1:
                setContentView(R.layout.activity_squat_3);
                break;
            case 2:
                setContentView(R.layout.activity_bend_3);
                break;
            case 3:
                setContentView(R.layout.activity_cossak_3);
                break;
            case 4:
                setContentView(R.layout.activity_ankle_3);
                break;
            case 5:
                setContentView(R.layout.activity_ankle_4);
                break;
            case 6:
                setContentView(R.layout.activity_diamond_3);
                break;
            case 7:
                setContentView(R.layout.activity_diamond_4);
                break;
            case 8:
                setContentView(R.layout.activity_situp_3);
                break;
            case 9:
                setContentView(R.layout.activity_scissor_3);
                break;
            case 10:
                setContentView(R.layout.activity_fullleg_3);
                break;
        }

        startButton2 = findViewById(R.id.startButton2);
        mTextView1 = findViewById(R.id.time_2);

        startButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MTimeRunning1){
                    stopTimer();
                }
                else{
                    startTimer();
                }
            }
        });
    }

    private void stopTimer(){
        countDownTimer1.cancel();
        MTimeRunning1=false;
        startButton2.setText("Start");
    }
    @SuppressLint("SetTextI18n")
    private void startTimer(){
        final CharSequence value = mTextView1.getText();
        String num1 = value.toString();
        String[] parts = num1.split(":");
        String num2 = parts[0]; // 004
        String num3 = parts[1]; // 034556

        final int number = Integer.parseInt(num2)*60 + Integer.parseInt(num3);
        MTimeLeftInMills1 = number* 1000L;

        countDownTimer1 = new CountDownTimer(MTimeLeftInMills1,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                MTimeLeftInMills1 = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

                int newValue1 = Integer.parseInt(buttonValue)-1;
                if(newValue1<=7){
                    Intent intent1 = new Intent(timer_under_25.this,timer_under_25.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent1.putExtra("value",String.valueOf(newValue1));
                    startActivity(intent1);
                }
                else {
                    newValue1 = 1;
                    Intent intent1 = new Intent(timer_under_25.this,timer_under_25.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent1.putExtra("value",String.valueOf(newValue1));
                    startActivity(intent1);
                }
            }
        }.start();
        startButton2.setText("Pause");
        MTimeRunning1=true;
    }

    private void updateTimer(){

        int minutes =(int) MTimeLeftInMills1/60000;
        int seconds =(int) MTimeLeftInMills1%60000/1000;


        String timeLeftText = "";
        if(minutes<10)
            timeLeftText = "0";
        timeLeftText = timeLeftText + minutes + ":";
        if(seconds<10)
            timeLeftText+="0";
        timeLeftText+=seconds;
        mTextView1.setText(timeLeftText);

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}