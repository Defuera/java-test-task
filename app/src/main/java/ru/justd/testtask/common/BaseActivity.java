package ru.justd.testtask.common;

import android.content.Intent;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by defuera on 20/04/2017.
 */

public abstract class BaseActivity<V, P extends BasePresenter<V>> extends AppCompatActivity {

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        attachPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        attachPresenter();
    }

    @Override
    protected void onPause() {
        super.onPause();
        detachPresenter();
    }

    @CallSuper
    protected void attachPresenter() {
        presenter().attachView(view());
    }

    @CallSuper
    protected void detachPresenter() {
        presenter().detachView();
    }

    protected abstract V view();

    protected abstract P presenter();

}