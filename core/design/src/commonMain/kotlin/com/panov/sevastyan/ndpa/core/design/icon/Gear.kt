package com.panov.sevastyan.ndpa.core.design.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val NdpaIcons.Gear: ImageVector
    get() {
        if (_Gear != null) {
            return _Gear!!
        }
        _Gear = ImageVector.Builder(
            name = "Gear",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 1.5f,
            ) {
                moveTo(12f, 5.8f)
                curveTo(15.424f, 5.8f, 18.2f, 8.576f, 18.2f, 12f)
                moveTo(12f, 5.8f)
                curveTo(8.576f, 5.8f, 5.8f, 8.576f, 5.8f, 12f)
                moveTo(12f, 5.8f)
                verticalLineTo(2.7f)
                moveTo(18.2f, 12f)
                curveTo(18.2f, 15.424f, 15.424f, 18.2f, 12f, 18.2f)
                moveTo(18.2f, 12f)
                horizontalLineTo(21.3f)
                moveTo(12f, 18.2f)
                curveTo(8.576f, 18.2f, 5.8f, 15.424f, 5.8f, 12f)
                moveTo(12f, 18.2f)
                verticalLineTo(21.3f)
                moveTo(5.8f, 12f)
                horizontalLineTo(2.7f)
                moveTo(16.384f, 16.384f)
                lineTo(18.576f, 18.576f)
                moveTo(7.616f, 16.384f)
                lineTo(5.424f, 18.576f)
                moveTo(7.616f, 7.616f)
                lineTo(5.424f, 5.424f)
                moveTo(16.384f, 7.616f)
                lineTo(18.576f, 5.424f)
                moveTo(12f, 9.2f)
                curveTo(13.546f, 9.2f, 14.8f, 10.454f, 14.8f, 12f)
                curveTo(14.8f, 13.546f, 13.546f, 14.8f, 12f, 14.8f)
                curveTo(10.454f, 14.8f, 9.2f, 13.546f, 9.2f, 12f)
                curveTo(9.2f, 10.454f, 10.454f, 9.2f, 12f, 9.2f)
                close()
            }
        }.build()

        return _Gear!!
    }

@Suppress("ObjectPropertyName")
private var _Gear: ImageVector? = null
