package com.kronos.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset

object Extensions {
    @Composable
    fun Dp.toPx(): Float {
        return with(LocalDensity.current) { this@toPx.toPx() }
    }

    fun Offset.toIntOffset() = IntOffset(x.toInt(), y.toInt())


fun DrawScope.drawGrid(size: Size, offset: Offset, zoom: Float) {
    val gridSize = 50f * zoom
    val startX = offset.x % gridSize
    val startY = offset.y % gridSize

    // Dibujar las líneas verticales
    for (x in startX.toInt() until size.width.toInt() step gridSize.toInt()) {
        drawLine(
            color = Color(0xFF222222),
            start = Offset(x.toFloat(), 0f),
            end = Offset(x.toFloat(), size.height),
            strokeWidth = .1f
        )
    }

    // Dibujar las líneas horizontales
    for (y in startY.toInt() until size.height.toInt() step gridSize.toInt()) {
        drawLine(
            color = Color(0xFF222222),
            start = Offset(0f, y.toFloat()),
            end = Offset(size.width, y.toFloat()),
            strokeWidth = .1f
        )
    }
}

}