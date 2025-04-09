package com.sesvete.gachatrackerfirebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.sesvete.gachatrackerfirebase.helper.DialogHelper;
import com.sesvete.gachatrackerfirebase.helper.LocaleHelper;

public class SignInWithPasswordActivity extends AppCompatActivity {

    private EditText editTextSignInPasswordUsername;
    private EditText editTextSignInPasswordPassword;
    private MaterialButton btnSignInPasswordSignIn;
    private MaterialButton btnSignInPasswordNewUser;
    private MaterialButton btnSignInPasswordBack;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_with_password);

        DialogHelper.keyboardTouchListener(findViewById(R.id.main_sign_in_with_password), this);

        editTextSignInPasswordUsername = findViewById(R.id.edit_text_sign_in_password_username);
        editTextSignInPasswordPassword = findViewById(R.id.edit_text_sign_in_password_password);
        btnSignInPasswordSignIn = findViewById(R.id.btn_sign_in_password_sign_in);
        btnSignInPasswordNewUser = findViewById(R.id.btn_sign_in_password_new_user);
        btnSignInPasswordBack = findViewById(R.id.btn_sign_in_password_back);

        btnSignInPasswordNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInWithPasswordActivity.this, CreateAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignInPasswordBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInWithPasswordActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}