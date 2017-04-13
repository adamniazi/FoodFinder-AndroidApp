package com.example.adam.foodfinderbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RestaurantSearch extends AppCompatActivity {

    EditText resSearch;
    Intent intent;

    public static final String RESTAURANT_SEARCH = "com.foodfinder.restaurantSearch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);
        resSearch = (EditText) findViewById(R.id.ressearch);
    }

    public void search(View view){
        intent = new Intent(this, RestaurantList.class);
        intent.putExtra(RESTAURANT_SEARCH, resSearch.getText().toString());
        User.getuInstance(this).setSearch(true);
        startActivity(intent);
    }
}
