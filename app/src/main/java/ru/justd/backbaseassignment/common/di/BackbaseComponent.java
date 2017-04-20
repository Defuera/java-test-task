package ru.justd.backbaseassignment.common.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.justd.backbaseassignment.list.view.MainActivity;

/**
 * Created by defuera on 20/04/2017.
 */
@Singleton
@Component(modules = BackbaseModule.class)
public interface BackbaseComponent {
    void inject(MainActivity mainActivity);
}
