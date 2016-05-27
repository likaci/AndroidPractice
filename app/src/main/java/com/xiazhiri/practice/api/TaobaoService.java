package com.xiazhiri.practice.api;

import com.xiazhiri.practice.model.BaseModel;
import com.xiazhiri.practice.model.IPInfo;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liuwencai on 16/5/27.
 */
public class TaobaoService {
    private static final String BASE_URL = "http://ip.taobao.com/service/";

    public static Observable<BaseModel<IPInfo>> queryIPInfo(String ip) {
        return SingletonHolder.service.queryIPInfo(ip);
    }

    public interface TaoBaoServiceAPI {
        @GET("getIpInfo.php")
        Observable<BaseModel<IPInfo>> queryIPInfo(@Query("ip") String ip);
    }

    private static class SingletonHolder {
        private static final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        private static final TaoBaoServiceAPI service = retrofit.create(TaoBaoServiceAPI.class);
    }

}
