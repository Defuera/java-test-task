package ru.justd.backbaseassignment.common.view;

/**
 * Created by defuera on 20/04/2017.
 */

public interface LoaderView<D> {

    void showLoading(boolean show);

    void showData(D members);

    void showError(Throwable throwable);
}
