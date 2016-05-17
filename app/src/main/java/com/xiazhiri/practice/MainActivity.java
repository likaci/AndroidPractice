package com.xiazhiri.practice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xiazhiri.practice.util.L;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func3;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Observable.just("network")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        L.e(s + " Step1");
                        return s;
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(final String s) {
                        return Observable.zip(Observable.just(s), Observable.just(s), Observable.just(s), new Func3<String, String, String, String>() {
                            @Override
                            public String call(String s1, String s2, String s3) {
                                L.e(s1 + s2 + s3 + " Step2");
                                return s1 + s2 + s3;
                            }
                        }).toBlocking().first();
                    }

                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        L.e(s + " Step3");
                        return s;
                    }
                })
                .subscribe();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

}
