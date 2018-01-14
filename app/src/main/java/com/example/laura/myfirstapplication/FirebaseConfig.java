package com.example.laura.myfirstapplication;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Laura on 16-Jan-18.
 */

public class FirebaseConfig {
    private static FirebaseDatabase database;

    public static FirebaseDatabase getDatabase() {
        if (database == null) {
            database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true); // offline data
        }
        return database;
    }
}
