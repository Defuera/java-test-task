package ru.justd.testtask.common;

import android.app.Application;

import ru.justd.testtask.common.di.DaggerTestTaskComponent;
import ru.justd.testtask.common.di.TestTaskComponent;
import ru.justd.testtask.common.di.TestTaskModule;

/**
 * Created by defuera on 20/04/2017.
 */

public class TestTaskApp extends Application {

    private TestTaskComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerTestTaskComponent
                .builder()
                .testTaskModule(new TestTaskModule(this))
                .build();
    }

    public TestTaskComponent getComponent() {
        return component;
    }
}
