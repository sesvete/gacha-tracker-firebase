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

// TODO: update the counter
// TODO: write into the database

public class DatabaseHelper {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference rootReference = database.getReference();
    private final DatabaseReference usersReference = rootReference.child("users");

    public interface OnCheckExistingUser{
        void onCreateNewUser(String uid);
        void onRetrieveExistingData(String uid);
    }

    public interface OnCounterReceivedListener {
        void onCounterReceived(int counter, boolean guarantee);
    }

    public interface OnCreateUserCallback{
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

        String[] games = {"genshin_impact", "honkai_star_rail", "zenless_zone_zero", "tribe_nine"};
        String[][] bannerTypes = {
                {"limited", "weapon", "standard"},
                {"limited", "light_cone", "standard"},
                {"limited", "w_engine", "standard", "bangboo"},
                {"limited", "tension_card", "standard"}
        };
        for (int i = 0; i < games.length; i++) {
            String game = games[i];
            for (String banner : bannerTypes[i]) {
                usersReference.child(uid).child("games").child(game).child(banner).child("counter_progress").setValue(initialValues).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("Database Creation", game + " " + banner + " banner created");
                        } else {
                            Log.d("Database Creation", "Error creating " + game + " " + banner + " banner: " + task.getException().getMessage());
                        }
                    }
                });
            }
        }
        callback.onCreateUser(uid);
    }

    public void getCounterStatus(String uid, String game, String banner, OnCounterReceivedListener listener){
        DatabaseReference userNameReference = usersReference.child(uid);
        DatabaseReference counterNumberReference = userNameReference.child("games").child(game).child(banner).child("counter_progress");
        counterNumberReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot dataSnapshot = task.getResult();
                    if (dataSnapshot.exists() && dataSnapshot.getValue() != null) {
                        Log.d("SuccessCounter", "Counter progress retrieved");

                        try {
                            Map<String, Object> counterProgress = (Map<String, Object>) dataSnapshot.getValue();

                            int counter = 0;
                            boolean guaranteed = false;

                            if (counterProgress.containsKey("number")) {
                                counter = ((Long) counterProgress.get("number")).intValue(); // or (int)(long) counterProgress.get("number")
                            }

                            if (counterProgress.containsKey("guaranteed")) {
                                guaranteed = (boolean) counterProgress.get("guaranteed");
                            }

                            listener.onCounterReceived(counter, guaranteed);
                        } catch (Exception e) {
                            Log.e("FirebaseDataError", "Error parsing counter progress: " + e.getMessage());
                            listener.onCounterReceived(0, false); // Default values on error
                        }
                    } else {
                        Log.d("skipped counter", "failure");
                        listener.onCounterReceived(0, false); // Default values if data doesn't exist
                    }
                } else {
                    listener.onCounterReceived(0, false); // Default values on task failure
                }
            }
        });

    }
}
