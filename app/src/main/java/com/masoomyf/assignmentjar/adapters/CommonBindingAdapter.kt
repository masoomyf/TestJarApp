package com.masoomyf.assignmentjar.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load

@BindingAdapter("loadImage")
fun ImageView.loadImage(src: String) {
    load(src)
}

@BindingAdapter("rvAdapter")
fun RecyclerView.loadAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter
}