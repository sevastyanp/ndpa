package com.panov.sevastyan.ndpa.core.design.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val NdpaIcons.ShieldOff: ImageVector
    get() {
        if (_ShieldOff != null) {
            return _ShieldOff!!
        }
        _ShieldOff = ImageVector.Builder(
            name = "ShieldOff",
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
                moveTo(12f, 2.8f)
                lineTo(20f, 5.8f)
                verticalLineTo(12f)
                curveTo(20f, 16.4f, 16.6f, 19.9f, 12f, 21.2f)
                curveTo(7.4f, 19.9f, 4f, 16.4f, 4f, 12f)
                verticalLineTo(5.8f)
                lineTo(12f, 2.8f)
                close()
            }
        }.build()

        return _ShieldOff!!
    }

@Suppress("ObjectPropertyName")
private var _ShieldOff: ImageVector? = null
