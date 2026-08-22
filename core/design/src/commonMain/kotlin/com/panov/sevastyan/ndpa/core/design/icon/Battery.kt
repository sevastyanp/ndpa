package com.panov.sevastyan.ndpa.core.design.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val NdpaIcons.Battery: ImageVector
    get() {
        if (_Battery != null) {
            return _Battery!!
        }
        _Battery = ImageVector.Builder(
            name = "Battery",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 1.5f,
            ) {
                moveTo(20.4f, 10.4f)
                verticalLineTo(13.6f)
                moveTo(3f, 7f)
                horizontalLineTo(18f)
                verticalLineTo(17f)
                horizontalLineTo(3f)
                verticalLineTo(7f)
                close()
                moveTo(5.5f, 9.5f)
                horizontalLineTo(10.5f)
                verticalLineTo(14.5f)
                horizontalLineTo(5.5f)
                verticalLineTo(9.5f)
                close()
            }
        }.build()

        return _Battery!!
    }

@Suppress("ObjectPropertyName")
private var _Battery: ImageVector? = null
