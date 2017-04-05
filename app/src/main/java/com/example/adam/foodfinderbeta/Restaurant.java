package com.example.adam.foodfinderbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class Restaurant extends AppCompatActivity {

    TextView resName;
    TextView resAddress;
    RatingBar ratingBar;
    Intent intent;
    //int rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        intent = getIntent();

        resName = (TextView) findViewById(R.id.resname);
        resAddress = (TextView) findViewById(R.id.resaddress);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        resName.setText(intent.getStringExtra(RestaurantList.RESTAURANT_NAME));
        resAddress.setText(intent.getStringExtra(RestaurantList.RESTAURANT_ADDRESS));
    }

    /*public void addListenerOnSubmit(View view){
        //do something
    }

    public void cancel(View view){
        //cancel and return to restaurant list
    }

    public int getRating(){
        int rated = 0;

        return rated;

    }*/
}
