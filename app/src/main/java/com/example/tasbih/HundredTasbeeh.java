package com.example.tasbih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

public class HundredTasbeeh extends AppCompatActivity {
    TextView showTasbih_h, showTasbih_m;
    AppCompatButton countBtn, backButton;
    int cm=0,ch=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hundred_tasbeeh);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorText));



        showTasbih_h = findViewById(R.id.count_hone);
        showTasbih_m = findViewById(R.id.count_h);
        countBtn = findViewById(R.id.cntbtn_h);
        backButton = findViewById(R.id.terBtn_h);

        countBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "app work", Toast.LENGTH_SHORT).show();


                if(cm!=99)
                {
                    ++cm;
                    HundredTasbeeh_count(cm,ch);

                }
                else{
                    ++ch;
                    cm=0;
                    HundredTasbeeh_count(cm,ch);
                }
                if(cm==99) {
                    Vibrator variable = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    variable.vibrate(200);
                }

            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Menu.class));
            }
        });
    }


    private void HundredTasbeeh_count(int m,int h) {
        String s1 = String.format("%02d",m);
        String s2 = String.format("%02d",h);

        showTasbih_h.setText(s2);
        showTasbih_m.setText(s1);
    }

}
