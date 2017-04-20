package ru.justd.backbaseassignment.list.view;

import android.content.Context;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import ru.justd.backbaseassignment.R;
import ru.justd.backbaseassignment.list.model.Member;

/**
 * Created by defuera on 20/04/2017.
 */

class MemberWidget extends FrameLayout {

    public MemberWidget(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.widget_member, this);
        ButterKnife.bind(this);
    }

    public void bind(Member item) {

    }
}
