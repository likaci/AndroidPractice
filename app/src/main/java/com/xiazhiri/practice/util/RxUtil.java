package com.xiazhiri.practice.util;


import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by liuwencai on 15/12/2.
 */

public class RxUtil {

    /**
     * via https://yongjhih.gitbooks.io/feed/content/RxJava.html
     * io线程执行 ui线程回调
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> mainAsync() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return mainAsync(observable);
            }
        };
    }

    public static <T> Observable.Transformer<T, T> logError() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return logError(observable);
            }
        };
    }

    public static <T> Observable.Transformer<T, T> asyncAndLogError() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return logError(mainAsync(observable));
            }
        };
    }

    public static <T> Observable.Transformer<T, T> asyncAndLogErrorAndBindLifecycle(final RxAppCompatActivity appCompatActivity) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return bindToLifecycle(logError(mainAsync(observable)), appCompatActivity);
            }
        };
    }

    private static <T> Observable<T> mainAsync(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private static <T> Observable<T> logError(Observable<T> observable) {
        return observable.doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private static <T> Observable<T> bindToLifecycle(Observable<T> observable, RxAppCompatActivity rxActivity) {
        return observable.compose(rxActivity.<T>bindToLifecycle());
    }

    //public static <T> Observable.Transformer<T, T> progress(final DialogControl dialogControl) {
    //    return new Observable.Transformer<T, T>() {
    //        @Override
    //        public Observable<T> call(Observable<T> observable) {
    //            return observable.doOnSubscribe(new Action0() {
    //                @Override
    //                public void call() {
    //                    dialogControl.showWaitDialog();
    //                }
    //            }).doOnCompleted(new Action0() {
    //                @Override
    //                public void call() {
    //                    dialogControl.hideWaitDialog();
    //                }
    //            }).doOnError(new Action1<Throwable>() {
    //                @Override
    //                public void call(Throwable throwable) {
    //                }
    //            });
    //        }
    //    };
    //}

}
