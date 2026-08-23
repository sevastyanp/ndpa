package com.panov.sevastyan.ndpa.core.ui.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.panov.sevastyan.ndpa.core.design.theme.NdpaTheme

private val VariantGap: Dp = 16.dp

@Composable
fun ThemedPreviews(content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(VariantGap)) {
        PreviewVariant(name = "Light", darkTheme = false, content = content)
        PreviewVariant(name = "Dark", darkTheme = true, content = content)
    }
}

@Composable
private fun PreviewVariant(
    name: String,
    darkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    NdpaTheme(darkTheme = darkTheme) {
        Column(modifier = Modifier.background(color = NdpaTheme.colors.bg)) {
            Text(
                text = name,
                modifier = Modifier.padding(
                    horizontal = NdpaTheme.spacing.x2l,
                    vertical = NdpaTheme.spacing.sm,
                ),
                style = NdpaTheme.typography.label,
                color = NdpaTheme.colors.textMuted,
            )
            content()
        }
    }
}
