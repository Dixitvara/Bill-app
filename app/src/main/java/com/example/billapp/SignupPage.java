package com.example.billapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SignupPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        // action bar
        new actionBar().getBar();
    }
}