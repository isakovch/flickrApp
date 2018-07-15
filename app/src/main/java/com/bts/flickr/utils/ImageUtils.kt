package com.bts.flickr.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

object ImageUtils {

    fun loadImage(imageView: ImageView, url: String) {
        Picasso.get()
                .load(url)
                .noFade()
                .into(imageView)
    }
}