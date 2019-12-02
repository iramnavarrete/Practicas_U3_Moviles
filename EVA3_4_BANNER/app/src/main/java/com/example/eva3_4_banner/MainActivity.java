package com.example.eva3_4_banner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView ivBanner;
    SeekBar sb;
    int barra = 2000;
    int contador = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (contador){
                case(0):
                    ivBanner.setImageResource(R.drawable.snow);
                    contador++;
                    break;
                case(1):
                    ivBanner.setImageResource(R.drawable.sunny);
                    contador++;
                    break;
                case(2):
                    ivBanner.setImageResource(R.drawable.tornado);
                    contador=0;
                    break;
            }
        }
    };
    Thread hilo = new Thread(){
        @Override
        public void run() {
            super.run();
            while (true){
                try {
                    Thread.sleep(barra);
                    Message msg = handler.obtainMessage();
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivBanner = findViewById(R.id.imgVwBanner);
        sb = findViewById(R.id.seekBar);
        hilo.start();
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                barra = barra - progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
