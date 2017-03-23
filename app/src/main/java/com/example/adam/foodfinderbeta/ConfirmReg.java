package com.example.adam.foodfinderbeta;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
//import android.content.SharedPreferences;
import java.io.FileOutputStream;
import android.widget.Toast;

public class ConfirmReg extends AppCompatActivity {

    public static final String FIRST = "FIRST NAME";
    public static final String LAST = "LAST NAME";
    public static final String EMA = "EMAIL";
    public static final String NU = "NUMBER";
    public static final String MyPREFERENCES = "MyPrefs";

    String s1;
    String s2;
    String s3;
    String s4;
    String file;

    //SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_reg);

        //sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        Intent intent = getIntent();

        s1 = intent.getStringExtra(Register.F_NAME);
        s2 = intent.getStringExtra(Register.L_NAME);
        s3 = intent.getStringExtra(Register.EMAIL);
        s4 = intent.getStringExtra(Register.NUM);

        TextView fname = (TextView) findViewById(R.id.fnameconf);
        TextView lname = (TextView) findViewById(R.id.lnameconf);
        TextView email = (TextView) findViewById(R.id.emailconf);
        TextView number = (TextView) findViewById(R.id.numbconf);

        fname.setText(s1);
        lname.setText(s2);
        email.setText(s3);
        number.setText(s4);

        file = "foodfinder";
    }

    public void register (View view) {
        //SharedPreferences.Editor editor = sharedpreferences.edit();
        Intent intent = new Intent(this, LoggedIn.class);

        /*editor.putString(FIRST, s1);
        editor.putString(LAST, s2);
        editor.putString(EMA, s3);
        editor.putString(NU, s4);
        editor.commit();*/

        try {
            FileOutputStream fout = new FileOutputStream(file, true);
            fout.write(s1.getBytes());
            fout.write(s2.getBytes());
            fout.write(s3.getBytes());
            fout.write(s4.getBytes());
            fout.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(ConfirmReg.this,"Thanks",Toast.LENGTH_LONG).show();

        startActivity(intent);

    }
}
