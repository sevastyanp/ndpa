@file:Suppress("TooManyFunctions")

package com.panov.sevastyan.ndpa.core.ui.timer.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.panov.sevastyan.ndpa.core.design.icon.Infinity
import com.panov.sevastyan.ndpa.core.design.icon.NdpaIcons
import com.panov.sevastyan.ndpa.core.design.theme.NdpaTheme
import com.panov.sevastyan.ndpa.core.ui.interaction.PressedIndication
import com.panov.sevastyan.ndpa.core.ui.preview.ThemedPreviews
import com.panov.sevastyan.ndpa.core.ui.timer.format
import com.panov.sevastyan.ndpa.core.ui.timer.toTimerDigits
import org.jetbrains.compose.resources.stringResource
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

private const val ArcStartAngle: Float = -90f
private const val FullSweep: Float = 360f

@Composable
fun TimerRing(
    state: TimerRingState,
    onModeToggleClick: () -> Unit,
    onSectionClick: (TimerRingSection) -> Unit,
    modifier: Modifier = Modifier,
) {
    val sizes = TimerRingDefaults.sizes

    Box(modifier = modifier.requiredSize(sizes.diameter)) {
        TimerDisc(
            modifier = Modifier
                .align(Alignment.Center)
                .size(sizes.discDiameter),
        )

        when (state) {
            is TimerRingState.Running -> RunningContent(state = state)

            is TimerRingState.Idle -> IdleContent(
                state = state,
                onSectionClick = onSectionClick,
                onModeToggleClick = onModeToggleClick,
            )
        }
    }
}

@Composable
private fun BoxScope.RunningContent(state: TimerRingState.Running) {
    val currentState by rememberUpdatedState(state)

    TimerArc(
        progress = { currentState.progress },
        showsPosition = state is TimerRingState.Running.Timed,
        modifier = Modifier.matchParentSize(),
    )

    TimerValue(
        value = state.duration.format(),
        caption = stringResource(state.caption),
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter)
            .padding(top = 96.dp),
    )
}

@Composable
private fun BoxScope.IdleContent(
    state: TimerRingState.Idle,
    onSectionClick: (TimerRingSection) -> Unit,
    onModeToggleClick: () -> Unit,
) {
    when (state) {
        is TimerRingState.Idle.Untimed -> IdleGlyph(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 76.dp),
        )

        is TimerRingState.Idle.Timed -> DurationDigits(
            duration = state.duration,
            selected = state.selected,
            onSectionClick = onSectionClick,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 102.dp),
        )
    }

    TimerSegment(
        icon = state.modeToggleIcon,
        contentDescription = stringResource(state.modeToggleDescription),
        onClick = onModeToggleClick,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 2.dp),
    )
}

@Composable
private fun TimerDisc(modifier: Modifier = Modifier) {
    val shadows = TimerRingDefaults.shadows

    val shapes = TimerRingDefaults.shapes
    val colors = TimerRingDefaults.colors

    Box(
        modifier = modifier
            .dropShadow(shape = shapes.disc, shadow = shadows.ambient)
            .dropShadow(shape = shapes.disc, shadow = shadows.lift)
            .dropShadow(shape = shapes.disc, shadow = shadows.edge)
            .clip(shape = shapes.disc)
            .drawWithCache {
                val brush = Brush.radialGradient(
                    colors = listOf(colors.discCenter, colors.discEdge),
                    center = size.center,
                    radius = size.minDimension / 2f,
                )

                onDrawBehind { drawCircle(brush = brush) }
            },
    )
}

@Composable
private fun TimerArc(
    progress: () -> Float,
    showsPosition: Boolean,
    modifier: Modifier = Modifier,
) {
    val sizes = TimerRingDefaults.sizes
    val colors = TimerRingDefaults.colors

    Spacer(
        modifier = modifier.drawWithCache {
            val radius = sizes.arcRadius.toPx()
            val stroke = Stroke(width = sizes.arcWidth.toPx(), cap = StrokeCap.Round)
            val arcTopLeft = Offset(x = size.center.x - radius, y = size.center.y - radius)
            val arcSize = Size(width = radius * 2, height = radius * 2)
            val positionRadius = sizes.positionDiameter.toPx() / 2f
            val coreRadius = sizes.positionCoreDiameter.toPx() / 2f

            onDrawBehind {
                val sweep = FullSweep * progress().coerceIn(0f, 1f)

                when {
                    sweep >= FullSweep -> drawCircle(color = colors.arc, radius = radius, style = stroke)

                    sweep > 0f -> drawArc(
                        color = colors.arc,
                        startAngle = ArcStartAngle,
                        sweepAngle = sweep,
                        useCenter = false,
                        topLeft = arcTopLeft,
                        size = arcSize,
                        style = stroke,
                    )
                }

                if (showsPosition) {
                    val angle = (ArcStartAngle + sweep) * (PI / 180f).toFloat()
                    val dot = size.center + Offset(x = cos(angle), y = sin(angle)) * radius

                    drawCircle(color = colors.arc, radius = positionRadius, center = dot)
                    drawCircle(color = colors.positionCore, radius = coreRadius, center = dot)
                }
            }
        },
    )
}

