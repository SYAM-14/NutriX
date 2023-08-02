package com.example.nutrx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class diet_above_25 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_above25);
    }
    @Override
    public void onBackPressed() {

        startActivity(new Intent(diet_above_25.this,profile.class));
    }
    public void bck_009(View view){
        startActivity(new Intent(diet_above_25.this,profile.class));
        finish();
    }
}