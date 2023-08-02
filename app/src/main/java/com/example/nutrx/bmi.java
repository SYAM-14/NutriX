package com.example.nutrx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class bmi extends AppCompatActivity {
    EditText weight_1,height_1;
    TextView result;
    Button calculate;
    String calculation,BMIResult;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.weight_1,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.height_1,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);



        weight_1 = findViewById(R.id.weight_1);
        height_1 = findViewById(R.id.height_1);
        result = findViewById(R.id.result_bmi);
        calculate = findViewById(R.id.button);

        calculate.setOnClickListener(v -> {

            if(awesomeValidation.validate()){
                String s1 = weight_1.getText().toString();
                String s2 = height_1.getText().toString();
                float weightValue = Float.parseFloat(s1);
                float heightValue = Float.parseFloat(s2)/100;

                float bmi = weightValue/(heightValue * heightValue);
                if(bmi>25){
                    BMIResult= "Overweight";
                }else if(bmi==25){

                    BMIResult = "perfect weight";
                }
                else{
                    BMIResult="Underweight";
                }
                calculation = "Result:" + bmi + "\n" +BMIResult;

                result.setText(calculation);

            }
            else {
                Toast.makeText(getApplicationContext(),"fill the fields",Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton back_arrow = (ImageButton) findViewById(R.id.imageButton2);

        back_arrow.setOnClickListener(v -> {

            startActivity(new Intent(bmi.this,sign_up.class));
            finish();
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(bmi.this,sign_up.class));
        finish();
    }
}
