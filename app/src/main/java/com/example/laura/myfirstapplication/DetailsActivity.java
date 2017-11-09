package com.example.laura.myfirstapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {
    EditText nameEditText;
    EditText yearEditText;
    TextView genreListText;
    Movie m;
    Integer pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        m = (Movie) getIntent().getSerializableExtra("movie");
        pos = (Integer) getIntent().getSerializableExtra("position");

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        yearEditText = (EditText) findViewById(R.id.yearEditText);
        genreListText = (TextView) findViewById(R.id.genreListText);
        ImageView imageView = (ImageView) findViewById(R.id.detailsImageView);
        nameEditText.setText(m.getName());
        yearEditText.setText(String.valueOf(m.getYear()));
        genreListText.setText(m.getGenre().toString());
        imageView.setImageResource(m.getPicture());

        Button button = (Button) findViewById(R.id.submitbutton);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = String.valueOf(nameEditText.getText());
                    Integer year = Integer.valueOf(String.valueOf(yearEditText.getText()));
                    Movie resultMovie = new Movie(name, year, m.getGenre(), m.getPicture());

                    Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                    intent.putExtra("resultMovie", resultMovie);
                    intent.putExtra("resultPosition", pos);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } catch (NumberFormatException e) {
                    Toast.makeText(DetailsActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
