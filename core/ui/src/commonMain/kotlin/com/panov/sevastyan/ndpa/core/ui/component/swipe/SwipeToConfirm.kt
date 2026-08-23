package com.panov.sevastyan.ndpa.core.ui.component.swipe

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.onLongClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.panov.sevastyan.ndpa.core.design.icon.ChevronRight
import com.panov.sevastyan.ndpa.core.design.icon.NdpaIcons
import com.panov.sevastyan.ndpa.core.design.theme.NdpaTheme
import com.panov.sevastyan.ndpa.core.ui.preview.ThemedPreviews
import kotlin.math.roundToInt
import kotlinx.coroutines.launch

private const val ConfirmThreshold = 0.9f

private const val CompleteDurationMillis = 150

private val ReturnSpring = spring<Float>(
    dampingRatio = Spring.DampingRatioNoBouncy,
    stiffness = Spring.StiffnessMedium,
)

@Composable
fun SwipeToConfirm(
    hint: String,
    tintStart: Color,
    tintEnd: Color,
    onSwiped: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val sizes = SwipeToConfirmDefaults.sizes
    val shapes = SwipeToConfirmDefaults.shapes
    val colors = SwipeToConfirmDefaults.colors

    val density = LocalDensity.current
    val thumbWidthPx = with(density) { sizes.thumbWidth.roundToPx() }
    val insetPx = with(density) { NdpaTheme.spacing.xs.roundToPx() }

    val scope = rememberCoroutineScope()

    val currentOnSwiped by rememberUpdatedState(onSwiped)

    val thumbOffset = remember { Animatable(0f) }
    var travel by remember { mutableFloatStateOf(0f) }

    val progress = {
        if (travel > 0f) (thumbOffset.value / travel).coerceIn(0f, 1f) else 0f
    }
    val tint = {
        lerp(tintStart, tintEnd, progress())
    }
    val hintAlpha = {
        (1f - 2f * progress()).coerceIn(0f, 1f)
    }

    Box(
        modifier = modifier
            .heightIn(min = sizes.minHeight)
            .semantics(mergeDescendants = true) {
                onLongClick(label = hint) {
                    currentOnSwiped()
                    true
                }
            }
            .clip(shape = shapes.container)
            .drawWithCache {
                val outline = shapes.container.createOutline(size, layoutDirection, this)

                onDrawBehind {
                    drawOutline(
                        outline = outline,
                        color = tint(),
                    )
                }
            }
            .onSizeChanged { containerSize ->
                travel =
                    (containerSize.width - thumbWidthPx - 2 * insetPx).coerceAtLeast(0).toFloat()
            },
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(all = sizes.borderWidth)
                .background(
                    color = colors.track,
                    shape = shapes.containerCore,
                ),
        )
        Text(
            text = hint,
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .fillMaxWidth()
                .padding(
                    start = NdpaTheme.spacing.xs + sizes.thumbWidth + NdpaTheme.spacing.sm,
                    end = NdpaTheme.spacing.sm,
                )
                .graphicsLayer { alpha = hintAlpha() },
            style = SwipeToConfirmDefaults.textStyle,
            color = colors.content,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Trail(
            thumbOffset = { thumbOffset.value },
            tint = tint,
            modifier = Modifier.matchParentSize(),
        )
        Thumb(
            tint = tint,
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .padding(all = NdpaTheme.spacing.xs)
                .offset {
                    IntOffset(
                        x = thumbOffset.value.roundToInt(),
                        y = 0,
                    )
                }
                .draggable(
                    state = rememberDraggableState { delta ->
                        scope.launch {
                            thumbOffset.snapTo((thumbOffset.value + delta).coerceIn(0f, travel))
                        }
                    },
                    orientation = Orientation.Horizontal,
                    onDragStopped = {
                        scope.launch {
                            if (progress() >= ConfirmThreshold) {
                                thumbOffset.animateTo(travel, tween(CompleteDurationMillis))
                                currentOnSwiped()
                            } else {
                                thumbOffset.animateTo(0f, ReturnSpring)
                            }
                        }
                    },
                ),
        )
    }
}

