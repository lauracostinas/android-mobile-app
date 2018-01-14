package com.example.laura.myfirstapplication;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Laura on 16-Jan-18.
 */

public class UserRepository {
    FirebaseDatabase database = FirebaseConfig.getDatabase();

    public void addUser(String email, String id, User.Role role) {
        User user = new User(email, id, role);
        database.getReference("users").child(id).setValue(user);
    }

    public void isAdmin(String id, final IsAdminCallback callback) {
        database.getReference("users").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if(snapshot.getKey().equals("role")) {
                            callback.action(snapshot.getValue().equals(User.Role.ADMIN.toString()));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public interface IsAdminCallback {
        void action(boolean isAdmin);
    }
}
