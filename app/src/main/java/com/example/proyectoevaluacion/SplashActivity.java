package com.example.proyectoevaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //animarImagen();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        },1000);
    }

    private void animarImagen() {
        final ImageView imagen = findViewById(R.id.imagen_splash);

        for (int i = 0; i < 30; i++) {
                    /*if (i % 4 == 0) {
                        imagen.setImageResource(R.drawable.dragon4);
                    } else*/
            if (i % 3 == 0) {
                imagen.setImageResource(R.drawable.dragon3);
            } else if (i % 2 == 0) {
                imagen.setImageResource(R.drawable.dragon2);
            } else {
                imagen.setImageResource(R.drawable.dragon1);
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}