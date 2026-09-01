package com.panov.sevastyan.ndpa.core.design.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val NdpaIcons.Trash: ImageVector
    get() {
        if (_Trash != null) {
            return _Trash!!
        }
        _Trash = ImageVector.Builder(
            name = "Trash",
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
                moveTo(5f, 6.6f)
                horizontalLineTo(19f)
                moveTo(9.7f, 6.6f)
                verticalLineTo(5f)
                horizontalLineTo(14.3f)
                verticalLineTo(6.6f)
                moveTo(7.2f, 6.6f)
                lineTo(8f, 16.8f)
                horizontalLineTo(16f)
                lineTo(16.8f, 6.6f)
                moveTo(10.7f, 9.5f)
                verticalLineTo(14f)
                moveTo(13.3f, 9.5f)
                verticalLineTo(14f)
            }
        }.build()

        return _Trash!!
    }

@Suppress("ObjectPropertyName")
private var _Trash: ImageVector? = null
