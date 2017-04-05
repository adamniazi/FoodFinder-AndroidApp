package com.example.adam.foodfinderbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    EditText email;
    EditText firstName;
    EditText lastName;
    EditText number;
    Intent intent;
    String urlInput;

    String e1;
    String f1;
    String l1;
    String n1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        email = (EditText) findViewById(R.id.editemail);
        firstName = (EditText) findViewById(R.id.editfname);
        lastName = (EditText) findViewById(R.id.editlname);
        number = (EditText) findViewById(R.id.editnumber);

        intent = getIntent();
        email.setText(intent.getStringExtra(LoggedIn.LOGGED_PROFILE_EMAIL));
        firstName.setText(intent.getStringExtra(LoggedIn.LOGGED_PROFILE_FNAME));
        lastName.setText(intent.getStringExtra(LoggedIn.LOGGED_PROFILE_LNAME));
        number.setText(intent.getStringExtra(LoggedIn.LOGGED_PROFILE_NUMBER));
        urlInput = intent.getStringExtra(LoggedIn.LOGGED_IN_URL_INPUT);
    }

    public void update (View view){
        String e2 = email.getText().toString();
        String f2 = firstName.getText().toString();
        String l2 = lastName.getText().toString();
        String n2 = number.getText().toString();
        post(f2, l2, e2, n2);
    }

    public void post(String fN, String lN, String eM, String pH){
        Map<String,String> params = new HashMap<String,String>();
        params.put("email", eM);
        params.put("firstName", fN);
        params.put("lastName", lN);
        params.put("phone", pH);

        String url = "http://"+urlInput+"/user/" + intent.getStringExtra(LoggedIn.LOGGED_PROFILE_EMAIL)+"/update";
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

    public void success(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void onCancel(View view){
        Intent cancel = new Intent(this, LoggedIn.class);
        //cancel.putExtra()
        startActivity(cancel);
    }
}
