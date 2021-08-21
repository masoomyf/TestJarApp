package com.masoomyf.assignmentjar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.masoomyf.assignmentjar.R
import com.masoomyf.assignmentjar.databinding.FragmentLauncherBinding

class LauncherFragment: Fragment() {

    private lateinit var binding: FragmentLauncherBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLauncherBinding.inflate(layoutInflater, container, false)
        context ?: return binding.root

        bindUI()

        return binding.root
    }

    private fun bindUI() {
        binding.launchScreenEvent1 = View.OnClickListener {
            val action = R.id.action_launcherFragment_to_shapeFragment
            findNavController().navigate(action)
        }

        binding.launchScreenEvent2 = View.OnClickListener {
            val action = R.id.action_launcherFragment_to_galleryFragment
            findNavController().navigate(action)
        }
    }
}