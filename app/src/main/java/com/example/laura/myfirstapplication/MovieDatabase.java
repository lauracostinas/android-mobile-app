package com.example.laura.myfirstapplication;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.laura.myfirstapplication.dao.MovieDao;

/**
 * Created by Laura on 16-Jan-18.
 */

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase INSTANCE;

    public abstract MovieDao movieDao();

    public static MovieDatabase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,
                    MovieDatabase.class, "movieDatabase69").build();
        }
        return INSTANCE;
    }
}