package com.xiazhiri.practice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


        new Thread(new Runnable() {
            @Override
            public void run() {
                Observable.just(null)
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Func1<Object, Object>() {
                            @Override
                            public Object call(Object o) {
                                Log.e(TAG, "map1: " + Thread.currentThread().getName());
                                return null;
                            }
                        })
                        .observeOn(Schedulers.io())
                        .map(new Func1<Object, Object>() {
                            @Override
                            public Object call(Object o) {
                                Log.e(TAG, "map2: " + Thread.currentThread().getName());
                                return null;
                            }
                        })
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                Log.e(TAG, "onSubscribe: " + Thread.currentThread().getName());
                            }
                        })
                        .subscribe(new Action1<Object>() {
                            @Override
                            public void call(Object o) {
                                Log.e(TAG, "subscribe: " + Thread.currentThread().getName());
                            }
                        });
            }
        }).start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
