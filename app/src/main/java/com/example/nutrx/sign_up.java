package com.example.nutrx;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class sign_up extends AppCompatActivity {
    AwesomeValidation awesomeValidation;
    FirebaseAuth mAuth;
    final String TAG = "sign_up";
    FirebaseFirestore fStore;
    String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final EditText regName = findViewById(R.id.name_1);
        final EditText regBmi = findViewById(R.id.b_m_i);
        final EditText regEmail = findViewById(R.id.email_1);
        final EditText regPassword = findViewById(R.id.password_1);
        Button createCreate = findViewById(R.id.create_1);
        mAuth = FirebaseAuth.getInstance();
        fStore =FirebaseFirestore.getInstance();
        ImageButton back_arrow = (ImageButton) findViewById(R.id.imageButton);
        back_arrow.setOnClickListener(v -> {
            Intent i = new Intent(sign_up.this, MainActivity.class);
            startActivity(i);
        });
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //validation
        awesomeValidation.addValidation(this,R.id.name_1,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.email_1,
                Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(this,R.id.password_1,
                ".{6,}",R.string.invalid_password);
        awesomeValidation.addValidation(this,R.id.cnf_password,
                R.id.password_1,R.string.invalid_cnf_password);
        awesomeValidation.addValidation(this,R.id.b_m_i,
                ".{2,}",R.string.invalid_bmi);

        createCreate.setOnClickListener(v -> {
            if(awesomeValidation.validate()) {
                String email = regEmail.getText().toString().trim();
                String password = regPassword.getText().toString().trim();
                String fullName = regName.getText().toString().trim();
                String BMI = regBmi.getText().toString().trim();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                assert user != null;
                                Toast.makeText(sign_up.this, "Successful: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                                userID = mAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fStore.collection("Users").document(userID);
                                Map<String,Object> regUser = new HashMap<>();
                                regUser.put("fName",fullName);
                                regUser.put("email",email);
                                regUser.put("bmi",BMI);
                                documentReference.set(regUser).addOnSuccessListener((OnSuccessListener) (aVoid) -> Log.d(TAG,"onSuccess user created" + userID)).addOnFailureListener(e -> Log.d(TAG,"onFailure"+ e));
                                startActivity(new Intent(sign_up.this, profile.class));
                                finish();
                            } else {
                                Log.w(TAG, "Failed", task.getException());
                                Toast.makeText(sign_up.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            else{
                Toast.makeText(getApplicationContext(),"fill the fields",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void bmi_open(View view){
        startActivity(new Intent(sign_up.this,bmi.class));
        finish();
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(sign_up.this,MainActivity.class));
        finish();
    }
}