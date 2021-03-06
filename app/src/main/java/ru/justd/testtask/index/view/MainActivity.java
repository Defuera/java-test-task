package ru.justd.testtask.index.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.m039.el_adapter.ListItemAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.justd.testtask.R;
import ru.justd.testtask.common.TestTaskApp;
import ru.justd.testtask.common.BaseActivity;
import ru.justd.testtask.detailed.view.DetailedActivity;
import ru.justd.testtask.index.model.Department;
import ru.justd.testtask.index.model.Member;
import ru.justd.testtask.index.presenter.MainPresenter;
import ru.justd.lilwidgets.LilLoaderWidget;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.loader)
    LilLoaderWidget loader;

    @BindColor(R.color.bg_text_title)
    int titleBackgroundColor;

    @BindDimen(R.dimen.padding_8)
    int paddingVertical;

    @BindDimen(R.dimen.padding_16)
    int paddingHorizontal;

    @Inject
    MainPresenter presenter;

    private final ListItemAdapter adapter = new ListItemAdapter();


    //region BaseActivity implementation

    @Override
    protected MainView view() {
        return this;
    }

    @Override
    protected MainPresenter presenter() {
        return presenter;
    }

    //region


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TestTaskApp) getApplicationContext()).getComponent().inject(this);

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
                        (view, item) -> DetailedActivity.start(view.getContext(), item)
                );

        adapter
                .addViewCreator(
                        String.class,
                        parent -> {
                            TextView title = new TextView(parent.getContext());
                            title.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);
                            title.setTextAppearance(R.style.Text_Title);
                            title.setBackgroundColor(titleBackgroundColor);
                            return title;
                        }
                )
                .addViewBinder(TextView::setText);

        recycler.setAdapter(adapter);

        loader.setOnErrorClicked(error -> {
            if (error == LilLoaderWidget.NETWORK_ERROR){
                presenter().reloadData();
            }
        });
    }

    //region BaseView implementation

    @Override
    public void showLoading(boolean show) {
        if (show) {
            loader.showLoading();
        } else {
            loader.hide();
        }
    }

    @Override
    public void showData(List<Department> departments) {
        for (Department department : departments) {
            adapter.addItem(department.name);
            adapter.addItems(department.members);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(Throwable throwable) {
        throwable.printStackTrace();
        loader.showNetworkError();
    }

    //region

}
