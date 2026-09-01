package com.panov.sevastyan.ndpa.core.design.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val NdpaIcons.Calendar: ImageVector
    get() {
        if (_Calendar != null) {
            return _Calendar!!
        }
        _Calendar = ImageVector.Builder(
            name = "Calendar",
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
                moveTo(5f, 11.5f)
                horizontalLineTo(19f)
                moveTo(9f, 5f)
                verticalLineTo(9f)
                moveTo(15f, 5f)
                verticalLineTo(9f)
                moveTo(7f, 7f)
                horizontalLineTo(17f)
                curveTo(18.1f, 7f, 19f, 7.9f, 19f, 9f)
                verticalLineTo(17f)
                curveTo(19f, 18.1f, 18.1f, 19f, 17f, 19f)
                horizontalLineTo(7f)
                curveTo(5.9f, 19f, 5f, 18.1f, 5f, 17f)
                verticalLineTo(9f)
                curveTo(5f, 7.9f, 5.9f, 7f, 7f, 7f)
                close()
            }
        }.build()

        return _Calendar!!
    }

@Suppress("ObjectPropertyName")
private var _Calendar: ImageVector? = null
