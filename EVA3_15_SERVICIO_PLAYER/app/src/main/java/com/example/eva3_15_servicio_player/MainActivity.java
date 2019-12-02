package com.example.eva3_15_servicio_player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent rola;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rola = new Intent(this,MyService.class);
    }
    public void iniciaRola(View v){
        startService(rola);
    }
    public void detieneRola(View v){
        stopService(rola);
    }
}
