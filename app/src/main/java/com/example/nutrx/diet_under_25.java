package com.example.nutrx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class diet_under_25 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_under25);
    }
    public void bck_00(View view){
        startActivity(new Intent(diet_under_25.this,profile.class));
        finish();
    }
    @Override
    public void onBackPressed() {

        startActivity(new Intent(diet_under_25.this,profile.class));
    }
}