package com.example.eva3_1_multitarea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Thread tMiHilo;
    @Override
    protected void onStop() {
        super.onStop();
        tMiHilo.interrupt();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView txtView;

        txtView = findViewById(R.id.textView);
        tMiHilo = new Thread(){
            @Override
            public void run() {
                super.run();
                int i=0;
                while(true){
                //for (int i=0;i<10;i++){
                    try {
                        Thread.sleep(1000);
                        Log.wtf("hilo","i = "+i);
                        //txtView.append("Hilo = " + i);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.wtf("error Hilo","no se arma");
                        break;      //Detiene el hilo de ejecución saliendo del ciclo infinito
                    }
                i++;
                }
            }
        };
        ;tMiHilo.interrupt(); //Generar una interrupcion en el hilo
        tMiHilo.start();
        //tMiHilo.run(); //Si se ejecuta con este método no jala chido porque es como ejecutarlo en el onCreate
        Runnable rRun = new Runnable() {
            @Override
            public void run() {
                int i=0;
                while(true){
                    //for (int i=0;i<10;i++){
                    try {
                        Thread.sleep(1000);
                        Log.wtf("Runnable","i = "+i);
                        //txtView.append("Runnable = " + i);    <--- No jala
                    } catch (InterruptedException e) {
                            e.printStackTrace();

                    }
                    i++;
                }
            }
        };
        Thread rHilo = new Thread(rRun);
        rHilo.start();
    }
}
