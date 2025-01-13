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

public class ClothingActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private ListView clothingListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing);

        dbHelper = new DatabaseHelper(this);

        clothingListView = findViewById(R.id.clothingListView);
        EditText clothingInput = findViewById(R.id.clothingInput);
        Button addClothingButton = findViewById(R.id.addClothingButton);
        Button randomSelectButton = findViewById(R.id.randomSelectButton);
        Button deleteAllButton = findViewById(R.id.deleteAllButton);

        // Listeyi güncelle
        updateClothingList();

        // Kıyafet ekleme
        addClothingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clothingName = clothingInput.getText().toString().trim();
                if (!clothingName.isEmpty()) {
                    dbHelper.addClothing(clothingName);
                    clothingInput.setText("");
                    updateClothingList();
                } else {
                    Toast.makeText(ClothingActivity.this, "Lütfen bir kıyafet adı girin!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Rastgele kıyafet seçme
        randomSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> clothingList = dbHelper.getClothing();
                if (clothingList.isEmpty()) {
                    Toast.makeText(ClothingActivity.this, "Kıyafet listesi boş!", Toast.LENGTH_SHORT).show();
                } else {
                    Random random = new Random();
                    String randomClothing = clothingList.get(random.nextInt(clothingList.size()));
                    Toast.makeText(ClothingActivity.this, "Rastgele Seçilen: " + randomClothing, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Tüm kıyafetleri silme
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteAllClothing();
                updateClothingList();
                Toast.makeText(ClothingActivity.this, "Tüm kıyafetler silindi!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateClothingList() {
        List<String> clothingList = dbHelper.getClothing();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clothingList);
        clothingListView.setAdapter(adapter);
    }
}
