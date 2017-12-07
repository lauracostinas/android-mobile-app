package com.example.laura.myfirstapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText yearEditText;
    private List<CheckBox> checkBoxList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        LinearLayout ll = (LinearLayout) findViewById(R.id.genre_list);

        for (CustomGenre g : CustomGenre.values()) {
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(g.toString().replace("_", " "));
            ll.addView(cb);
            checkBoxList.add(cb);
        }

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        yearEditText = (EditText) findViewById(R.id.yearEditText);

        Button button = (Button) findViewById(R.id.submitbutton);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = String.valueOf(nameEditText.getText());
                    Integer year = Integer.valueOf(String.valueOf(yearEditText.getText()));
                    List<CustomGenre> genres = new ArrayList<>();
                    for (CheckBox cb: checkBoxList) {
                        if(cb.isChecked()) {
                            genres.add(CustomGenre.valueOf(cb.getText().toString().replace(" ", "_")));
                        }
                    }
                    final Movie resultMovie = new Movie(name, year, genres, R.drawable.itmoviethumbnail);

                    try {
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                MovieDatabase.getDatabase(getApplicationContext()).movieDao().insertMovie(resultMovie);
                                return null;
                            }
                        }.execute().get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }


                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } catch (NumberFormatException e) {
                    Toast.makeText(AddActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
