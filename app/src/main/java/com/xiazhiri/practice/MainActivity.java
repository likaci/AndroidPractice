package com.xiazhiri.practice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xiazhiri.practice.api.TaobaoService;
import com.xiazhiri.practice.base.BaseActivity;
import com.xiazhiri.practice.model.BaseModel;
import com.xiazhiri.practice.model.IPInfo;
import com.xiazhiri.practice.util.L;
import com.xiazhiri.practice.util.RxUtil;

import rx.functions.Action1;

public class MainActivity extends BaseActivity {

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
                TaobaoService.queryIPInfo("8.8.8.8")
                        .compose(RxUtil.<BaseModel<IPInfo>>asyncAndLogErrorAndBindLifecycle(MainActivity.this))
                        .subscribe(new Action1<BaseModel<IPInfo>>() {
                            @Override
                            public void call(BaseModel<IPInfo> ipInfoBaseModel) {
                                L.e(ipInfoBaseModel.getData().getCountry());
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        });

            }
        });

    }
}
