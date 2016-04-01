package com.xiazhiri.practice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.xiazhiri.practice.util.L;

public class MyService extends Service {

    public MyService() {
    }

    @Override
    public void onCreate() {
        L.e();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        L.e();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        L.e();
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        L.e();
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        L.e();
        super.onRebind(intent);
    }


    @Override
    public void onDestroy() {
        L.e();
        super.onDestroy();
    }

}
