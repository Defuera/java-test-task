package ru.justd.backbaseassignment.common;

import android.app.Application;

import ru.justd.backbaseassignment.common.di.BackbaseComponent;
import ru.justd.backbaseassignment.common.di.BackbaseModule;
import ru.justd.backbaseassignment.common.di.DaggerBackbaseComponent;

/**
 * Created by defuera on 20/04/2017.
 */

public class BackbaseApp extends Application {

    private BackbaseComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerBackbaseComponent
                .builder()
                .backbaseModule(new BackbaseModule(this))
                .build();
    }

    public BackbaseComponent getComponent() {
        return component;
    }
}
