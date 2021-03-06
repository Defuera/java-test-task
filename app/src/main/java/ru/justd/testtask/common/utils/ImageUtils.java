package ru.justd.testtask.common.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import ru.justd.testtask.BuildConfig;
import ru.justd.testtask.R;

/**
 * Created by defuera on 20/04/2017.
 */

public class ImageUtils {
    private static final String PHOTOS_PATH = BuildConfig.BASE_URL + "photos/";

    public static void loadUserpic(Context context, String photo, ImageView image) {
        Picasso
                .with(context)
                .load(PHOTOS_PATH + photo)
                .transform(new CropCircleTransformation())
                .placeholder(R.drawable.userpic_placeholer)
                .into(image);
    }
}
