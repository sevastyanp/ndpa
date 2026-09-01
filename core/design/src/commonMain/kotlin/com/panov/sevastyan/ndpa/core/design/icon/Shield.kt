package com.panov.sevastyan.ndpa.core.design.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val NdpaIcons.Shield: ImageVector
    get() {
        if (_Shield != null) {
            return _Shield!!
        }
        _Shield = ImageVector.Builder(
            name = "Shield",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(12f, 3.5f)
                lineTo(19f, 6.5f)
                verticalLineTo(11.5f)
                curveTo(19f, 15.8f, 16.1f, 19.1f, 12f, 20.5f)
                curveTo(7.9f, 19.1f, 5f, 15.8f, 5f, 11.5f)
                verticalLineTo(6.5f)
                lineTo(12f, 3.5f)
                close()
            }
        }.build()

        return _Shield!!
    }

@Suppress("ObjectPropertyName")
private var _Shield: ImageVector? = null
