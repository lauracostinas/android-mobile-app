package com.example.laura.myfirstapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DetailsActivity extends AppCompatActivity {
    EditText nameEditText;
    EditText yearEditText;
    TextView genreListText;
    Movie movie;
    Integer pos;

    MovieRepository movieRepository = new MovieRepository();
    UserRepository userRepository = new UserRepository();
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        firebaseAuth = FirebaseAuth.getInstance();

        movie = (Movie) getIntent().getSerializableExtra("movie");
        pos = (Integer) getIntent().getSerializableExtra("position");

        userRepository.isAdmin(firebaseAuth.getCurrentUser().getUid(), new UserRepository.IsAdminCallback() {
            @Override
            public void action(boolean isAdmin) {
                Button button = findViewById(R.id.deletebutton);
                if(!isAdmin) {
                    button.setVisibility(View.INVISIBLE);
                } else {
                    button.setVisibility(View.VISIBLE);
                }
            }
        });


            movieRepository.getAll(new MovieRepository.IGetAllMovies() {
                @Override
                public void returnMovies(List<Movie> movies) {
                    Map<CustomGenre, Integer> count = new HashMap<>();
                    List<PieEntry> entries = new ArrayList<>();
                    for(Movie movie : movies) {
                        for(CustomGenre genre : movie.getGenre()) {
                            int c = 0;
                            if(count.containsKey(genre)) {
                                c = count.get(genre);
                            }

                            count.put(genre, c + 1);

                        }
                    }

                    for (Map.Entry<CustomGenre, Integer> e : count.entrySet()) {
                        entries.add(new PieEntry(Float.valueOf(e.getValue()), e.getKey().toString()));
                    }

                    PieDataSet pds = new PieDataSet(entries, "Genres");
                    pds.setDrawValues(false);
                    pds.setColors(ColorTemplate.COLORFUL_COLORS);

                    PieChart chart = findViewById(R.id.chart);
                    chart.getDescription().setText("All movies considered");
                    chart.setData(new PieData(pds));
                    chart.invalidate();
                }
            });





        nameEditText = (EditText) findViewById(R.id.nameEditText);
        yearEditText = (EditText) findViewById(R.id.yearEditText);
        genreListText = (TextView) findViewById(R.id.genreListText);
        ImageView imageView = (ImageView) findViewById(R.id.detailsImageView);
        nameEditText.setText(movie.getName());
        yearEditText.setText(String.valueOf(movie.getYear()));
        genreListText.setText(movie.getGenre().toString());
        imageView.setImageResource(movie.getPicture());

        Button button = (Button) findViewById(R.id.submitbutton);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = String.valueOf(nameEditText.getText());
                    Integer year = Integer.valueOf(String.valueOf(yearEditText.getText()));

                    movie.setName(name);
                    movie.setYear(year);

                    movieRepository.update(movie, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        }
                    });

                } catch (NumberFormatException e) {
                    Toast.makeText(DetailsActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.deletebutton).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);

                    builder.setMessage("Are you sure you want to delete this wonderful movie from the database forever and ever?")
                            .setTitle("Delete movie");


                    builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            movieRepository.delete(movie.getId(), new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if(task.isSuccessful()) {
                                        Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                                        setResult(Activity.RESULT_OK, intent);
                                        finish();
                                    }
                                }
                            });


                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });

                    AlertDialog dialog = builder.create();

                    dialog.show();


                } catch (NumberFormatException e) {
                    Toast.makeText(DetailsActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
