package com.example.eatanddrink.util

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.eatdrink.R

@BindingAdapter("categoryImage")
fun bindImage(imgView : ImageView , imgUri:String?){
    imgUri?.let {
        Glide.with(imgView.context)
            .load(imgUri)
            .dontAnimate()
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

