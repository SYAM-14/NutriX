package com.example.nutrx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class exercise_under_25 extends AppCompatActivity {

    int[] newArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_under25);

        newArray = new int[]{

                R.id.squat_jump_3,R.id.sumo_bend_3,R.id.cossack_squat_3,R.id.ankle_pushUp_3,R.id.ankle_pushUp_4,
                R.id.diamond_kicks_3,R.id.diamond_kicks_4,R.id.sit_up_3,R.id.scissor_skier_3,R.id.full_leg_3
        };
    }
    public void bck2(View view){
        startActivity(new Intent(exercise_under_25.this,profile.class));
        finish();
    }
    public void imgBtn_1(View view){

        for(int i=0; i <newArray.length;i++){
            if(view.getId()==newArray[i]){
                int value = i+1;
                Log.i("First",String.valueOf(value));
                Intent intent = new Intent(exercise_under_25.this,timer_under_25.class);
                intent.putExtra("value",String.valueOf(value));
                startActivity(intent);
            }
        }

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(exercise_under_25.this,profile.class));
        finish();
    }
}
