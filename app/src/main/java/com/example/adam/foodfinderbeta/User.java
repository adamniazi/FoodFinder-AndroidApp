package com.example.adam.foodfinderbeta;

//to hold the static user object

import android.content.Context;

public class User {

    private static User uInstance;
    private static Context uCtx;
    private String email;
    private String fName;
    private String lName;
    private String number;
    private String url;

    private User(Context context){
        uCtx = context;
    }

    public static synchronized User getuInstance(Context context){
        if(uInstance == null){
            uInstance = new User(context);
        }
        return uInstance;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String em){
        email = em;
    }

    public String getfName(){
        return fName;
    }

    public void setfName(String fn){
        fName = fn;
    }

    public String getlName(){
        return lName;
    }

    public void setlName(String ln){
        lName = ln;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String num){
        number = num;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String u){
        url = u;
    }
}
