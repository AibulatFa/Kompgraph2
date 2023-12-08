package com.example.kompgraph2

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Region
import android.view.View

class PolygonView(context: Context) : View(context) {

    private val fillPaint: Paint = Paint()
    private val polygonPath: Path = Path()

    init {
        fillPaint.color = Color.RED
        fillPaint.style = Paint.Style.FILL
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Определите вершины вашего многоугольника
        val vertices = listOf(
            Point(100, 100),
            Point(200, 200),
            Point(300, 150),
            Point(250, 50),
            Point(150, 50)
        )

        // Создайте путь, соединяющий вершины многоугольника
        val path = Path()
        path.moveTo(vertices[0].x.toFloat(), vertices[0].y.toFloat())
        for (i in 1 until vertices.size) {
            path.lineTo(vertices[i].x.toFloat(), vertices[i].y.toFloat())
        }
        path.close()

        // Создайте регион, основанный на пути
        val region = Region()
        region.setPath(path, Region(0, 0, width, height))

        // Заливка области многоугольника с использованием XOR для граней
        val xorRegion = Region(0, 0, width, height)
        xorRegion.op(region, Region.Op.XOR)
        canvas.drawPath(xorRegion.getBoundaryPath(), fillPaint)
    }
}

data class Point(val x: Int, val y: Int)
