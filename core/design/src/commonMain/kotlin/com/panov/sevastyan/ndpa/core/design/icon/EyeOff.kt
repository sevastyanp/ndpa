package com.panov.sevastyan.ndpa.core.design.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val NdpaIcons.EyeOff: ImageVector
    get() {
        if (_EyeOff != null) {
            return _EyeOff!!
        }
        _EyeOff = ImageVector.Builder(
            name = "EyeOff",
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
                moveTo(4.5f, 4.5f)
                lineTo(19.5f, 19.5f)
                moveTo(2.6f, 12f)
                curveTo(5.1f, 7.6f, 8.5f, 5.6f, 12f, 5.6f)
                curveTo(15.5f, 5.6f, 18.9f, 7.6f, 21.4f, 12f)
                curveTo(18.9f, 16.4f, 15.5f, 18.4f, 12f, 18.4f)
                curveTo(8.5f, 18.4f, 5.1f, 16.4f, 2.6f, 12f)
                close()
                moveTo(12f, 9f)
                curveTo(13.657f, 9f, 15f, 10.343f, 15f, 12f)
                curveTo(15f, 13.657f, 13.657f, 15f, 12f, 15f)
                curveTo(10.343f, 15f, 9f, 13.657f, 9f, 12f)
                curveTo(9f, 10.343f, 10.343f, 9f, 12f, 9f)
                close()
            }
        }.build()

        return _EyeOff!!
    }

@Suppress("ObjectPropertyName")
private var _EyeOff: ImageVector? = null
