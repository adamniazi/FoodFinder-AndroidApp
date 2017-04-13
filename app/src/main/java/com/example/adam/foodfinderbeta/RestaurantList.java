package com.example.adam.foodfinderbeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.Activity;
import android.widget.Toast;

import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

public class RestaurantList extends AppCompatActivity {

    Intent resRating;
    public static final String RESTAURANT_NAME = "com.foodfinder.restaurantName";
    public static final String RESTAURANT_ADDRESS = "com.foodfinder.restaurantAddress";
    public static final String RESTAURANT_LIST_URL = "com.foodfinder.restaurantListURL;";
    String urlInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        resRating = new Intent(this, Restaurant.class);
        urlInput = User.getuInstance(this).getUrl();
        getRestaurantsList();
    }

    private void populateList(ArrayList<ResListRow> res){
        final ArrayList<ResListRow> resCopy = res;
        MyListViewAdapter adapter = new MyListViewAdapter(this, res);
        ListView listView = (ListView) findViewById(R.id.reslist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                resRating.putExtra(RESTAURANT_NAME, resCopy.get(position).getName());
                resRating.putExtra(RESTAURANT_ADDRESS, resCopy.get(position).getAddress());
                //resRating.putExtra(RESTAURANT_ID, resCopy.get(position).getId());
                resRating.putExtra(RESTAURANT_LIST_URL, urlInput);
                startActivity(resRating);
            }
        });
    }

    private void getRestaurantsList(){
        boolean search = User.getuInstance(this).getSearch();
        final String url;
        if(search){
            url = "http://"+urlInput+"/restaurant?keyword="+getIntent().getStringExtra(RestaurantSearch.RESTAURANT_SEARCH);
        }else{
            url = "http://"+urlInput+"/restaurant";
        }
        Toast.makeText(getBaseContext(), url , Toast.LENGTH_LONG).show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response){
                        ArrayList<ResListRow> temp;
                        Toast.makeText(getBaseContext(), "Received response", Toast.LENGTH_LONG).show();
                        temp = ResListRow.getRestaurants(response);
                        populateList(temp);
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(getBaseContext(), "No response received!: " + url , Toast.LENGTH_LONG).show();
                    }
                });
        VolleyController.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }
}
