package com.panov.sevastyan.ndpa.core.design.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val NdpaIcons.BellOff: ImageVector
    get() {
        if (_BellOff != null) {
            return _BellOff!!
        }
        _BellOff = ImageVector.Builder(
            name = "BellOff",
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
                moveTo(6.6f, 17.4f)
                verticalLineTo(11f)
                curveTo(6.6f, 8.02f, 9.02f, 5.6f, 12f, 5.6f)
                curveTo(14.98f, 5.6f, 17.4f, 8.02f, 17.4f, 11f)
                verticalLineTo(17.4f)
                moveTo(4.4f, 17.4f)
                horizontalLineTo(19.6f)
                moveTo(10f, 20.2f)
                horizontalLineTo(14f)
                moveTo(4.5f, 4.5f)
                lineTo(19.5f, 19.5f)
            }
        }.build()

        return _BellOff!!
    }

@Suppress("ObjectPropertyName")
private var _BellOff: ImageVector? = null
