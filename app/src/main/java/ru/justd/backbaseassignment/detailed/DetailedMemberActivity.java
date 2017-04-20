package ru.justd.backbaseassignment.detailed;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by defuera on 20/04/2017.
 */

public class DetailedMemberActivity extends AppCompatActivity {
    private static final String EXTRA_MEMBER_ID = "EXTRA_MEMBER_ID";

    public static void start(Context context, String id) {
        Intent intent = new Intent(context, DetailedMemberActivity.class);
        intent.putExtra(EXTRA_MEMBER_ID, id);
        context.startActivity(intent);
    }
}
