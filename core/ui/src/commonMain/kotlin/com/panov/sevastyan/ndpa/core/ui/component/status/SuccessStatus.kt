package com.panov.sevastyan.ndpa.core.ui.component.status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.panov.sevastyan.ndpa.core.design.icon.Check
import com.panov.sevastyan.ndpa.core.design.icon.NdpaIcons
import com.panov.sevastyan.ndpa.core.design.theme.NdpaTheme
import com.panov.sevastyan.ndpa.core.ui.preview.ThemedPreviews

@Composable
fun SuccessStatus(
    text: String,
    modifier: Modifier = Modifier,
) {
    val sizes = SuccessStatusDefaults.sizes
    val shapes = SuccessStatusDefaults.shapes
    val colors = SuccessStatusDefaults.colors

    Row(
        modifier = modifier
            .semantics(
                mergeDescendants = true,
                properties = { /* no-op */ },
            )
            .heightIn(min = sizes.minHeight)
            .background(colors.container, shapes.container)
            .padding(NdpaTheme.spacing.lg),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = sizes.gap,
            alignment = Alignment.CenterHorizontally,
        ),
    ) {
        Box(
            modifier = Modifier
                .size(sizes.indicator)
                .background(colors.indicator, shapes.indicator),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = NdpaIcons.Check,
                contentDescription = null,
                modifier = Modifier.size(sizes.check),
                tint = colors.check,
            )
        }
        Text(
            text = text,
            style = SuccessStatusDefaults.textStyle,
            color = colors.content,
            textAlign = TextAlign.Center,
            maxLines = 2,
        )
    }
}

internal object SuccessStatusDefaults {

    val sizes: SuccessStatusSizes = SuccessStatusSizes(
        minHeight = 56.dp,
        indicator = 24.dp,
        check = 14.dp,
        gap = 10.dp,
    )

    val shapes: SuccessStatusShapes
        @Composable
        @ReadOnlyComposable
        get() = SuccessStatusShapes(
            container = NdpaTheme.shapes.md,
            indicator = NdpaTheme.shapes.full,
        )

    val colors: SuccessStatusColors
        @Composable
        @ReadOnlyComposable
        get() = with(NdpaTheme.colors) {
            SuccessStatusColors(
                container = okSoft,
                indicator = ok,
                check = bg,
                content = ok,
            )
        }

    val textStyle: TextStyle
        @Composable
        @ReadOnlyComposable
        get() = NdpaTheme.typography.body.copy(fontWeight = FontWeight.Medium)
}

@Immutable
internal data class SuccessStatusSizes(
    val minHeight: Dp,
    val indicator: Dp,
    val check: Dp,
    val gap: Dp,
)

@Immutable
internal data class SuccessStatusShapes(
    val container: Shape,
    val indicator: Shape,
)

@Immutable
internal data class SuccessStatusColors(
    val container: Color,
    val indicator: Color,
    val check: Color,
    val content: Color,
)

@Preview
@Composable
private fun SuccessStatusPreview() {
    ThemedPreviews {
        Column(
            modifier = Modifier.padding(NdpaTheme.spacing.x2l),
            verticalArrangement = Arrangement.spacedBy(NdpaTheme.spacing.lg),
        ) {
            SuccessStatus(text = "Access granted", modifier = Modifier.fillMaxWidth())
            SuccessStatus(text = "Access granted")
        }
    }
}
