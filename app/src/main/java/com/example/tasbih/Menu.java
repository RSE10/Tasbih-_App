package com.example.tasbih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {
    CardView threeTasbeeh,hundredTasbeeh,infinityTasbeeh,noteView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorText));

        threeTasbeeh = findViewById(R.id.ThirtyThree);
        hundredTasbeeh = findViewById(R.id.hundred);
        infinityTasbeeh = findViewById(R.id.infinity);
        noteView = findViewById(R.id.NotesId);

        threeTasbeeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        hundredTasbeeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HundredTasbeeh.class));
            }
        });
        infinityTasbeeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),InfinityTasbeeh.class));
            }
        });
        noteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Notehome.class));
            }
        });
    }
}