package com.example.laura.myfirstapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.laura.myfirstapplication.dao.MovieDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getName();
    public static final int REQUEST_CODE = 1;
    public static final int ADD_CODE = 2;
    List<Movie> movies = new ArrayList<>();
    ListView listView;
    MovieListAdapter movieListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "In onCreate");



        listView = (ListView) findViewById(R.id.movielist);

        initializeMovieAdapter();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, ADD_CODE);
            }
        });
    }

    private MovieDatabase getDatabase() {
        return MovieDatabase.getDatabase(getApplicationContext());
    }

    private void initializeMovieAdapter() {
        try {
            new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Void... params) {
                    movies.clear();
                    movies.addAll(getDatabase().movieDao().getAll());
                    return true;
                }
            }.execute().get();

            movieListAdapter = new MovieListAdapter(movies, getLayoutInflater());
            listView.setAdapter(movieListAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    intent.putExtra("movie", movies.get(position));
                    intent.putExtra("position", position);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    private void resetTestData() {
        movies.clear();
        movies.add(new Movie("IT", 2017, Collections.singletonList(CustomGenre.HORROR), R.drawable.itmoviethumbnail));
        movies.add(new Movie("Moana", 2017, Arrays.asList(CustomGenre.ANIMATION, CustomGenre.FAMILY), R.drawable.moanamoviethumbnail));

        MovieDao dao = getDatabase().movieDao();
        dao.deleteAll();
        dao.insertAll(movies);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        initializeMovieAdapter();
    }

    @Override
    protected void onResume(){
        super.onResume();
        movieListAdapter.notifyDataSetChanged();
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"movietrackeradm@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Movie Suggestions");
        EditText et = (EditText) findViewById(R.id.edit_message);
        String s = String.valueOf(et.getText());
        intent.putExtra(Intent.EXTRA_TEXT, s);

        try {
            if (s.isEmpty())
                throw new IllegalArgumentException("Nothing in the input field");
            startActivity(Intent.createChooser(intent, "Send email"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients", Toast.LENGTH_SHORT).show();
        } catch (IllegalArgumentException ex) {
            Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
