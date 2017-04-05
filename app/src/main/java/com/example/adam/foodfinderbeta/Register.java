package com.example.adam.foodfinderbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private static final String TAG = Register.class.getSimpleName();

    public static final String F_NAME = "com.foodfinder.1";
    public static final String  L_NAME = "com.foodfinder.2";
    public static final String EMAIL = "com.foodfinder.3";
    public static final String NUM = "com.foodfinder.4";
    Button button;
    RequestQueue queue;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button = (Button) findViewById(R.id.button2);
        queue = VolleyController.getInstance(this.getApplicationContext()).getRequestQueue();
        url = "http://192.168.0.102:8080/user/";
    }

    public void submit(View view){

        EditText fName = (EditText) findViewById(R.id.fname);
        EditText lName = (EditText) findViewById(R.id.lname);
        EditText email = (EditText) findViewById(R.id.email);
        EditText number = (EditText) findViewById(R.id.number);

        String firstN = fName.getText().toString();
        String lastN = lName.getText().toString();
        String email1 = email.getText().toString();
        String num = number.getText().toString();

        if (firstN.length() > 0){
            if (lastN.length() > 0){
                if ((email1.length() > 3) && (email1.contains("@"))){
                    if (num.length() == 10){
                        post(firstN, lastN, email1.replaceAll("\\s", ""), num);
                    } else {
                        Toast.makeText(getBaseContext(), "Please enter valid Phone Number", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Please enter valid Email", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getBaseContext(), "Please enter a Last Name", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getBaseContext(), "Please enter a First Name", Toast.LENGTH_LONG).show();
        }
    }

    public void success(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void post(String fN, String lN, String eM, String pH){
        Map<String,String> params = new HashMap<String,String>();
        params.put("email", eM);
        params.put("firstName", fN);
        params.put("lastName", lN);
        params.put("phone", pH);

        String url = "http://192.168.0.102:8080/user/";
        JsonObjectRequest signUp = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getBaseContext(), "User added", Toast.LENGTH_LONG).show();
                        success();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), "Email already exists", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }
        };
        VolleyController.getInstance(this).addToRequestQueue(signUp);
    }
}
