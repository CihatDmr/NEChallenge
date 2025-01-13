package com.example.negiysenyiysek;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button neGiysemButton = findViewById(R.id.neGiysemButton);
        Button neYesemButton = findViewById(R.id.neYesemButton);
        Button neIzlesemButton = findViewById(R.id.neIzlesemButton);
        Button exitButton = findViewById(R.id.exitButton);

        // Ne Giysem butonuna tıklanınca ClothingActivity'e geç
        neGiysemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClothingActivity.class);
                startActivity(intent);
            }
        });

        // Ne Yesem butonuna tıklanınca FoodActivity'e geç
        neYesemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });

        // Ne İzlesem butonuna tıklanınca WatchActivity'e geç
        neIzlesemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WatchActivity.class);
                startActivity(intent);
            }
        });

        // Çıkış butonuna tıklanınca uygulamayı kapat
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