@Composable
private fun Trail(
    thumbOffset: () -> Float,
    tint: () -> Color,
    modifier: Modifier = Modifier,
) {
    val sizes = SwipeToConfirmDefaults.sizes
    val inset = NdpaTheme.spacing.xs

    Box(
        modifier = modifier.drawBehind {
            val offset = thumbOffset()
            if (offset > 0f) {
                val insetPx = inset.toPx()
                drawRoundRect(
                    color = tint(),
                    topLeft = Offset(insetPx, insetPx),
                    size = Size(offset + sizes.thumbWidth.toPx(), sizes.thumbHeight.toPx()),
                    cornerRadius = CornerRadius(sizes.thumbRadius.toPx()),
                )
            }
        },
    )
}

@Composable
private fun Thumb(
    tint: () -> Color,
    modifier: Modifier = Modifier,
) {
    val sizes = SwipeToConfirmDefaults.sizes
    val shapes = SwipeToConfirmDefaults.shapes
    val colors = SwipeToConfirmDefaults.colors

    Box(
        modifier = modifier
            .size(
                width = sizes.thumbWidth,
                height = sizes.thumbHeight,
            )
            .clip(shape = shapes.thumb)
            .drawBehind {
                drawRoundRect(
                    color = tint(),
                    cornerRadius = CornerRadius(sizes.thumbRadius.toPx()),
                )
            },
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(all = sizes.thumbBorderWidth)
                .background(
                    color = colors.thumb,
                    shape = shapes.thumbCore,
                ),
        )
        Icon(
            imageVector = NdpaIcons.ChevronRight,
            contentDescription = null,
            modifier = Modifier.graphicsLayer { colorFilter = ColorFilter.tint(tint()) },
            tint = Color.Unspecified,
        )
    }
}

internal object SwipeToConfirmDefaults {

    val sizes: SwipeToConfirmSizes = SwipeToConfirmSizes(
        minHeight = 56.dp,
        borderWidth = 1.5.dp,
        containerCoreRadius = 12.5.dp,
        thumbWidth = 96.dp,
        thumbHeight = 48.dp,
        thumbRadius = 10.dp,
        thumbBorderWidth = 1.5.dp,
    )

    val shapes: SwipeToConfirmShapes
        @Composable
        @ReadOnlyComposable
        get() = SwipeToConfirmShapes(
            container = NdpaTheme.shapes.lg,
            containerCore = RoundedCornerShape(sizes.containerCoreRadius),
            thumb = RoundedCornerShape(sizes.thumbRadius),
            thumbCore = RoundedCornerShape(sizes.thumbRadius - sizes.thumbBorderWidth),
        )

    val colors: SwipeToConfirmColors
        @Composable
        @ReadOnlyComposable
        get() = with(NdpaTheme.colors) {
            SwipeToConfirmColors(
                track = bgSunken,
                content = textMuted,
                thumb = surface,
            )
        }

    val textStyle: TextStyle
        @Composable
        @ReadOnlyComposable
        get() = NdpaTheme.typography.body
}

@Immutable
internal data class SwipeToConfirmSizes(
    val minHeight: Dp,
    val borderWidth: Dp,
    val containerCoreRadius: Dp,
    val thumbWidth: Dp,
    val thumbHeight: Dp,
    val thumbRadius: Dp,
    val thumbBorderWidth: Dp,
)

@Immutable
internal data class SwipeToConfirmShapes(
    val container: Shape,
    val containerCore: Shape,
    val thumb: Shape,
    val thumbCore: Shape,
)

@Immutable
internal data class SwipeToConfirmColors(
    val track: Color,
    val content: Color,
    val thumb: Color,
)

@Preview
@Composable
private fun SwipeToConfirmPreview() {
    ThemedPreviews {
        Column(
            modifier = Modifier.padding(all = NdpaTheme.spacing.x2l),
            verticalArrangement = Arrangement.spacedBy(NdpaTheme.spacing.lg),
        ) {
            SwipeToConfirm(
                hint = "Slide to end",
                tintStart = NdpaTheme.colors.accentMuted,
                tintEnd = NdpaTheme.colors.accent,
                onSwiped = { /* no-op */ },
                modifier = Modifier.fillMaxWidth(),
            )
            SwipeToConfirm(
                hint = "Slide all the way to the end to finish the focus session",
                tintStart = NdpaTheme.colors.accentMuted,
                tintEnd = NdpaTheme.colors.accent,
                onSwiped = { /* no-op */ },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
