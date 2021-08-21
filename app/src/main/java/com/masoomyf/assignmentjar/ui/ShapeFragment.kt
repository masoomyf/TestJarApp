package com.masoomyf.assignmentjar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.masoomyf.assignmentjar.databinding.FragmentShapeBinding
import com.masoomyf.assignmentjar.widgets.ShapeMakerView

class ShapeFragment : Fragment() {

    private lateinit var binding: FragmentShapeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShapeBinding.inflate(layoutInflater, container, false)
        context ?: return binding.root

        bindUI()
        return binding.root
    }


    private fun bindUI() {
        binding.addCircleEvent = View.OnClickListener {
            val success = binding.shapeContainer.addShape(ShapeMakerView.SHAPE_CIRCLE)
            if (!success) {
                Toast.makeText(activity, "No space left", Toast.LENGTH_SHORT).show()
            }
        }

        binding.addSquareEvent = View.OnClickListener {
            val success = binding.shapeContainer.addShape(ShapeMakerView.SHAPE_SQUARE)
            if (!success) {
                Toast.makeText(activity, "No space left", Toast.LENGTH_SHORT).show()
            }
        }

        binding.undoEvent = View.OnClickListener {
            val success = binding.shapeContainer.undo()
            if (!success) {
                Toast.makeText(activity, "Nothing to undo", Toast.LENGTH_SHORT).show()
            }
        }
    }

}