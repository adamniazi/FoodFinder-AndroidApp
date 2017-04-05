package com.example.adam.foodfinderbeta;


import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResListRow {

    public String resName;
    public String resAddress;

    public ResListRow(){
        //empty constructor
    }

    public ResListRow(String name, String address) {
        resName = name;
        resAddress = address;
    }

    public String getAddress() {
        return resAddress;
    }
    public void setAddress(String addr) {
        resAddress = addr;
    }
    public String getName() {
        return resName;
    }
    public void setName(String name) {
        resName = name;
    }
    @Override
    public String toString() {
        return resName + "\n" + resAddress;
    }

    public static ArrayList<ResListRow> getRestaurants(JSONArray jsonObjs){
        ArrayList<ResListRow> restaurants = new ArrayList<ResListRow>();
        for(int i = 0; i < jsonObjs.length(); i++) {
            try {
                restaurants.add(restaurantFromJSON(jsonObjs.getJSONObject(i)));
            } catch (JSONException e){
                e.printStackTrace();
                return null;
            }
        }
        return restaurants;
    }

    public static ResListRow restaurantFromJSON(JSONObject obj){
        ResListRow r = new ResListRow();

        try {
            r.setName(obj.getString("name"));
            r.setAddress(obj.getString("address"));
        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }
        return r;
    }
}