@Composable
private fun TimerValue(
    value: String,
    caption: String,
    modifier: Modifier = Modifier,
) {
    val textStyles = TimerRingDefaults.textStyles
    val colors = TimerRingDefaults.colors

    Column(
        modifier = modifier.semantics(mergeDescendants = true) {},
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(NdpaTheme.spacing.xs),
    ) {
        Text(
            text = value,
            style = textStyles.value,
            color = colors.value,
            softWrap = false,
            maxLines = 1,
        )
        Text(
            text = caption,
            style = textStyles.caption,
            color = colors.caption,
        )
    }
}

@Composable
private fun IdleGlyph(modifier: Modifier = Modifier) {
    val sizes = TimerRingDefaults.sizes
    val colors = TimerRingDefaults.colors

    Icon(
        imageVector = NdpaIcons.Infinity,
        contentDescription = null,
        modifier = modifier.size(sizes.glyph),
        tint = colors.glyph,
    )
}

@Composable
private fun DurationDigits(
    duration: Duration,
    selected: TimerRingSection?,
    onSectionClick: (TimerRingSection) -> Unit,
    modifier: Modifier = Modifier,
) {
    val (hours, minutes, seconds) = duration.toTimerDigits()

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DurationSection(
            value = hours,
            selected = selected == TimerRingSection.Hours,
            onClick = { onSectionClick(TimerRingSection.Hours) },
        )
        DurationColon()
        DurationSection(
            value = minutes,
            selected = selected == TimerRingSection.Minutes,
            onClick = { onSectionClick(TimerRingSection.Minutes) },
        )
        DurationColon()
        DurationSection(
            value = seconds,
            selected = selected == TimerRingSection.Seconds,
            onClick = { onSectionClick(TimerRingSection.Seconds) },
        )
    }
}

@Composable
private fun DurationSection(
    value: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shapes = TimerRingDefaults.shapes
    val colors = TimerRingDefaults.colors
    val textStyles = TimerRingDefaults.textStyles

    Text(
        text = value,
        style = textStyles.digit,
        color = colors.digit(selected),
        softWrap = false,
        maxLines = 1,
        modifier = modifier
            .clip(shape = shapes.section)
            .clickable(
                interactionSource = null,
                indication = PressedIndication,
                role = Role.Button,
                onClick = onClick,
            )
            .background(color = colors.sectionBackground(selected))
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .semantics { this.selected = selected },
    )
}

@Composable
private fun DurationColon(modifier: Modifier = Modifier) {
    val colors = TimerRingDefaults.colors
    val textStyles = TimerRingDefaults.textStyles

    Text(
        text = ":",
        style = textStyles.digit,
        color = colors.digitInactive,
        softWrap = false,
        maxLines = 1,
        modifier = modifier,
    )
}

@Composable
private fun TimerSegment(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val sizes = TimerRingDefaults.sizes

    val shapes = TimerRingDefaults.shapes
    val colors = TimerRingDefaults.colors

    Box(
        modifier = modifier
            .size(width = sizes.discDiameter, height = sizes.segmentHeight)
            .clip(shape = shapes.segment)
            .clickable(
                interactionSource = null,
                indication = PressedIndication,
                role = Role.Button,
                onClick = onClick,
            )
            .background(color = colors.segment),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(sizes.segmentIcon),
            tint = colors.segmentIcon,
        )
    }
}

internal object TimerRingDefaults {

    val sizes: TimerRingSizes = TimerRingSizes(
        diameter = 284.dp,
        discDiameter = 280.dp,
        arcRadius = 139.dp,
        arcWidth = 4.dp,
        positionDiameter = 13.dp,
        positionCoreDiameter = 5.dp,
        segmentHeight = 64.dp,
        segmentIcon = 28.dp,
        glyph = 108.dp,
    )

