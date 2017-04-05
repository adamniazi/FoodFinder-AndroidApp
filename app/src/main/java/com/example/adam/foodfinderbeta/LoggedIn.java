package com.example.adam.foodfinderbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LoggedIn extends AppCompatActivity {

    TextView fName;
    TextView lName;
    TextView email;
    TextView number;
    String url;

    public static final String LOGGED_PROFILE_EMAIL = "com.foodfinder.profileEmail";
    public static final String LOGGED_PROFILE_FNAME = "com.foodfinder.profileFName";
    public static final String LOGGED_PROFILE_LNAME = "com.foodfinder.profileLName";
    public static final String LOGGED_PROFILE_NUMBER = "com.foodfinder.profilePhone";
    public static final String LOGGED_IN_URL_INPUT = "com.foodfinder.urlInput";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        fName = (TextView) findViewById(R.id.fNameLoggedIn);
        lName = (TextView) findViewById(R.id.lnameLoggedIn);
        email = (TextView) findViewById(R.id.emailLoggedIn);
        number = (TextView) findViewById(R.id.numLoggedIn);

        Intent intent = getIntent();

        fName.setText(intent.getStringExtra(MainActivity.PROFILE_FNAME));
        lName.setText(intent.getStringExtra(MainActivity.PROFILE_LNAME));
        email.setText(intent.getStringExtra(MainActivity.PROFILE_EMAIL));
        number.setText(intent.getStringExtra(MainActivity.PROFILE_NUMBER));
        url = intent.getStringExtra(MainActivity.URL_INPUT);
    }

    public void EditProfile(View view){
        Intent editProfile = new Intent(this, EditProfile.class);
        editProfile.putExtra(LOGGED_PROFILE_EMAIL, email.getText());
        editProfile.putExtra(LOGGED_PROFILE_FNAME, fName.getText());
        editProfile.putExtra(LOGGED_PROFILE_LNAME, lName.getText());
        editProfile.putExtra(LOGGED_PROFILE_NUMBER, number.getText());
        editProfile.putExtra(LOGGED_IN_URL_INPUT, url);
        startActivity(editProfile);
    }

    public void getRestaurants(View view){
        Intent resList = new Intent(this, RestaurantList.class);
        resList.putExtra(LOGGED_PROFILE_EMAIL, email.getText());
        resList.putExtra(LOGGED_IN_URL_INPUT, url);
        startActivity(resList);
    }
}
