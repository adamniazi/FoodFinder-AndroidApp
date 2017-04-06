package com.example.adam.foodfinderbeta;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button register;
    Button login;
    EditText emailLogin;
    EditText urlInput;
    RequestQueue queue;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (Button) findViewById(R.id.mainRegister);
        login = (Button) findViewById(R.id.mainLogin);
        emailLogin = (EditText) findViewById(R.id.mainEmail);
        urlInput = (EditText) findViewById(R.id.url);
        queue = VolleyController.getInstance(this.getApplicationContext()).getRequestQueue();

    }

    public void register(View view){
        Intent regis = new Intent(this, Register.class);
        startActivity(regis);
    }

    public void login(View view){
        String url = "http://"+urlInput.getText().toString()+"/user/";
        final String em = emailLogin.getText().toString();

        if (em.matches("")){
            Toast.makeText(getBaseContext(), "Please enter an email", Toast.LENGTH_LONG).show();
        } else if(!em.contains("@")){
            Toast.makeText(getBaseContext(), "Please enter a valid email", Toast.LENGTH_LONG).show();
        } else {
            String send = url + em;
            JsonObjectRequest JSONRequest = new JsonObjectRequest
                    (Request.Method.GET, send, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            String em1;
                            try {
                                em1 = response.get("email").toString();
                            } catch (JSONException e){
                                em1 = null;
                            }
                            if(em1.equals(em)){
                                loginSuccess(response);
                            } else {
                                Toast.makeText(getBaseContext(), "User not found", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getBaseContext(), "Oops! That didn't work!", Toast.LENGTH_LONG).show();
                }
            });

            //Instantiate the RequestQueue and add the request to the queue

            VolleyController.getInstance(this).addToRequestQueue(JSONRequest);
        }
    }

    public void loginSuccess(JSONObject obj){
        Intent loggedIn = new Intent(this, LoggedIn.class);
        try{
            user = User.getuInstance(this.getApplicationContext());
            user.setEmail(obj.get("email").toString());
            user.setfName(obj.get("firstName").toString());
            user.setlName(obj.get("lastName").toString());
            user.setNumber(obj.get("phone").toString());
            user.setUrl(urlInput.getText().toString());
        } catch (JSONException e){
            Toast.makeText(getBaseContext(), "Something went wrong with incoming data", Toast.LENGTH_LONG).show();
        }
        startActivity(loggedIn);

    }
}
