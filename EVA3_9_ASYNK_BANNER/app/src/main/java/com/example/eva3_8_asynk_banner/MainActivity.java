package com.example.eva3_8_asynk_banner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    ImageView ImgBanner;
    int iCont = 0;
    int tiempo = 1000;
    SeekBar skbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImgBanner = findViewById(R.id.imageView);
        skbar = findViewById(R.id.seekBar);
        skbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (i) {
                    case 1:
                        i = 10;
                        break;
                    case 2:
                        i = 9;
                        break;
                    case 3:
                        i = 8;
                        break;
                    case 4:
                        i = 7;
                        break;
                    case 5:
                        i = 6;
                        break;
                    case 6:
                        i = 5;
                        break;
                    case 7:
                        i = 4;
                        break;
                    case 8:
                        i = 3;
                        break;
                    case 9:
                        i = 2;
                        break;
                    case 10:
                        i = 1;
                        break;

                }
                tiempo = i * 100;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        MiClaseAsin miClase = new MiClaseAsin();
        miClase.execute(1000);
    }

    class MiClaseAsin extends AsyncTask<Integer, Integer, String> {
        // Todos pueden interacturar con la ui, excepto doinbackground

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //txtV.setText("AsyncTask Start ");
        }

        // Equivalente del run en thread
        @Override
        protected String doInBackground(Integer... integers) {
            int iDemora = integers[0];
            while (true) {
                try {
                    Thread.sleep(tiempo);
                    publishProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //txtV.append(values[0]);
            switch (iCont) {
                case 0:
                    ImgBanner.setImageResource(R.drawable.cloudy);
                    iCont++;
                    break;
                case 1:
                    ImgBanner.setImageResource(R.drawable.snow);
                    iCont++;
                    break;
                case 2:
                    ImgBanner.setImageResource(R.drawable.sunny);
                    iCont++;
                    break;
                default:
                    iCont = 0;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //txtV.append(s);
        }
    }
}
