package com.example.laura.myfirstapplication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.example.laura.myfirstapplication.Movie;

import java.util.List;

/**
 * Created by Laura on 16-Jan-18.
 */

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movies")
    List<Movie> getAll();

    @Insert
    void insertAll(List<Movie> movies);

    @Insert
    void insertMovie(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("DELETE FROM movies")
    void deleteAll();

    @Query("SELECT * FROM movies WHERE genre LIKE :genre")
    Movie findByGenre(String genre);

    @Query("SELECT * FROM movies WHERE id LIKE :id")
    Movie findById(int id);

    @Update
    void updateMovies(Movie... movies);
}
