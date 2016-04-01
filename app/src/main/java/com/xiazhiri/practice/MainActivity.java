package com.xiazhiri.practice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xiazhiri.practice.util.L;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            L.e();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            L.e();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @OnClick({R.id.start, R.id.stop, R.id.bind, R.id.unbind})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.start:
                intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.stop:
                intent = new Intent(this, MyService.class);
                stopService(intent);
                break;
            case R.id.bind:
                //intent = new Intent(this, MyService.class);
                intent = new Intent("com.xiazhiri.practice.IMyAidlInterface");
                intent.setPackage("com.xiazhiri.practice");
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                try {
                    unbindService(serviceConnection);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
