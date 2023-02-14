package com.example.tasbih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class InfinityTasbeeh extends AppCompatActivity {
    AppCompatButton backButton;
    TextView cntTasbeeh;
    AppCompatButton count_i,back_i;
    int cnt=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinity_tasbeeh);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorText));

        cntTasbeeh = findViewById(R.id.count_tasbeeh);
        count_i = findViewById(R.id.cntbtn_i);
        back_i = findViewById(R.id.terBtn_i);
        backButton = findViewById(R.id.terBtn_i);

        count_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cnt<99999){
                    ++cnt;
                    count_tasbeeh(cnt);
                }
                else{
                    cnt=1;
                    count_tasbeeh(cnt);
                }
                if(cnt==99999) {
                    Vibrator variable = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    variable.vibrate(200);
                }


            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Menu.class));
            }
        });

    }

    private void count_tasbeeh(int cnt) {
        String so = String.format("%05d", cnt);
        cntTasbeeh.setText(so);
    }
}