    val shadows: TimerRingShadows = TimerRingShadows(
        ambient = Shadow(
            radius = 20.dp,
            color = Color.Black,
            spread = 1.dp,
            offset = DpOffset.Zero,
            alpha = 0.09f,
        ),
        lift = Shadow(
            radius = 20.dp,
            color = Color.Black,
            offset = DpOffset(x = 0.dp, y = (-5).dp),
            alpha = 0.07f,
        ),
        edge = Shadow(
            radius = 6.dp,
            color = Color.Black,
            offset = DpOffset.Zero,
            alpha = 0.08f,
        ),
    )

    val shapes: TimerRingShapes
        @Composable
        @ReadOnlyComposable
        get() = TimerRingShapes(
            disc = CircleShape,
            segment = DiscSegmentShape(discDiameter = sizes.discDiameter),
            section = NdpaTheme.shapes.lg,
        )

    val colors: TimerRingColors
        @Composable
        @ReadOnlyComposable
        get() = with(NdpaTheme.colors) {
            TimerRingColors(
                discCenter = surface,
                discEdge = accentWash,
                arc = accent,
                positionCore = surface,
                segment = surface,
                segmentIcon = dialMuted,
                value = dial,
                caption = dialMuted,
                glyph = dial,
                digit = dial,
                digitInactive = dialFaint,
                sectionSelected = accentSoft,
            )
        }

    val textStyles: TimerRingTextStyles
        @Composable
        @ReadOnlyComposable
        get() {
            val fontScale = LocalDensity.current.fontScale
            val numeral = NdpaTheme.typography.numeral
            val numeralSmall = NdpaTheme.typography.numeralSmall

            return TimerRingTextStyles(
                value = numeral.copy(
                    fontSize = numeral.fontSize / fontScale,
                    lineHeight = numeral.lineHeight / fontScale,
                ),
                caption = NdpaTheme.typography.label,
                digit = numeralSmall.copy(
                    fontSize = numeralSmall.fontSize / fontScale,
                    lineHeight = numeralSmall.lineHeight / fontScale,
                ),
            )
        }
}

@Immutable
internal data class TimerRingSizes(
    val diameter: Dp,
    val discDiameter: Dp,
    val arcRadius: Dp,
    val arcWidth: Dp,
    val positionDiameter: Dp,
    val positionCoreDiameter: Dp,
    val segmentHeight: Dp,
    val segmentIcon: Dp,
    val glyph: Dp,
)

@Immutable
internal data class TimerRingShadows(
    val ambient: Shadow,
    val lift: Shadow,
    val edge: Shadow,
)

@Immutable
internal data class TimerRingShapes(
    val disc: Shape,
    val segment: Shape,
    val section: Shape,
)

@Immutable
internal data class TimerRingColors(
    val discCenter: Color,
    val discEdge: Color,
    val arc: Color,
    val positionCore: Color,
    val segment: Color,
    val segmentIcon: Color,
    val value: Color,
    val caption: Color,
    val glyph: Color,
    val digit: Color,
    val digitInactive: Color,
    val sectionSelected: Color,
) {
    fun digit(selected: Boolean): Color = if (selected) digit else digitInactive

    fun sectionBackground(selected: Boolean): Color = if (selected) sectionSelected else Color.Transparent
}

@Immutable
internal data class TimerRingTextStyles(
    val value: TextStyle,
    val caption: TextStyle,
    val digit: TextStyle,
)

@Preview(widthDp = 1280, heightDp = 800)
@Composable
private fun TimerRingPreview() {
    ThemedPreviews {
        Row(
            modifier = Modifier.padding(all = NdpaTheme.spacing.x2l),
            horizontalArrangement = Arrangement.spacedBy(NdpaTheme.spacing.lg),
        ) {
            TimerRing(
                state = TimerRingState.Running.Timed(remaining = 18.minutes, total = 50.minutes),
                onModeToggleClick = { /* no-op */ },
                onSectionClick = { /* no-op */ },
            )
            TimerRing(
                state = TimerRingState.Running.Untimed(elapsed = 42.minutes + 17.seconds),
                onModeToggleClick = { /* no-op */ },
                onSectionClick = { /* no-op */ },
            )
            TimerRing(
                state = TimerRingState.Idle.Untimed,
                onModeToggleClick = { /* no-op */ },
                onSectionClick = { /* no-op */ },
            )
            TimerRing(
                state = TimerRingState.Idle.Timed(duration = 50.minutes, selected = TimerRingSection.Seconds),
                onModeToggleClick = { /* no-op */ },
                onSectionClick = { /* no-op */ },
            )
        }
    }
}
