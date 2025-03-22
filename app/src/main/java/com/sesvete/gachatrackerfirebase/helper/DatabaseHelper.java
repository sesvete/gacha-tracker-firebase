package com.sesvete.gachatrackerfirebase.helper;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference rootReference = database.getReference();
    private final DatabaseReference usersReference = rootReference.child("users");

    public interface OnCheckExistingUser{
        void onCreateNewUser(String uid);
        void onRetrieveExistingData(String uid);
    }

    interface OnCreateUserCallback{
        void onCreateUser(String uid);
    }

    public void checkIfUserExists(String uid, OnCheckExistingUser check){
        DatabaseReference usernameReference = usersReference.child(uid);
        ValueEventListener checkForUserListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()){
                    writeNewUser(uid, new OnCreateUserCallback() {
                        @Override
                        public void onCreateUser(String uid) {
                            Log.d("UserSuccess", "Successfully created user");
                        }
                    });
                    check.onCreateNewUser(uid);
                } else {
                    check.onRetrieveExistingData(uid);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("checkUser", error.getMessage());
            }
        };
        usernameReference.addListenerForSingleValueEvent(checkForUserListener);
    }

    // nastavi za vse bannerje in igre strukturo podatkovne baze že ob ustvaritvi
    public void writeNewUser(String uid, OnCreateUserCallback callback){

        Map<String, Object> initialValues = new HashMap<>();
        initialValues.put("number", 0);
        initialValues.put("guaranteed", false);

        usersReference.child(uid).child("games").child("genshin_impact").child("limited").child("counter_progress").setValue(initialValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("Database Creation", "Genshin limited banner created");
                } else {
                    Log.d("Database Creation", task.getException().getMessage());
                }
            }
        });
        usersReference.child(uid).child("games").child("genshin_impact").child("weapon").child("counter_progress").setValue(initialValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("Database Creation", "Genshin weapon banner created");
                } else {
                    Log.d("Database Creation", task.getException().getMessage());
                }
            }
        });
        usersReference.child(uid).child("games").child("genshin_impact").child("standard").child("counter_progress").setValue(initialValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("Database Creation", "Genshin standard banner created");
                } else {
                    Log.d("Database Creation", task.getException().getMessage());
                }
            }
        });
        callback.onCreateUser(uid);

    }
}
