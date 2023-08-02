package com.example.nutrx;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sign_in extends AppCompatActivity {
    AwesomeValidation awesomeValidation;
    FirebaseAuth mAuth;
    final String TAG = "sign_in";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        final EditText logEmail = findViewById(R.id.email_2);
        final EditText logPassword = findViewById(R.id.password_2);
        Button createCreate_2 = findViewById(R.id.create_2);
        mAuth = FirebaseAuth.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.email_2,
                Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(this,R.id.password_2,
                ".{6,}",R.string.invalid_password);

        createCreate_2.setOnClickListener(v -> {
            if(awesomeValidation.validate()){
                String email = logEmail.getText().toString().trim();
                String password = logPassword.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                assert user != null;
                                Toast.makeText(sign_in.this, "success." + user.getEmail(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(sign_in.this, profile.class));
                                finish();
                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(sign_in.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            else{
                Toast.makeText(getApplicationContext(),"fill the fields",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void back_2 (View view){
        startActivity(new Intent(sign_in.this,MainActivity.class));
        finish();
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(sign_in.this,MainActivity.class));
        finish();
    }

}