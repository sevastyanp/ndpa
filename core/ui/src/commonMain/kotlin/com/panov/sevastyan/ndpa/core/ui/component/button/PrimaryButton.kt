package com.panov.sevastyan.ndpa.core.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.panov.sevastyan.ndpa.core.ui.component.interaction.PressedIndication
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
            .clickable(
                interactionSource = null,
                indication = PressedIndication,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick,
            )
            .border(
                width = sizes.borderWidth,
                color = colors.border(enabled),
                shape = shapes.container,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(all = 3.dp)
                .background(
                    color = colors.core(enabled),
                    shape = shapes.core,
                ),
        )
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
        borderWidth = 1.5.dp,
        coreRadius = 11.dp,
    )

    val shapes: PrimaryButtonShapes
        @Composable
        @ReadOnlyComposable
        get() = PrimaryButtonShapes(
            container = NdpaTheme.shapes.lg,
            core = RoundedCornerShape(sizes.coreRadius),
        )

    val colors: PrimaryButtonColors
        @Composable
        @ReadOnlyComposable
        get() = with(NdpaTheme.colors) {
            PrimaryButtonColors(
                border = accent,
                core = accent,
                content = onAccent,
                disabledBorder = line,
                disabledCore = bgSunken,
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
    val borderWidth: Dp,
    val coreRadius: Dp,
)

@Immutable
internal data class PrimaryButtonShapes(
    val container: Shape,
    val core: Shape,
)

@Immutable
internal data class PrimaryButtonColors(
    val border: Color,
    val core: Color,
    val content: Color,
    val disabledBorder: Color,
    val disabledCore: Color,
    val disabledContent: Color,
) {
    fun border(enabled: Boolean): Color = if (enabled) border else disabledBorder

    fun core(enabled: Boolean): Color = if (enabled) core else disabledCore

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
