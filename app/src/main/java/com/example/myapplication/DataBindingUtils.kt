package com.example.myapplication

import android.R
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


open class DataBindingUtils {

    @BindingAdapter("imageUrl")
    open fun loadImage(view: ImageView, url: String?) {
        Glide.with(view.context)
            .load(url)
            .placeholder(R.drawable.alert_light_frame)
            .into(view)
    }
}