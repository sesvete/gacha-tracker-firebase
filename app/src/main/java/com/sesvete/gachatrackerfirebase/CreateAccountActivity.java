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

public class CreateAccountActivity extends AppCompatActivity {

    private EditText editTextRegisterUsername;
    private EditText editTextRegisterPassword;
    private EditText editTextRegisterPasswordReenter;
    private MaterialButton btnRegisterCreate;
    private MaterialButton btnRegisterBack;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        DialogHelper.keyboardTouchListener(findViewById(R.id.main_register), this);

        btnRegisterBack = findViewById(R.id.btn_register_back);

        btnRegisterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccountActivity.this, SignInWithPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}