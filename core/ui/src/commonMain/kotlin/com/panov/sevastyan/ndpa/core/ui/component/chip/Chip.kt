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
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
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
    Box(
        modifier = modifier
            .heightIn(min = ChipDefaults.MinHeight)
            .clickable(
                interactionSource = null,
                indication = PressedIndication,
                role = Role.Button,
                onClick = onClick,
            )
            .border(
                width = ChipDefaults.BorderWidth,
                color = NdpaTheme.colors.line,
                shape = ChipDefaults.shape,
            )
            .padding(
                horizontal = NdpaTheme.spacing.xl,
                vertical = NdpaTheme.spacing.sm,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = NdpaTheme.typography.button,
            color = NdpaTheme.colors.text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

internal object ChipDefaults {

    val MinHeight: Dp = 48.dp

    val BorderWidth: Dp = 1.dp

    val shape: Shape
        @Composable
        @ReadOnlyComposable
        get() = NdpaTheme.shapes.md
}

@Preview
@Composable
private fun ChipPreview() {
    ThemedPreviews {
        Column(
            modifier = Modifier.padding(NdpaTheme.spacing.x2l),
            verticalArrangement = Arrangement.spacedBy(NdpaTheme.spacing.lg),
        ) {
            Chip(text = "25 min", onClick = {})
            Chip(text = "Last time · 1 h 30 min", onClick = {})
        }
    }
}
