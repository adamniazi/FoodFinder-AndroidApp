package com.example.adam.foodfinderbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

public class Register extends AppCompatActivity {

    private static final String TAG = Register.class.getSimpleName();

    public static final String F_NAME = "com.foodfinder.1";
    public static final String  L_NAME = "com.foodfinder.2";
    public static final String EMAIL = "com.foodfinder.3";
    public static final String NUM = "com.foodfinder.4";
    Button button;
    boolean good;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button = (Button) findViewById(R.id.button2);
        good = false;
    }

    public void submit(View view){

        Intent intent = new Intent(this, ConfirmReg.class);
        EditText fName = (EditText) findViewById(R.id.fname);
        EditText lName = (EditText) findViewById(R.id.lname);
        EditText email = (EditText) findViewById(R.id.email);
        EditText number = (EditText) findViewById(R.id.number);

        String firstN = fName.getText().toString();
        String lastN = lName.getText().toString();
        String email1 = email.getText().toString();
        String num = number.getText().toString();

        /*if (firstN.length() > 0){
            if (lastN.length() > 0){
                if ((email1.length() > 3) && (email1.contains("@"))){
                    if (num.length() == 10){
                        good = true;
                    }
                }
            }
        }*/

        //button.setClickable(good);
        //Log.d(TAG, "Good is: " + good);

        intent.putExtra(F_NAME, firstN);
        intent.putExtra(L_NAME, lastN);
        intent.putExtra(EMAIL, email1);
        intent.putExtra(NUM, num);

        startActivity(intent);


    }
}
