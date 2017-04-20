package ru.justd.backbaseassignment.list.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.m039.el_adapter.ListItemAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.justd.backbaseassignment.R;
import ru.justd.backbaseassignment.common.BackbaseApp;
import ru.justd.backbaseassignment.common.BaseActivity;
import ru.justd.backbaseassignment.detailed.DetailedMemberActivity;
import ru.justd.backbaseassignment.list.model.Member;
import ru.justd.backbaseassignment.list.presenter.MainPresenter;
import ru.justd.lilwidgets.LilLoaderWidget;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.loader)
    LilLoaderWidget loader;

    @Inject
    MainPresenter presenter;

    private final ListItemAdapter adapter = new ListItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BackbaseApp) getApplicationContext()).getComponent().inject(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        adapter
                .addViewCreator(
                        Member.class,
                        parent -> new MemberWidget(parent.getContext())
                )
                .addViewBinder(MemberWidget::bind)
                .addOnItemViewClickListener(
                        (view, item) -> DetailedMemberActivity.start(view.getContext(), item.getId())
                );

        recycler.setAdapter(adapter);
    }

    @Override
    protected MainView view() {
        return this;
    }

    @Override
    protected MainPresenter presenter() {
        return presenter;
    }

    @Override
    public void showLoading(boolean show) {
        if (show) {
            loader.showLoading();
        } else {
            loader.hide();
        }
    }

    @Override
    public void showData(List<Member> members) {
        adapter.addItems(members);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(Throwable throwable) {
        loader.showNetworkError();
    }

}
