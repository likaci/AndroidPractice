package com.xiazhiri.practice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.xiazhiri.practice.util.L;

public class MyService extends Service {
    IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            L.e("Wow");
        }
    };

    public MyService() {
    }

    @Override
    public void onCreate() {
        L.e();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.e(this.hashCode() + "");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        L.e(this.hashCode() + "");
        return binder;
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
