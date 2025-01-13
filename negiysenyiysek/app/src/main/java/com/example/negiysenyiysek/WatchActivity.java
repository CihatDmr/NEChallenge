package com.example.negiysenyiysek;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Random;

public class WatchActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private ListView moviesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);

        dbHelper = new DatabaseHelper(this);

        moviesListView = findViewById(R.id.moviesListView);
        EditText movieInput = findViewById(R.id.movieInput);
        Button addMovieButton = findViewById(R.id.addMovieButton);
        Button randomSelectButton = findViewById(R.id.randomSelectButton);
        Button deleteAllButton = findViewById(R.id.deleteAllButton);

        // Listeyi güncelle
        updateMoviesList();

        // Film ekleme
        addMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieName = movieInput.getText().toString().trim();
                if (!movieName.isEmpty()) {
                    dbHelper.addMovie(movieName);
                    movieInput.setText("");
                    updateMoviesList();
                } else {
                    Toast.makeText(WatchActivity.this, "Lütfen bir film adı girin!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Rastgele film seçme
        randomSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> moviesList = dbHelper.getMovies();
                if (moviesList.isEmpty()) {
                    Toast.makeText(WatchActivity.this, "Film listesi boş!", Toast.LENGTH_SHORT).show();
                } else {
                    Random random = new Random();
                    String randomMovie = moviesList.get(random.nextInt(moviesList.size()));
                    Toast.makeText(WatchActivity.this, "Rastgele Seçilen: " + randomMovie, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Tüm filmleri silme
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteAllMovies();
                updateMoviesList();
                Toast.makeText(WatchActivity.this, "Tüm filmler silindi!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateMoviesList() {
        List<String> moviesList = dbHelper.getMovies();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, moviesList);
        moviesListView.setAdapter(adapter);
    }
}
