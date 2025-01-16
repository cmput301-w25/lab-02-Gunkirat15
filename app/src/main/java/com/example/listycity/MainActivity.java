package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    EditText textArea;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Sydney", "Tokyo", "Beijing", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);


        Button addButton = findViewById(R.id.add_city);
        Button deleteButton = findViewById(R.id.delete_city);

        textArea = findViewById(R.id.text_area);

        // Set an OnClickListener on the button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = textArea.getText().toString().trim();
                if (!cityName.isEmpty()) {
                    dataList.add(cityName.toLowerCase());
                    updateCityList();
                    textArea.setText(""); // Clear input
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a city", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityToDelete = textArea.getText().toString().trim();
                if (!cityToDelete.isEmpty()) {
                    boolean cityFound = false;
                    for (int i = 0; i < dataList.size(); i++) {
                        if (dataList.get(i).equalsIgnoreCase(cityToDelete)) { // Case-insensitive match
                            dataList.remove(i);
                            cityFound = true;
                            break;
                        }
                    }

                    if (cityFound) {
                        updateCityList();
                        textArea.setText(""); // Clear input
                        Toast.makeText(MainActivity.this, "City deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "City not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a city to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void updateCityList() {
        StringBuilder cities = new StringBuilder();
        for (String city : dataList) {
            cities.append(city).append("\n");
        }
        cityAdapter.notifyDataSetChanged();
    }
}