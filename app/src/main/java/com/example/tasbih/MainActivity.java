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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView showTasbih,countv;
    AppCompatButton countBtn,terminateBtn;
    int cc=0,i=0,j=0,k=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorText));



        showTasbih = findViewById(R.id.read);
        countv = findViewById(R.id.count);
        countBtn = findViewById(R.id.cbtn);
        terminateBtn = findViewById(R.id.delbtn);

        countBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "app work", Toast.LENGTH_SHORT).show();
                if(cc==100){
                    cc=i=j=k=0;
                    //Toast.makeText(MainActivity.this, "101", Toast.LENGTH_SHORT).show();
                }
                if(cc<33)
                {
                    //Toast.makeText(MainActivity.this, "34", Toast.LENGTH_SHORT).show();
                    showTasbih.setText("Allahu Akbar");
                    allahuakbar();
                }
                else if(cc>=33 && cc<66)
                {
                    if(cc==34) {
                        Vibrator variable = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    }
                   // Toast.makeText(MainActivity.this, "67", Toast.LENGTH_SHORT).show();
                    showTasbih.setText("SUBHANALLAH");
                    subahanallah();
                }
                else if(cc>=66 && cc<99)
                {
                    if(cc==67) {
                        Vibrator variable = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        variable.vibrate(200);
                    }
                  //  Toast.makeText(MainActivity.this, "67", Toast.LENGTH_SHORT).show();
                    showTasbih.setText("Alhamdulillah");
                    alhamduLillah();
                }

                //Toast.makeText(MainActivity.this, "00", Toast.LENGTH_SHORT).show();
                ++cc;
            }
        });
        terminateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Menu.class));
            }
        });
    }

    private void alhamduLillah() {
        ++k;
        String s = String.valueOf(k);
        countv.setText(s);
    }

    void allahuakbar()
    {
        ++i;
        String s = String.valueOf(i);
        countv.setText(s);
    }
    void subahanallah()
    {
        ++j;
        String s = String.valueOf(j);
        countv.setText(s);
    }
}