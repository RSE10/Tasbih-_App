package com.example.tasbih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    //Fireable
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    //Veriable
    TextView gotoSignUp;
    TextInputEditText _Name,_UserName,_Email,_Password;
    AppCompatButton Register;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setStatusBarColor(ContextCompat.getColor(SignUp.this,R.color.colorText));

        gotoSignUp = findViewById(R.id.loginUpView);
        _Name = findViewById(R.id.name);
       _UserName = findViewById(R.id.userName);
        _Email = findViewById(R.id.email);
        _Password = findViewById(R.id.password);
        Register = findViewById(R.id.registerBtn);



        //Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();

        gotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = _Name.getText().toString();
                String username = _UserName.getText().toString();
                String email = _Email.getText().toString();
                String password = _Password.getText().toString();

                if(!name.isEmpty()){
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference("datauser");

                    String name_s = _Name.getText().toString();
                    String username_s = _UserName.getText().toString();
                    String email_s = _Email.getText().toString();
                    String password_s = _Password.getText().toString();

                    storeingregisterinfo storeingRegisterinfo = new storeingregisterinfo(name_s,username_s,email_s,password_s);
                    databaseReference.child(username_s).setValue(storeingRegisterinfo);
                    Toast.makeText(SignUp.this, "Successfully Store", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Login.class));
                    finish();
                }
                else{
                    _Name.setError("Not Filled");
                }
            }
        });
    }
}