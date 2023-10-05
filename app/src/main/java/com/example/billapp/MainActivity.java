package com.example.billapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//package actionbar;
public class MainActivity extends AppCompatActivity {

    EditText emailET, passET;
    Button loginBtn;
    TextView signupTxt;
    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // actionbar color changer
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1AACAC"));
        actionBar.setBackgroundDrawable(colorDrawable);

        checkLogin();

        // variables
        signupTxt = findViewById(R.id.signupText);
        emailET = findViewById(R.id.emailEt);
        passET = findViewById(R.id.passwordEt);
        loginBtn = findViewById(R.id.loginButton);
        checkBox = findViewById(R.id.chkbox);

        // sharedPreference
        sharedPreferences = getSharedPreferences("sharedData", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loadingDialog ld = new loadingDialog(MainActivity.this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailET.getText().toString().trim();
                String pass = passET.getText().toString().trim();

                //check email format and emptiness
                if (email.isEmpty()) {
                    emailET.setError("Required");
                    return;
                }
                if (!email.matches("^[a-z0-9+_.-]+@(.+)$")) {
                    emailET.setError("Enter valid Email");
                    return;
                }

                // password
                if (pass.isEmpty()) {
                    passET.setError("Required");
                    return;
                }

                // loading animation
                ld.startLoading();

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // storing data in shared preference
                            if (checkBox.isChecked()) {
                                editor.putString("sharedEmail", email);
                                editor.putString("sharedPass", pass);
                                editor.commit();
                            }

                            ld.dismissDialog();
                            startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        } else {
                            ld.dismissDialog();
                            Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        signupTxt.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), SignupPage.class));
        });
    }

    private void checkLogin() {
        sharedPreferences = getSharedPreferences("sharedData", MODE_PRIVATE);
        if (sharedPreferences.contains("sharedEmail") && sharedPreferences.contains("sharedPass")) {
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
            finish();
        }
    }

}