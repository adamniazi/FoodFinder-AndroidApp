package com.example.adam.foodfinderbeta;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//class taken from
//https://examples.javacodegeeks.com/android/core/app/listactivity/android-listactivity-example/
public class RestaurantList extends Activity {

    ArrayList<ResListRow> restaurants;
    Intent resRating;
    public static final String RESTAURANT_NAME = "com.foodfinder.restaurantName";
    public static final String RESTAURANT_ADDRESS = "com.foodfinder.restaurantAddress";
    public static final String RESTAURANT_LIST_URL = "com.foodfinder.restaurantListURL;";
    Intent intent;
    String urlInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        resRating = new Intent(this, Restaurant.class);
        intent = getIntent();
        urlInput = intent.getStringExtra(LoggedIn.LOGGED_IN_URL_INPUT);
        getRestaurantsList();
    }

    /*@Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        String selectedItem = (String) getListView().getItemAtPosition(position);
        //text.setText("You clicked " + selectedItem + " at position " + position);
        Toast.makeText(this, selectedItem + " selected", Toast.LENGTH_LONG).show();
    }*/

    private void populateList(ArrayList<ResListRow> res){
        //ArrayList<ResListRow> test = ResListRow.getRestaurants();
        /*for(int i = 0; i < restaurants.size(); i++){
            String output ="i= "+i+", name: "+restaurants.get(i).getName()+", add: " +restaurants.get(i).getAddress();
            Toast.makeText(getBaseContext(), output, Toast.LENGTH_LONG).show();
        }*/
        final ArrayList<ResListRow> resCopy = res;
        MyListViewAdapter adapter = new MyListViewAdapter(this, res);
        ListView listView = (ListView) findViewById(R.id.reslist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                //Intent intent = new Intent(MainActivity.this, SendMessage.class);
                //String message = "position: "+position+",id: "+id;
                //String nextName = resCopy.get(position).getName();
                //String nextAddress = resCopy.get(position).getAddress();
                resRating.putExtra(RESTAURANT_NAME, resCopy.get(position).getName());
                resRating.putExtra(RESTAURANT_ADDRESS, resCopy.get(position).getAddress());
                resRating.putExtra(RESTAURANT_LIST_URL, urlInput);
                //Toast.makeText(getBaseContext(), nextName+" "+nextAddress, Toast.LENGTH_LONG).show();
                //intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(resRating);
            }
        });
    }

    private void getRestaurantsList(){
        final String url = "http://"+urlInput+"/restaurant";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response){
                        ArrayList<ResListRow> temp;
                        Toast.makeText(getBaseContext(), "Received response", Toast.LENGTH_LONG).show();
                        temp = ResListRow.getRestaurants(response);
                        /*for(int i = 0; i < temp.size(); i++){
                            ResListRow temp2 =new ResListRow(temp.get(i).getName(),temp.get(i).getAddress());
                            restaurants.add(temp2);
                        }*/
                        populateList(temp);
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(getBaseContext(), "No response received!: " + url , Toast.LENGTH_LONG).show();
                    }
                });
        VolleyController.getInstance(this).addToRequestQueue(jsonArrayRequest);
        //populateList();
    }
}
