package com.panov.sevastyan.ndpa.core.ui.component.chip

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.panov.sevastyan.ndpa.core.design.theme.NdpaTheme
import com.panov.sevastyan.ndpa.core.ui.component.interaction.PressedIndication
import com.panov.sevastyan.ndpa.core.ui.preview.ThemedPreviews

@Composable
fun Chip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val sizes = ChipDefaults.sizes
    val shapes = ChipDefaults.shapes
    val colors = ChipDefaults.colors

    Box(
        modifier = modifier
            .heightIn(min = sizes.minHeight)
            .clickable(
                interactionSource = null,
                indication = PressedIndication,
                role = Role.Button,
                onClick = onClick,
            )
            .border(
                width = sizes.borderWidth,
                color = colors.border,
                shape = shapes.container,
            )
            .padding(
                horizontal = NdpaTheme.spacing.xl,
                vertical = NdpaTheme.spacing.sm,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = ChipDefaults.textStyle,
            color = colors.content,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

internal object ChipDefaults {

    val sizes: ChipSizes = ChipSizes(
        minHeight = 48.dp,
        borderWidth = 1.dp,
    )

    val shapes: ChipShapes
        @Composable
        @ReadOnlyComposable
        get() = ChipShapes(
            container = NdpaTheme.shapes.md,
        )

    val colors: ChipColors
        @Composable
        @ReadOnlyComposable
        get() = with(NdpaTheme.colors) {
            ChipColors(
                border = line,
                content = text,
            )
        }

    val textStyle: TextStyle
        @Composable
        @ReadOnlyComposable
        get() = NdpaTheme.typography.button
}

@Immutable
internal data class ChipSizes(
    val minHeight: Dp,
    val borderWidth: Dp,
)

@Immutable
internal data class ChipShapes(
    val container: Shape,
)

@Immutable
internal data class ChipColors(
    val border: Color,
    val content: Color,
)

@Preview
@Composable
private fun ChipPreview() {
    ThemedPreviews {
        Column(
            modifier = Modifier.padding(all = NdpaTheme.spacing.x2l),
            verticalArrangement = Arrangement.spacedBy(NdpaTheme.spacing.lg),
        ) {
            Chip(text = "25 min", onClick = { /* no-op */ })
            Chip(text = "Last time · 1 h 30 min", onClick = { /* no-op */ })
        }
    }
}
