package ru.justd.backbaseassignment.common;

import rx.Observable;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by defuera on 20/04/2017.
 */

public abstract class BasePresenter<V> {

    private V view;
    private CompositeSubscription subscriptions = new CompositeSubscription();

    public void attachView(V view) {
        this.view = view;
        onViewAttached();
    }

    public void detachView() {
        this.view = null;
        subscriptions.clear();
        onViewDetached();
    }

    protected V view() {
        return view;
    }

    protected abstract void onViewAttached();

    protected void onViewDetached() {}

    protected <T> void subscribe(
            Observable<T> observable,
            Action1<T> onNext,
            Action1<Throwable> onError,
            Action0 onCompleted
    ) {
        subscriptions.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io())
                        .subscribe(onNext, onError, onCompleted)
        );
    }

    protected <T> void subscribe(
            Single<T> single,
            Action1<T> onSuccess,
            Action1<Throwable> onError
    ) {
        subscriptions.add(
                single
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onSuccess, onError)
        );
    }

//    fun<T> subscribe(
//            single:Single<T>,
//            singleSubscriber:SingleSubscriber<T>
//    ) {
//        subscriptions.add(
//                single.subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(singleSubscriber)
//        )
//    }
//
//    fun<T> subscribe(
//            single:Single<T>,
//            onSuccess:Action1<T>=Action1 { },
//    onError:Action1<Throwable> =
//
//    Action1(Throwable::printStackTrace))
//
//    {
//        subscriptions.add(
//                single.subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(onSuccess, onError)
//        )
//    }
//
//    fun subscribe(
//            completable:Completable,
//            onSuccess:Action0=Action0 { },
//    onError:Action1<Throwable> =Action1
//
//    { })
//
//    {
//        subscriptions.add(
//                completable
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(onSuccess, onError))
//    }
}
