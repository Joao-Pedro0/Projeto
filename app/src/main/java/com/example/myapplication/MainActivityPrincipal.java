package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Handler;
import android.widget.TextView;


public class MainActivityPrincipal extends AppCompatActivity {
    private boolean running;
    private long startTime;
    public Button btnParar;
    public Button btnInicio;
    public TextView txtTempo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);

        btnParar = findViewById(R.id.btnParar);
        btnInicio = findViewById(R.id.btnInicio);
        txtTempo = findViewById(R.id.textViewTempo);

        startTime = 0;
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running == false) {
                    Iniciar();
                }
            }
        });

        btnParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (running == true) {
                    Parar();
                }
                txtTempo.setText("00:00:00:000");
            }
        });

    }

    private void Iniciar() {
        running = true;
        startTime = System.currentTimeMillis();
        Atualiza();
    }

    private void Parar() {
        running = false;
    }

    private void Atualiza() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis() - startTime;
                int hours = (int) (time / 1000) / 3600;
                int minutes = (int) ((time / 1000) % 3600) / 60;
                int seconds = (int) (time / 1000) % 60;
                int milliseconds = (int) (time % 1000);

                String timeFormatted = String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, milliseconds);
                txtTempo.setText(timeFormatted);

                if (running) {
                    handler.postDelayed(this, 0);
                }
            }
        });

    }
}