package com.example.tasbih;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Notehome extends AppCompatActivity {
    ImageView backBtn;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    NoteAdapter noteAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notehome);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorText));

        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String username_s = sharedPreferences.getString("username", "");
        Toast.makeText(this, username_s, Toast.LENGTH_SHORT).show();



        floatingActionButton = (FloatingActionButton) findViewById(R.id.add_note_button);
        recyclerView = findViewById(R.id.recycleviewnote);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        backBtn = findViewById(R.id.backfromNote);

        FirebaseRecyclerOptions<NoteModel> options =
                new FirebaseRecyclerOptions.Builder<NoteModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("note").child(username_s),NoteModel.class)
                        .build();
        noteAdapter = new NoteAdapter(options,this);
        recyclerView.setAdapter(noteAdapter);




        backBtn.setOnClickListener((v) -> startActivity(new Intent(getApplicationContext(), Menu.class)));



        floatingActionButton.setOnClickListener((v) -> startActivity(new Intent(getApplicationContext(), NoteWrite.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        noteAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        noteAdapter.stopListening();
    }
}