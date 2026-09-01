package com.panov.sevastyan.ndpa.core.ui.button.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.panov.sevastyan.ndpa.core.design.theme.NdpaTheme
import com.panov.sevastyan.ndpa.core.ui.interaction.PressedIndication
import com.panov.sevastyan.ndpa.core.ui.preview.ThemedPreviews

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val sizes = PrimaryButtonDefaults.sizes
    val shapes = PrimaryButtonDefaults.shapes
    val colors = PrimaryButtonDefaults.colors

    Box(
        modifier = modifier
            .heightIn(min = sizes.minHeight)
            .background(
                color = colors.container(enabled),
                shape = shapes.container,
            )
            .clickable(
                interactionSource = null,
                indication = PressedIndication,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(
                    horizontal = NdpaTheme.spacing.x2l,
                    vertical = NdpaTheme.spacing.sm,
                ),
            style = PrimaryButtonDefaults.textStyle,
            color = colors.content(enabled),
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

internal object PrimaryButtonDefaults {

    val sizes: PrimaryButtonSizes = PrimaryButtonSizes(
        minHeight = 56.dp,
    )

    val shapes: PrimaryButtonShapes
        @Composable
        @ReadOnlyComposable
        get() = PrimaryButtonShapes(
            container = NdpaTheme.shapes.lg,
        )

    val colors: PrimaryButtonColors
        @Composable
        @ReadOnlyComposable
        get() = with(NdpaTheme.colors) {
            PrimaryButtonColors(
                container = accent,
                content = onAccent,
                disabledContainer = bgSunken,
                disabledContent = textFaint,
            )
        }

    val textStyle: TextStyle
        @Composable
        @ReadOnlyComposable
        get() = NdpaTheme.typography.button
}

@Immutable
internal data class PrimaryButtonSizes(
    val minHeight: Dp,
)

@Immutable
internal data class PrimaryButtonShapes(
    val container: Shape,
)

@Immutable
internal data class PrimaryButtonColors(
    val container: Color,
    val content: Color,
    val disabledContainer: Color,
    val disabledContent: Color,
) {
    fun container(enabled: Boolean): Color = if (enabled) container else disabledContainer

    fun content(enabled: Boolean): Color = if (enabled) content else disabledContent
}

@Preview
@Composable
private fun PrimaryButtonPreview() {
    ThemedPreviews {
        Column(
            modifier = Modifier.padding(all = NdpaTheme.spacing.x2l),
            verticalArrangement = Arrangement.spacedBy(NdpaTheme.spacing.lg),
        ) {
            PrimaryButton(
                text = "Start focus",
                onClick = { /* no-op */ },
                modifier = Modifier.fillMaxWidth(),
            )
            PrimaryButton(
                text = "Start focus",
                onClick = { /* no-op */ },
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
            )
        }
    }
}
