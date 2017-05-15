package ru.justd.testtask.common.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.justd.testtask.index.view.MainActivity;

/**
 * Created by defuera on 20/04/2017.
 */
@Singleton
@Component(modules = TestTaskModule.class)
public interface TestTaskComponent {
    void inject(MainActivity mainActivity);
}
