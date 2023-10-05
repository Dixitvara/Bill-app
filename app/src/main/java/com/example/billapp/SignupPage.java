package com.example.billapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupPage extends AppCompatActivity {

    EditText cnameET, fnameET, emailET, addressET, cpassET, passET;
    TextView loginTxt;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        // actionbar color changer
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1AACAC"));
        actionBar.setBackgroundDrawable(colorDrawable);

        // variables
        cnameET = findViewById(R.id.cname);
        fnameET = findViewById(R.id.fullname);
        emailET = findViewById(R.id.email);
        addressET = findViewById(R.id.address);
        passET = findViewById(R.id.password);
        cpassET = findViewById(R.id.cpassword);

        button = findViewById(R.id.loginButton);
        loginTxt = findViewById(R.id.loginText);

        // navigation to signup page
        loginTxt.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });

        // onclick of submit button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cname = cnameET.getText().toString().trim();
                String fname = fnameET.getText().toString().trim();
                String email = emailET.getText().toString().trim();
                String address = addressET.getText().toString().trim();
                String pass = passET.getText().toString().trim();
                String cpass = cpassET.getText().toString().trim();

                // validation
                // checks whether fields are empty or not
                if (cname.isEmpty()) {
                    cnameET.setError("Required");
                    return;
                }

                // checking fname input
                if (fname.isEmpty()) {
                    fnameET.setError("Required");
                    return;
                }
                if (!fname.matches("[A-Z][a-z]*")) {
                    fnameET.setError("Enter valid name");
                    return;
                }

                //check email format and emptiness
                if (email.isEmpty()) {
                    emailET.setError("Required");
                    return;
                }
                if (!email.matches("^(.+)@(.+)$")) {
                    emailET.setError("Enter valid Email");
                    return;
                }

                if (address.isEmpty()) {
                    addressET.setError("Required");
                    return;
                }

                // password validation
                if (pass.isEmpty()) {
                    passET.setError("Required");
                    return;
                }
                if (pass.length() < 7) {
                    passET.setError("length at least 8");
                    return;
                }
                if (cpass.isEmpty()) {
                    cpassET.setError("Required");
                    return;
                }


                if (cpass.equals(pass)) {
                    UserModel data = new UserModel(cname, fname, email, address);
                    FirebaseFirestore.getInstance().collection("user").document().set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignupPage.this, "Account created!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(SignupPage.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    cpassET.setError("Not matching");
                }

            }
        });
    }
}