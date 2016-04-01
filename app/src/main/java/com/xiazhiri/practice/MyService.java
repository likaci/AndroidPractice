package com.xiazhiri.practice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.xiazhiri.practice.util.L;

/**
 * Created by liuwencai on 16/4/1.
 */
public class MyService extends Service {

    MyBinder binder = new MyBinder();

    @Override
    public void onCreate() {
        L.e();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.e();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        L.e();
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        L.e();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        L.e();
        super.onDestroy();
    }

    class MyBinder extends Binder {
        public void fun() {
            L.e();
        }
    }

}
