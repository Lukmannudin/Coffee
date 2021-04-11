package com.oleg.coffee.helper

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.oleg.coffee.R

/**
 * Crafted by Lukman on 04/04/2021.
 **/

fun ImageView.setImage(uri: String) {
    Glide.with(this.context).load(uri)
        .centerCrop()
        .placeholder(R.drawable.ic_no_photos)
        .into(this)
}