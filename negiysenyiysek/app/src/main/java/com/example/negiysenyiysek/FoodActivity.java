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

public class FoodActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private ListView foodListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        dbHelper = new DatabaseHelper(this);

        foodListView = findViewById(R.id.foodListView);
        EditText foodInput = findViewById(R.id.foodInput);
        Button addFoodButton = findViewById(R.id.addFoodButton);
        Button randomSelectButton = findViewById(R.id.randomSelectButton);
        Button deleteAllButton = findViewById(R.id.deleteAllButton);

        // Listeyi güncelle
        updateFoodList();

        // Yemek ekleme
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodName = foodInput.getText().toString().trim();
                if (!foodName.isEmpty()) {
                    dbHelper.addFood(foodName);
                    foodInput.setText("");
                    updateFoodList();
                } else {
                    Toast.makeText(FoodActivity.this, "Lütfen bir yemek adı girin!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Rastgele yemek seçme
        randomSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> foodList = dbHelper.getFood();
                if (foodList.isEmpty()) {
                    Toast.makeText(FoodActivity.this, "Yemek listesi boş!", Toast.LENGTH_SHORT).show();
                } else {
                    Random random = new Random();
                    String randomFood = foodList.get(random.nextInt(foodList.size()));
                    Toast.makeText(FoodActivity.this, "Rastgele Seçilen: " + randomFood, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Tüm yemekleri silme
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteAllFood();
                updateFoodList();
                Toast.makeText(FoodActivity.this, "Tüm yemekler silindi!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateFoodList() {
        List<String> foodList = dbHelper.getFood();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foodList);
        foodListView.setAdapter(adapter);
    }
}
