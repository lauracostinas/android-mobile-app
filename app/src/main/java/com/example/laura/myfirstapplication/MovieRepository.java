package com.example.laura.myfirstapplication;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laura on 16-Jan-18.
 */

public class MovieRepository {
    private DatabaseReference ref = FirebaseConfig.getDatabase().getReference("movies");

    public void addMovie(String name, Integer year, List<CustomGenre> genre) {
        Movie movie = new Movie(name, year, genre, R.drawable.itmoviethumbnail);
        ref.child(String.valueOf(movie.getId())).setValue(movie);
    }

    public void getAll(final IGetAllMovies callback) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Movie> movies = new ArrayList<Movie>();
                if(dataSnapshot.exists()) {

                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Movie movie = snapshot.getValue(Movie.class);
                        movies.add(movie);
                    }


                }

                callback.returnMovies(movies);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void delete(String id, OnCompleteListener listener) {
        ref.child(id).removeValue().addOnCompleteListener(listener);
    }

    public void update(Movie movie, OnCompleteListener listener) {
        ref.child(movie.getId()).setValue(movie).addOnCompleteListener(listener);
    }

    interface IGetAllMovies {
        void returnMovies(List<Movie> movies);
    }
}
