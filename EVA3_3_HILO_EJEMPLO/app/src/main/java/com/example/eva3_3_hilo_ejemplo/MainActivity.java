package com.example.eva3_3_hilo_ejemplo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imgVwMostrar;
    Bitmap imagen = null;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            imgVwMostrar.setImageBitmap(imagen);
        }
    };

    Thread hilo = new Thread(){
        @Override
        public void run() {
            super.run();
            imagen = cargarImagen("https://cdn.forbes.com.mx/2018/12/perro_china-640x360.jpg");
            Message msg = handler.obtainMessage();
            handler.sendMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVwMostrar = findViewById(R.id.imageView);
        hilo.start();
       // Bitmap bitmap = cargarImagen("https://cdn.forbes.com.mx/2018/12/perro_china-640x360.jpg");
        //imgVwMostrar.setImageBitmap(bitmap);
    }

    private Bitmap cargarImagen(String url){
        Bitmap imagen = null;
        try{
            InputStream input = (InputStream)new URL(url).getContent();
            imagen = BitmapFactory.decodeStream(input);
        }catch (Exception e){
            e.printStackTrace();
        }
        return imagen;
    }
}
