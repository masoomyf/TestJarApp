package com.masoomyf.assignmentjar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.masoomyf.assignmentjar.adapters.GalleryAdapter
import com.masoomyf.assignmentjar.databinding.FragmentGalleryBinding
import com.masoomyf.assignmentjar.viewmodels.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment: Fragment() {

    private lateinit var binding: FragmentGalleryBinding

    private var galleryLoadJob: Job? = null
    private val adapter = GalleryAdapter()
    private val viewModel: GalleryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(layoutInflater, container, false)
        context ?: return binding.root

        bindUI()

        return binding.root
    }

    private fun bindUI() {
        binding.galleryAdapter = adapter

        viewModel.photoList.observe(viewLifecycleOwner) {
            it ?: return@observe
            galleryLoadJob?.cancel()
            galleryLoadJob = lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
    }
}