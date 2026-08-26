package com.panov.sevastyan.ndpa.core.design.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val NdpaIcons.Play: ImageVector
    get() {
        if (_Play != null) {
            return _Play!!
        }
        _Play = ImageVector.Builder(
            name = "Play",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(3.066f, 5.7f)
                curveTo(3.066f, 3.15f, 5.812f, 1.544f, 8.034f, 2.794f)
                lineTo(19.235f, 9.095f)
                curveTo(21.5f, 10.369f, 21.5f, 13.631f, 19.235f, 14.905f)
                lineTo(8.034f, 21.206f)
                curveTo(5.812f, 22.456f, 3.066f, 20.85f, 3.066f, 18.301f)
                lineTo(3.066f, 5.7f)
                close()
            }
        }.build()

        return _Play!!
    }

@Suppress("ObjectPropertyName")
private var _Play: ImageVector? = null
