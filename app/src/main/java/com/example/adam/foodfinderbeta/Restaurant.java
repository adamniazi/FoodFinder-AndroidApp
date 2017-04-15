package com.example.adam.foodfinderbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Restaurant extends AppCompatActivity {

    TextView resName;
    TextView resAddress;
    RatingBar ratingBar;
    Intent intent;
    int rating;

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
        setStars();
    }

    public void setStars(){
        String url = "http://"+User.getuInstance(this).getUrl()+"/rating/"+User.getuInstance(this).getEmail()+"/"+intent.getStringExtra(RestaurantList.RESTAURANT_NAME).replaceAll(" ", "%20");

        JsonObjectRequest jsonObject = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            rating = Integer.parseInt(response.get("rating").toString());
                            ratingBar.setRating(Integer.parseInt(response.get("rating").toString()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String noRating = "This restaurant has not yet been rated";
                        Toast.makeText(getBaseContext(), noRating, Toast.LENGTH_LONG).show();
                    }
                });
        VolleyController.getInstance(this).addToRequestQueue(jsonObject);
    }

    public void cancel(View view){
        backToResList();
    }

    public void submitRating(View view){
        if(rating != ratingBar.getRating()){
            //post to server
            //Toast.makeText(getBaseContext(), "Rating was changed", Toast.LENGTH_LONG).show();
            postRating(ratingBar.getRating());
        } else {
            backToResList();
        }
    }

    public void postRating(float newRating){
        String resName = intent.getStringExtra(RestaurantList.RESTAURANT_NAME).replaceAll(" ", "%20");
        String url = "http://"+User.getuInstance(this).getUrl()+"/rating/"+User.getuInstance(this).getEmail()+"/"+resName;
        Toast.makeText(getBaseContext(), url, Toast.LENGTH_LONG).show();
        Map<String,String> params = new HashMap<String, String>();
        int r = (int) newRating;
        params.put("email", User.getuInstance(this).getEmail());
        params.put("restaurant", intent.getStringExtra(RestaurantList.RESTAURANT_NAME));
        params.put("rating", Integer.toString(r));

        JsonObjectRequest updateRating = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getBaseContext(), "Rating Updated", Toast.LENGTH_LONG).show();
                        backToResList();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), "Rating was not updated", Toast.LENGTH_LONG).show();
                        backToResList();
                    }
                });
        VolleyController.getInstance(this).addToRequestQueue(updateRating);
    }

    public void backToResList(){
        Intent resList = new Intent(this, RestaurantList.class);
        startActivity(resList);
    }
}
