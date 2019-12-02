package com.example.eva3_5_handler_post;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();
    //AQUI NO PODEMOS MODIFICAR LA UI
    Thread tHilo = new Thread() {
        @Override
        public void run() {
            super.run();
            //AQUI VA EL TRABAJO EN SEGUNDO PLANO
            while (true) {
                try {
                    Thread.sleep(500);
                    handler.post(rModoficaUI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    Runnable rModoficaUI = new Runnable() {
        @Override
        public void run() {
            //AQUI VA EL TRABAJO DE MODIFICAR LA UI
            txtV.append("Hola mundo!!");
            txtV.append("\n");
        }
    };


    TextView txtV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtV = findViewById(R.id.textView);
        tHilo.start();
    }
}
