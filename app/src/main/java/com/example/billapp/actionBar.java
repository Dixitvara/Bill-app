package com.example.billapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import androidx.appcompat.app.AppCompatActivity;

public class actionBar extends AppCompatActivity {
    public void getBar() {
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1AACAC"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
    }
}


