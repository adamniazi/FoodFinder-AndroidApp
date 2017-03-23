package com.example.adam.foodfinderbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    File myFile;
    BufferedReader reader;
    String file = "foodfinder";
    String text;

    public static final String EXTRA_MESSAGE = "com.foodfinder.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            myFile = new File(getFilesDir(), file);
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(myFile)));
            text = reader.readLine();
        } catch (Exception e){
            e.printStackTrace();
        }

        if(text.length() > 0){
            Intent login = new Intent (this, LoggedIn.class);
            startActivity(login);
        } else {
            Intent regis = new Intent(this, Register.class);
            startActivity(regis);
        }
    }
}
