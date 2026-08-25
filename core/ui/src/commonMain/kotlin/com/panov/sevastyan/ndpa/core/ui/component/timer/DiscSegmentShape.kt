package com.panov.sevastyan.ndpa.core.ui.component.timer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

internal data class DiscSegmentShape(
    private val discDiameter: Dp,
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val radius = with(density) { discDiameter.toPx() } / 2f
        val center = Offset(x = size.width / 2f, y = size.height - radius)

        val disc = Path().apply { addOval(Rect(center = center, radius = radius)) }
        val band = Path().apply { addRect(Rect(offset = Offset.Zero, size = size)) }

        return Outline.Generic(Path.combine(PathOperation.Intersect, disc, band))
    }
}
