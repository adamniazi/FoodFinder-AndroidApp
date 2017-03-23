package com.example.adam.foodfinderbeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LoggedIn extends AppCompatActivity {

    File myFile;
    BufferedReader reader;
    String first;
    String last;
    String em;
    String ph;
    String file = "foodfinder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        TextView fName = (TextView) findViewById(R.id.fNameLoggedIn);
        TextView lName = (TextView) findViewById(R.id.lnameLoggedIn);
        TextView email = (TextView) findViewById(R.id.emailLoggedIn);
        TextView number = (TextView) findViewById(R.id.numLoggedIn);

        try {
            myFile = new File(getFilesDir(), file);
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(myFile)));
            fName.setText(reader.readLine());
            last = reader.readLine();
            em = reader.readLine();
            ph = reader.readLine();
        } catch (Exception e){
            e.printStackTrace();
        }

        //fName.setText(first);
        lName.setText(last);
        email.setText(em);
        number.setText("Hello");







    }
}
