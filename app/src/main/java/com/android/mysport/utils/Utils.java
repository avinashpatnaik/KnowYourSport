package com.android.mysport.utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

/**
 * A utility class to serve common functions
 */
public class Utils {

    /**
     * Load the image using glide library
     *
     * @param context context
     * @param targetImageView targetImage
     * @param url url
     */
    public static void loadImage(@NonNull final Context context, @NonNull final ImageView targetImageView, @NonNull final String url) {
        Glide.with(context)
                .load(url)
                .optionalFitCenter()
                .into(targetImageView);
    }
}
