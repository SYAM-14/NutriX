package com.example.nutrx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class exercise_above_25 extends AppCompatActivity {

    int[] newArray1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_above25);
        newArray1 = new int[]{

                R.id.squat_jump1,R.id.sumo_bend_1,R.id.cossack_squat,R.id.ankle_pushUp_1,R.id.ankle_pushUp_2,
                R.id.diamond_kicks,R.id.diamond_kicks_2,R.id.sit_up_1,R.id.scissor_skier,R.id.full_leg


        };

    }
    public void bck1(View view){
        startActivity(new Intent(exercise_above_25.this,profile.class));
        finish();
    }

    public void imgBtn(View view){

        for(int i=0; i <newArray1.length;i++){
            if(view.getId()==newArray1[i]){
                int value1 = i+1;
                Log.i("First",String.valueOf(value1));
                Intent intent = new Intent(exercise_above_25.this,timer_above_25.class);
                intent.putExtra("value1",String.valueOf(value1));
                startActivity(intent);
            }
        }

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(exercise_above_25.this,profile.class));
        finish();
    }

}
