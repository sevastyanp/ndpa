package com.panov.sevastyan.ndpa.core.design.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val NdpaIcons.AppsGrid: ImageVector
    get() {
        if (_AppsGrid != null) {
            return _AppsGrid!!
        }
        _AppsGrid = ImageVector.Builder(
            name = "AppsGrid",
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
                moveTo(10f, 4f)
                horizontalLineTo(4f)
                verticalLineTo(10f)
                horizontalLineTo(10f)
                verticalLineTo(4f)
                close()
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(20f, 4f)
                horizontalLineTo(14f)
                verticalLineTo(10f)
                horizontalLineTo(20f)
                verticalLineTo(4f)
                close()
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(10f, 14f)
                horizontalLineTo(4f)
                verticalLineTo(20f)
                horizontalLineTo(10f)
                verticalLineTo(14f)
                close()
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(20f, 14f)
                horizontalLineTo(14f)
                verticalLineTo(20f)
                horizontalLineTo(20f)
                verticalLineTo(14f)
                close()
            }
        }.build()

        return _AppsGrid!!
    }

@Suppress("ObjectPropertyName")
private var _AppsGrid: ImageVector? = null
