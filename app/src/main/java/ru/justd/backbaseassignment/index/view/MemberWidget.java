package ru.justd.backbaseassignment.index.view;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.justd.backbaseassignment.R;
import ru.justd.backbaseassignment.common.utils.ImageUtils;
import ru.justd.backbaseassignment.index.model.Member;

/**
 * Created by defuera on 20/04/2017.
 */

public class MemberWidget extends FrameLayout {

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.name)
    TextView name;

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
        ImageUtils.loadUserpic(getContext(), member.photo, image);
    }
}
