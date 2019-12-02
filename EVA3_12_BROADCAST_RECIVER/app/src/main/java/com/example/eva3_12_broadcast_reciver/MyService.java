package com.example.eva3_12_broadcast_reciver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent inMensa = new Intent("MI_SERVICIO");
        inMensa.putExtra("MENSAJE","onCreate");
        sendBroadcast(inMensa);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Intent inMensa = new Intent("MI_SERVICIO");
        inMensa.putExtra("MENSAJE","onStart");
        sendBroadcast(inMensa);
        Thread tHilo = new Thread(){
            @Override
            public void run() {
                super.run();
                while(true){
                    try {
                        Thread.sleep(500);
                        Intent inMensa2 = new Intent("MI_SERVICIO");
                        inMensa2.putExtra("MENSAJE","-OCUPADO");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent inMensa = new Intent("MI_SERVICIO");
        inMensa.putExtra("MENSAJE","onDestroy");
        sendBroadcast(inMensa);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
