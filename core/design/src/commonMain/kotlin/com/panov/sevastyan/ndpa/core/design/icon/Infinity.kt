package com.panov.sevastyan.ndpa.core.design.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val NdpaIcons.Infinity: ImageVector
    get() {
        if (_Infinity != null) {
            return _Infinity!!
        }
        _Infinity = ImageVector.Builder(
            name = "Infinity",
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
                moveTo(12f, 12f)
                curveTo(9.778f, 9.033f, 7.556f, 7.556f, 5.333f, 7.556f)
                curveTo(4.155f, 7.556f, 3.024f, 8.024f, 2.191f, 8.857f)
                curveTo(1.357f, 9.691f, 0.889f, 10.821f, 0.889f, 12f)
                curveTo(0.889f, 13.179f, 1.357f, 14.309f, 2.191f, 15.143f)
                curveTo(3.024f, 15.976f, 4.155f, 16.444f, 5.333f, 16.444f)
                curveTo(7.556f, 16.444f, 9.778f, 14.967f, 12f, 12f)
                close()
                moveTo(12f, 12f)
                curveTo(14.222f, 14.967f, 16.444f, 16.444f, 18.667f, 16.444f)
                curveTo(19.845f, 16.444f, 20.976f, 15.976f, 21.809f, 15.143f)
                curveTo(22.643f, 14.309f, 23.111f, 13.179f, 23.111f, 12f)
                curveTo(23.111f, 10.821f, 22.643f, 9.691f, 21.809f, 8.857f)
                curveTo(20.976f, 8.024f, 19.845f, 7.556f, 18.667f, 7.556f)
                curveTo(16.444f, 7.556f, 14.222f, 9.033f, 12f, 12f)
                close()
            }
        }.build()

        return _Infinity!!
    }

@Suppress("ObjectPropertyName")
private var _Infinity: ImageVector? = null
