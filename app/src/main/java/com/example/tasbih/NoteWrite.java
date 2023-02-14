package com.example.tasbih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class NoteWrite extends AppCompatActivity {
    EditText titleEditText,contentEditText;
    ImageView backBtn;
    ImageButton saveNoteBtn;
    String username_s;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_write);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorText));

        titleEditText = findViewById(R.id.notes_title_text);
        contentEditText = findViewById(R.id.notes_context_text);
        backBtn = findViewById(R.id.backfromWriteNote);
        saveNoteBtn = (ImageButton) findViewById(R.id.save_noteBtn);

        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        username_s = sharedPreferences.getString("username", "");

        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
                startActivity(new Intent(getApplicationContext(),Notehome.class));
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Notehome.class));
            }
        });
    }

    private void saveNote() {
        String noteTitle = titleEditText.getText().toString();
        String noteContent = contentEditText.getText().toString();

        if(!noteTitle.isEmpty()){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("note");
            String title = titleEditText.getText().toString();
            String body = contentEditText.getText().toString();
            String username = username_s;
            NoteStoring noteStoring = new NoteStoring(title,body);
            databaseReference.child(username).push().setValue(noteStoring);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

        }
        else {
            titleEditText.setError("Title is required");
        }
    }

}