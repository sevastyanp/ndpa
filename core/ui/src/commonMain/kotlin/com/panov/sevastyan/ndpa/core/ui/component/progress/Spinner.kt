package com.panov.sevastyan.ndpa.core.ui.component.progress

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.panov.sevastyan.ndpa.core.design.theme.NdpaTheme
import com.panov.sevastyan.ndpa.core.ui.preview.ThemedPreviews

private const val SweepDegrees = 270f
private const val RotationMillis = 1000

@Composable
fun Spinner(
    modifier: Modifier = Modifier,
    color: Color = SpinnerDefaults.colors.indicator,
) {
    val sizes = SpinnerDefaults.sizes
    val transition = rememberInfiniteTransition()
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(RotationMillis, easing = LinearEasing)),
    )

    Canvas(modifier = modifier.size(size = NdpaTheme.sizes.icon)) {
        val inset = sizes.strokeWidth.toPx() / 2
        rotate(rotation) {
            drawArc(
                color = color,
                startAngle = 0f,
                sweepAngle = SweepDegrees,
                useCenter = false,
                topLeft = Offset(inset, inset),
                size = Size(size.width - 2 * inset, size.height - 2 * inset),
                style = Stroke(width = sizes.strokeWidth.toPx(), cap = StrokeCap.Round),
            )
        }
    }
}

internal object SpinnerDefaults {

    val sizes: SpinnerSizes = SpinnerSizes(
        strokeWidth = 2.4.dp,
    )

    val colors: SpinnerColors
        @Composable
        @ReadOnlyComposable
        get() = SpinnerColors(
            indicator = NdpaTheme.colors.accent,
        )
}

@Immutable
internal data class SpinnerSizes(
    val strokeWidth: Dp,
)

@Immutable
internal data class SpinnerColors(
    val indicator: Color,
)

@Preview
@Composable
private fun SpinnerPreview() {
    ThemedPreviews {
        Row(
            modifier = Modifier.padding(all = NdpaTheme.spacing.x2l),
            horizontalArrangement = Arrangement.spacedBy(NdpaTheme.spacing.lg),
        ) {
            Spinner()
            Box(
                modifier = Modifier
                    .background(
                        color = NdpaTheme.colors.accent,
                        shape = NdpaTheme.shapes.md,
                    )
                    .padding(all = NdpaTheme.spacing.sm),
            ) {
                Spinner(color = NdpaTheme.colors.onAccent)
            }
        }
    }
}
