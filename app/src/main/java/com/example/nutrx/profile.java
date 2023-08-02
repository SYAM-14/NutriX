package com.example.nutrx;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.Objects;

public class profile extends AppCompatActivity {

    TextView fullName,BMI,logout;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullName = findViewById(R.id.welcomeText);
        BMI = findViewById(R.id.welcomeBMI);
        logout = findViewById(R.id.logout);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();


        DocumentReference documentReference = fStore.collection("Users").document(userID);
        documentReference.addSnapshotListener(this, (DocumentSnapshot, e) -> {
            assert DocumentSnapshot != null;
            fullName.setText(DocumentSnapshot.getString("fName"));
            BMI.setText(DocumentSnapshot.getString("bmi"));
        });

        logout.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(profile.this,MainActivity.class));
            finish();
        });

    }
    public void exc(View view){
        DocumentReference documentReference = fStore.collection("Users").document(userID);
        documentReference.addSnapshotListener(this, (DocumentSnapshot, e) -> {
            assert DocumentSnapshot != null;
            float f = Float.parseFloat(Objects.requireNonNull(DocumentSnapshot.getString("bmi")));

            if(f>25){
                Intent i = new Intent(profile.this, exercise_above_25.class);
                startActivity(i);
            }
            else{
                Intent l = new Intent(profile.this, exercise_under_25.class);
                startActivity(l);
            }
        });
    }
    public void diet(View view){
        DocumentReference documentReference = fStore.collection("Users").document(userID);
        documentReference.addSnapshotListener(this, (DocumentSnapshot, e) -> {
            assert DocumentSnapshot != null;
            float f = Float.parseFloat(Objects.requireNonNull(DocumentSnapshot.getString("bmi")));
            if(f>25){
                Intent j = new Intent(profile.this, diet_above_25.class);
                startActivity(j);
            }
            else{
                Intent k= new Intent(profile.this, diet_under_25.class);
                startActivity(k);
            }
    }); }
    public void step(View view){
        Intent m = new Intent(profile.this,step_sensor.class);
        startActivity(m);
    }
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}