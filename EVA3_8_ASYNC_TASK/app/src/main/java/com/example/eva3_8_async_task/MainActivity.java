package com.example.eva3_8_async_task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewMostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewMostrar = findViewById(R.id.textview);
        MiClaseAsin miClase = new MiClaseAsin();
        miClase.execute(20, 500);       //Las veces que quiero que se ejecute y la demora

    }

    class MiClaseAsin extends AsyncTask<Integer,String,String>{

        //TODOS PUEDE INTERACTUAR CON LA UI, EXCEPTO doInBackground
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textViewMostrar.setText("INICIO DE LA CLASE ASÍNCRONA");
        }

        //EQUIVALENTE A RUN EN THREAD
        @Override
        protected String doInBackground(Integer... integers) {//Conexión o consulta a base de datos y se devuelve la consulta
                                                                //Y la manda a OnPostExecute
            int iVeces = integers[0];
            int iDemora = integers[1];
            for (int i = 0; i < iVeces; i++){
                try {
                    Thread.sleep(iDemora);
                    publishProgress(i + " - "); //Lo manda  a onProgressUpdate
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        return "FIN DE LA ASYNCTASK";   //lo recibe onPostExecute();
        }

        @Override
        protected void onProgressUpdate(String... values) { //Notificación de lo que se está haciendo en doInBackground
            super.onProgressUpdate(values);
            textViewMostrar.append(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textViewMostrar.append(s);
        }
    }
}

