package com.masoomyf.assignmentjar.data

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class UnsplashPhoto(
    @SerializedName("id") val id: String,
    @SerializedName("urls") val urls: UnsplashUrls,
) {
    object DiffCallback : DiffUtil.ItemCallback<UnsplashPhoto>() {
        override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
            return oldItem == newItem
        }
    }
}