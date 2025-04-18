package com.sesvete.gachatrackerfirebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.credentials.CredentialManager;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.sesvete.gachatrackerfirebase.fragment.LanguageSettingsFragment;
import com.sesvete.gachatrackerfirebase.helper.AuthenticationHelper;
import com.sesvete.gachatrackerfirebase.helper.LocaleHelper;


//TODO: make sure you check for safer intents!!!!!
//TODO: also check intent filters

//TODO: language setting bo sicer tudi na register screenu, vendar nom uno itak samo kopiral


public class SignInActivity extends AppCompatActivity {

    private MaterialButton btnSignInWithGoogle;
    private MaterialButton btnSignInWithEmail;
    private FirebaseAuth mAuth;
    private CredentialManager credentialManager;

    //basically zagotovimo, da se locale posodobi preden se izgradi main activity

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();

        credentialManager = CredentialManager.create(getBaseContext());

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.language_fragment_container, LanguageSettingsFragment.class, null).setReorderingAllowed(true).commit();

        btnSignInWithEmail = findViewById(R.id.btn_sign_in_password);
        // basically se bo to delalo na novem activity

        btnSignInWithGoogle = findViewById(R.id.btn_sign_in_google);


        btnSignInWithEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignInWithPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthenticationHelper.launchCredentialManager(getResources(), credentialManager, mAuth, SignInActivity.this);
            }
        });

    }
}

/*
You've encountered a common issue when setting locales in Android. The problem arises because Android's system locale can sometimes override your application's locale settings, especially during the initial launch or when the application is restored from the background. Here's a breakdown of the problem and how to fix it:

Problem:

    System Locale Priority: Android prioritizes the system locale (device language) over your application's locale settings.
    Initialization Timing: Even though you're setting the locale in your Application class, the system might have already initialized resources based on the system locale before your code runs.
    Configuration Updates: Sometimes, the system configuration might not be fully updated when you set the locale, leading to inconsistencies.

Solution:

To ensure your application always uses the selected language, you need to:

    Force Configuration Update: Force the system to update the configuration with your desired locale.
    Use createConfigurationContext (API 17+) or attachBaseContext: This is the most reliable way to enforce the locale before the Activity is created.
 */