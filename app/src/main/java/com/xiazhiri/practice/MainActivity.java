package com.xiazhiri.practice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xiazhiri.practice.util.L;

import java.util.Random;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

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
                test();
            }
        });


    }

    private void test() {
        L.e("----------");
        Observable.just("disk")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return new Random().nextBoolean() ? s : "diskCacheError";
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        if (s.contains("Error")) {
                            L.e("Filter1 " + s);
                            throw new RuntimeException();
                        }
                        return s;
                    }
                })
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends String>>() {
                    @Override
                    public Observable<? extends String> call(Throwable throwable) {
                        return  Observable.just("network")
                                .map(new Func1<String, String>() {
                                    @Override
                                    public String call(String s) {
                                        return new Random().nextBoolean() ? s : "netWorkError";
                                    }

                                })
                                .map(new Func1<String, String>() {
                                    @Override
                                    public String call(String s) {
                                        if (s.contains("Error")) {
                                            L.e("Filter2 " + s);
                                            throw new RuntimeException();
                                        }
                                        return s;
                                    }
                                })
                                .retry(new Func2<Integer, Throwable, Boolean>() {
                                    @Override
                                    public Boolean call(Integer integer, Throwable throwable) {
                                        L.e("Retry " + integer);
                                        return integer < 2;
                                    }
                                });
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        L.e("Result " + s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        L.e("Error after Retry");
                    }
                });
    }
}
