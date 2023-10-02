package com.example.billapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

//package actionbar;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // actionbar color changer
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1AACAC"));
        actionBar.setBackgroundDrawable(colorDrawable);

        // variables
        TextView signupTxt = findViewById(R.id.signupText);

        signupTxt.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), SignupPage.class));
        });
    }
}