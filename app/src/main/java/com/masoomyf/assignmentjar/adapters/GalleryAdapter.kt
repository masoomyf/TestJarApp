package com.masoomyf.assignmentjar.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.masoomyf.assignmentjar.data.UnsplashPhoto
import com.masoomyf.assignmentjar.databinding.ItemUnsplashPhotoBinding

class GalleryAdapter :
    PagingDataAdapter<UnsplashPhoto, GalleryAdapter.ViewHolder>(UnsplashPhoto.DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)

    }


    class ViewHolder(private val binding: ItemUnsplashPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UnsplashPhoto) {
            with(binding) {
                photo = item
                executePendingBindings()
            }
        }

        companion object {
            fun create(parent: ViewGroup) = ViewHolder(
                ItemUnsplashPhotoBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }
}