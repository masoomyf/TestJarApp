package com.masoomyf.assignmentjar.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.graphics.withTranslation
import com.masoomyf.assignmentjar.data.MyShape
import java.util.*
import kotlin.collections.ArrayList

class ShapeMakerView : View {

    private var cellSize = 0f
    private var shapeStack: Stack<MyShape> = Stack()
    private var shapeData: ArrayList<MyShape> = ArrayList()


    private lateinit var mPaint: Paint

    companion object {
        const val SHAPE_EMPTY = 0
        const val SHAPE_CIRCLE = 1
        const val SHAPE_SQUARE = 2
    }

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }


    private fun init() {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.BLACK
            strokeWidth = 3f
        }

        cellSize = 50 * resources.displayMetrics.density
    }

    fun addShape(shapeType: Int): Boolean {
        val list = shapeData.filter { it.shape == SHAPE_EMPTY }
        if (list.isEmpty()) {
            return false
        }

        val shape = list.random()
        shape.shape = shapeType
        Log.d("SHAPEX", shape.toString())
        shapeStack.add(shape)

        invalidate()
        return true
    }

    fun undo(): Boolean {
        if (shapeStack.isEmpty()) {
            return false
        }
        val myShape = shapeStack.pop()
        shapeData.forEach { shape ->
            if (shape.row == myShape.row && shape.col == myShape.col) {
                shape.shape = SHAPE_EMPTY
                invalidate()
                return true
            }
        }
        return false
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        canvas ?: return
        canvas.drawColor(Color.WHITE)
        for (shape in shapeData) {

            when (shape.shape) {
                SHAPE_CIRCLE -> {
                    canvas.withTranslation(shape.col * cellSize, shape.row * cellSize) {
                        val rx = cellSize * 0.5f
                        canvas.drawCircle(rx, rx, rx, mPaint)
                    }
                }

                SHAPE_SQUARE -> {
                    canvas.withTranslation(shape.col * cellSize, shape.row * cellSize) {
                        canvas.drawRect(0f, 0f, cellSize, cellSize, mPaint)
                    }
                }
            }

        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val cols = (w / cellSize).toInt()
        val rows = (h / cellSize).toInt()

        shapeData.clear()
        shapeStack.clear()

        repeat(rows) {row->
            repeat(cols) {col->
                shapeData.add(MyShape(row, col, SHAPE_EMPTY))
            }
        }
    }

}