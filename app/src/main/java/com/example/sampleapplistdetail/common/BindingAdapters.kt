package com.example.sampleapplistdetail.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sampleapplistdetail.R

class BindingAdapters {
    companion object {
        @BindingAdapter("urlToImage")
        @JvmStatic
        fun urlToImage(view: ImageView, s: String?) {
            val options = RequestOptions.placeholderOf(R.drawable.ic_launcher_background)
                .error(R.drawable.error)
            Glide.with(view).setDefaultRequestOptions(options).load(s ?: "").into(view)
        }
    }
}