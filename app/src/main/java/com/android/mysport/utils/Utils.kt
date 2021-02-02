package com.android.mysport.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * A utility class to serve common functions
 */
object Utils {
    /**
     * Load the image using glide library
     *
     * @param context context
     * @param targetImageView targetImage
     * @param url url
     */
    @JvmStatic
    fun loadImage(context: Context, targetImageView: ImageView, url: String) {
        Glide.with(context)
                .load(url)
                .optionalFitCenter()
                .into(targetImageView)
    }
}