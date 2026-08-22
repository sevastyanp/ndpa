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
            defaultWidth = 108.dp,
            defaultHeight = 48.dp,
            viewportWidth = 108f,
            viewportHeight = 48f,
        ).apply {
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 6.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(54f, 24f)
                curveTo(44f, 10.65f, 34f, 4f, 24f, 4f)
                curveTo(18.7f, 4f, 13.61f, 6.11f, 9.86f, 9.86f)
                curveTo(6.11f, 13.61f, 4f, 18.7f, 4f, 24f)
                curveTo(4f, 29.3f, 6.11f, 34.39f, 9.86f, 38.14f)
                curveTo(13.61f, 41.89f, 18.7f, 44f, 24f, 44f)
                curveTo(34f, 44f, 44f, 37.35f, 54f, 24f)
                close()
                moveTo(54f, 24f)
                curveTo(64f, 37.35f, 74f, 44f, 84f, 44f)
                curveTo(89.3f, 44f, 94.39f, 41.89f, 98.14f, 38.14f)
                curveTo(101.89f, 34.39f, 104f, 29.3f, 104f, 24f)
                curveTo(104f, 18.7f, 101.89f, 13.61f, 98.14f, 9.86f)
                curveTo(94.39f, 6.11f, 89.3f, 4f, 84f, 4f)
                curveTo(74f, 4f, 64f, 10.65f, 54f, 24f)
                close()
            }
        }.build()

        return _Infinity!!
    }

@Suppress("ObjectPropertyName")
private var _Infinity: ImageVector? = null
