package com.sesvete.gachatrackerfirebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.sesvete.gachatrackerfirebase.helper.AuthenticationHelper;
import com.sesvete.gachatrackerfirebase.helper.DialogHelper;
import com.sesvete.gachatrackerfirebase.helper.LocaleHelper;

public class SignInWithPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText editTextSignInPasswordEmail;
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

        mAuth = FirebaseAuth.getInstance();

        editTextSignInPasswordEmail = findViewById(R.id.edit_text_sign_in_password_email);
        editTextSignInPasswordPassword = findViewById(R.id.edit_text_sign_in_password_password);
        btnSignInPasswordSignIn = findViewById(R.id.btn_sign_in_password_sign_in);
        btnSignInPasswordNewUser = findViewById(R.id.btn_sign_in_password_new_user);
        btnSignInPasswordBack = findViewById(R.id.btn_sign_in_password_back);

        btnSignInPasswordSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextSignInPasswordEmail.getText().toString();
                String password = editTextSignInPasswordPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()){
                    Toast.makeText(SignInWithPasswordActivity.this, getString(R.string.fill_all_forms), Toast.LENGTH_SHORT).show();
                } else {
                    long timerSingInEmailStart = System.nanoTime();
                    AuthenticationHelper.signInWithEmailAndPassword(mAuth, SignInWithPasswordActivity.this, getResources(), email, password, timerSingInEmailStart);
                }

            }
        });

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