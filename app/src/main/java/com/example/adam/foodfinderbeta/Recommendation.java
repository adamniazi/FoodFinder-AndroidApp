package com.example.adam.foodfinderbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Recommendation extends AppCompatActivity {
    TextView resName;
    TextView resAddress;
    RequestQueue queue;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        resName = (TextView) findViewById(R.id.resname);
        resAddress = (TextView) findViewById(R.id.resaddress);
        url = "http://" + User.getuInstance(this).getUrl()+"/recommend/"+User.getuInstance(this).getEmail();

        JsonObjectRequest rec = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            resName.setText(response.get("name").toString());
                            resAddress.setText(response.get("address").toString());
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

        VolleyController.getInstance(this).addToRequestQueue(rec);

    }

    public void cancel(View view){
        Intent back = new Intent(this, LoggedIn.class);
        startActivity(back);

    }
}
