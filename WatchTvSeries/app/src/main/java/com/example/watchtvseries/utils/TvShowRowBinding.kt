package com.example.watchtvseries.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import okhttp3.HttpUrl

class TvShowRowBinding {
    companion object {

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: HttpUrl) {
            imageView.load(imageUrl) {

            }
        }
    }
}