package ru.justd.testtask.detailed.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.justd.testtask.R;
import ru.justd.testtask.common.utils.ImageUtils;
import ru.justd.testtask.index.model.Member;

/**
 * Created by defuera on 20/04/2017.
 */

public class DetailedActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.role)
    TextView role;

    @BindView(R.id.email)
    TextView email;


    //regions static

    private static final String EXTRA_MEMBER_ID = "EXTRA_MEMBER_ID";

    public static void start(Context context, Member member) {
        Intent intent = new Intent(context, DetailedActivity.class);
        intent.putExtra(EXTRA_MEMBER_ID, member);
        context.startActivity(intent);
    }

    //endregion


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detailed);
        ButterKnife.bind(this);

        displayData(getMember());
    }

    private void displayData(Member member) {
        name.setText(member.name + " " + member.surname);
        role.setText(member.role);
        email.setText(member.email.replace("backbase", "gmail"));
        ImageUtils.loadUserpic(this, member.photo, image);
    }

    public Member getMember() {
        return (Member) getIntent().getSerializableExtra(EXTRA_MEMBER_ID);
    }
}
