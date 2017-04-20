package ru.justd.backbaseassignment.list.view;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.justd.backbaseassignment.R;
import ru.justd.backbaseassignment.common.utils.ImageUtils;
import ru.justd.backbaseassignment.list.model.Member;

/**
 * Created by defuera on 20/04/2017.
 */

public class MemberWidget extends FrameLayout {

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.role)
    TextView role;

    @BindView(R.id.email)
    TextView email;

    public MemberWidget(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.widget_member, this);
        ButterKnife.bind(this);
    }

    public void bind(Member member) {
        name.setText(member.name + " " + member.surname);
        role.setText(member.role);
        email.setText(member.email);
        ImageUtils.loadUserpic(getContext(), member.photo, image);
    }
}
