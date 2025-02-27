package com.sesvete.gachaframework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

//TODO: make sure you check for safer intents!!!!!
//TODO: also check intent filters

//TODO: add language setting on signIn Screen
// language setting bo sicer tudi na register screenu, vendar nom uno itak samo kopiral


public class SignInActivity extends AppCompatActivity {

    private MaterialButton btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}