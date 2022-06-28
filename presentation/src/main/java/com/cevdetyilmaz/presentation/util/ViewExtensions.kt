package com.cevdetyilmaz.presentation.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cevdetyilmaz.presentation.R

fun ImageView.show(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_background)
        .into(this)
}