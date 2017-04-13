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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        fName = (TextView) findViewById(R.id.fNameLoggedIn);
        lName = (TextView) findViewById(R.id.lnameLoggedIn);
        email = (TextView) findViewById(R.id.emailLoggedIn);
        number = (TextView) findViewById(R.id.numLoggedIn);

        fName.setText(User.getuInstance(this).getfName());
        lName.setText(User.getuInstance(this).getlName());
        email.setText(User.getuInstance(this).getEmail());
        number.setText(User.getuInstance(this).getNumber());
        url = User.getuInstance(this).getUrl();
    }

    public void EditProfile(View view){
        Intent editProfile = new Intent(this, EditProfile.class);
        startActivity(editProfile);
    }

    public void getRestaurants(View view){
        Intent resList = new Intent(this, RestaurantList.class);
        startActivity(resList);
    }

    public void getRecommendation(View view){
        Intent rec = new Intent(this, Recommendation.class);
        startActivity(rec);
    }

    public void search(View view){
        Intent search = new Intent(this, RestaurantSearch.class);
        startActivity(search);
    }
}
