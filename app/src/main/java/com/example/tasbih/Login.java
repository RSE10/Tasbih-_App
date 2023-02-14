package com.example.tasbih;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    TextInputEditText username_var,password_var;
    TextInputLayout passwordLayout;
    TextView signupBtn;
    AppCompatButton LoginBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(ContextCompat.getColor(Login.this,R.color.colorText));

        signupBtn = findViewById(R.id.signUpView);
        LoginBtn = findViewById(R.id.loginBtn);
        username_var = findViewById(R.id.user_name_l);
        password_var = findViewById(R.id.password_name_l);
        passwordLayout = findViewById(R.id.passwordlayout);


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = username_var.getText().toString();
                final String password = password_var.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("shared_preference",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username",username);
                editor.apply();

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference("datauser");
                Query check_username = databaseReference.orderByChild("username").equalTo(username);

                check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            username_var.setError(null);
                            String userNameData = snapshot.child(username).child("username").getValue(String.class);
                            String check_password = snapshot.child(username).child("password").getValue(String.class);
                            if(check_password.equals(password)){
                                password_var.setError(null);
                                //share username
                                SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("username", userNameData);
                                editor.apply();
                                startActivity(new Intent(getApplicationContext(),Menu.class));
                                finish();
                            }else{
                                passwordLayout.setHelperText("Password Incorrect");
                            }
                        }else{
                            username_var.setError("User not register!");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDiologue = new AlertDialog.Builder(Login.this);
        alertDiologue.setTitle("Exit App");
        alertDiologue.setMessage("Do you want to close app?");
        alertDiologue.setPositiveButton("Yes" ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        alertDiologue.setNegativeButton("No" ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDiologue.show();
    }
}