package ru.justd.backbaseassignment.list.presenter;

import javax.inject.Inject;

import ru.justd.backbaseassignment.common.BasePresenter;
import ru.justd.backbaseassignment.list.model.MembersRepository;
import ru.justd.backbaseassignment.list.view.MainView;

/**
 * Created by defuera on 20/04/2017.
 */

public class MainPresenter extends BasePresenter<MainView> {

    @Inject
    MembersRepository interactor;

    @Inject
    MainPresenter() {}

    @Override
    protected void onViewAttached() {
        loadData();
    }

    private void loadData() {
        view().showLoading(true);
        subscribe(
                interactor.fetchMembers(),
                departments -> {
                    view().showLoading(false);
                    view().showData(departments);
                },
                throwable -> view().showError(throwable)
        );
    }

    public void reloadData() {
        loadData();
    }

}
