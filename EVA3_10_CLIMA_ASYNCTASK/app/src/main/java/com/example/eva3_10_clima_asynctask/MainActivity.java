package com.example.eva3_10_clima_asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ClimaAsynk().execute();

    }
}

class ClimaAsynk extends AsyncTask<Void, Void, String>{

    final String ruta = "https://samples.openweathermap.org/data/2.5/group?id=524901,703448,2643743&units=metric&appid=b6907d289e10d714a6e88b30761fae22";

    @Override
    protected String doInBackground(Void... voids) {
        String sResu = null;
        //AQUI SE HACE LA CONEXIÓN
        try {
            URL url = new URL(ruta);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.connect();
            if (http.getResponseCode() == HttpURLConnection.HTTP_OK){
                //LEER LA RESPUESTA
                String linea;
                StringBuffer lineas = new StringBuffer();
                InputStream inputStream = http.getInputStream();
                InputStreamReader isReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(isReader);

                while((linea = bufferedReader.readLine()) !=  null){
                    lineas.append(linea);
                }
                sResu = lineas.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sResu;
    }

    @Override
    protected void onPostExecute(String s) {    //s ES LA VARIABLE QUE TAREMOS DEL DO IN BACKGROUND
        super.onPostExecute(s);
        if(s != null){
            //AQUI PROCESAMOS LOS OBJETOS JSON
            Log.wtf("cadena",s);
            //Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonClima = new JSONObject(s);
                JSONArray jsonCiudades = jsonClima.getJSONArray("list");
                for (int i = 0; i<jsonCiudades.length();i++){
                    //LEER CADA CIUDAD Y PONER LOS DATOS EN UNA LISTA
                    JSONObject jsonCIudad = jsonCiudades.getJSONObject(i);
                    //NOMBRE DE LACIUDAD
                    //jsonCIudad.getString("name");

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}
