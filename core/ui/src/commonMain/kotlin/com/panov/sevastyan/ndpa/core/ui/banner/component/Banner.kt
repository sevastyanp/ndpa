package com.panov.sevastyan.ndpa.core.ui.banner.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.panov.sevastyan.ndpa.core.design.icon.AlertCircle
import com.panov.sevastyan.ndpa.core.design.icon.NdpaIcons
import com.panov.sevastyan.ndpa.core.design.theme.NdpaTheme
import com.panov.sevastyan.ndpa.core.ui.preview.ThemedPreviews

@Composable
fun Banner(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
) {
    val sizes = BannerDefaults.sizes
    val shapes = BannerDefaults.shapes
    val colors = BannerDefaults.colors

    Row(
        modifier = modifier
            .semantics(
                mergeDescendants = true,
                properties = { /* no-op */ },
            )
            .background(
                color = colors.container,
                shape = shapes.container,
            )
            .border(
                width = sizes.borderWidth,
                color = colors.border,
                shape = shapes.container,
            )
            .padding(all = NdpaTheme.spacing.lg),
        horizontalArrangement = Arrangement.spacedBy(NdpaTheme.spacing.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(size = sizes.icon),
            tint = colors.icon,
        )
        Text(
            text = text,
            style = BannerDefaults.textStyle,
            color = colors.content,
        )
    }
}

internal object BannerDefaults {

    val sizes: BannerSizes = BannerSizes(
        icon = 32.dp,
        borderWidth = 1.dp,
    )

    val shapes: BannerShapes
        @Composable
        @ReadOnlyComposable
        get() = BannerShapes(
            container = NdpaTheme.shapes.lg,
        )

    val colors: BannerColors
        @Composable
        @ReadOnlyComposable
        get() = with(NdpaTheme.colors) {
            BannerColors(
                container = surface,
                border = line,
                icon = accent,
                content = text,
            )
        }

    val textStyle: TextStyle
        @Composable
        @ReadOnlyComposable
        get() = NdpaTheme.typography.title.copy(fontWeight = FontWeight.Normal)
}

@Immutable
internal data class BannerSizes(
    val icon: Dp,
    val borderWidth: Dp,
)

@Immutable
internal data class BannerShapes(
    val container: Shape,
)

@Immutable
internal data class BannerColors(
    val container: Color,
    val border: Color,
    val icon: Color,
    val content: Color,
)

@Preview
@Composable
private fun BannerPreview() {
    ThemedPreviews {
        Column(
            modifier = Modifier.padding(all = NdpaTheme.spacing.x2l),
            verticalArrangement = Arrangement.spacedBy(NdpaTheme.spacing.lg),
        ) {
            Banner(
                icon = NdpaIcons.AlertCircle,
                text = "Blocking is off. Grant access again",
                modifier = Modifier.fillMaxWidth(),
            )
            Banner(
                icon = NdpaIcons.AlertCircle,
                text = "Session ended",
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
