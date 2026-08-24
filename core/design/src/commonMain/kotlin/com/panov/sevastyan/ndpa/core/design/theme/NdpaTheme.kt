package com.panov.sevastyan.ndpa.core.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.panov.sevastyan.ndpa.core.design.color.DarkNdpaColors
import com.panov.sevastyan.ndpa.core.design.color.LightNdpaColors
import com.panov.sevastyan.ndpa.core.design.color.NdpaColors
import com.panov.sevastyan.ndpa.core.design.elevation.DefaultNdpaElevation
import com.panov.sevastyan.ndpa.core.design.elevation.NdpaElevation
import com.panov.sevastyan.ndpa.core.design.font.ndpaFontFamily
import com.panov.sevastyan.ndpa.core.design.shape.DefaultNdpaShapes
import com.panov.sevastyan.ndpa.core.design.shape.NdpaShapes
import com.panov.sevastyan.ndpa.core.design.size.DefaultNdpaSizes
import com.panov.sevastyan.ndpa.core.design.size.NdpaSizes
import com.panov.sevastyan.ndpa.core.design.spacing.DefaultNdpaSpacing
import com.panov.sevastyan.ndpa.core.design.spacing.NdpaSpacing
import com.panov.sevastyan.ndpa.core.design.typography.NdpaTypography
import com.panov.sevastyan.ndpa.core.design.typography.ndpaTypography

private fun noTheme(name: String): Nothing = error("No $name provided. Wrap the content in `NdpaTheme { ... }`.")

internal val LocalNdpaColors = staticCompositionLocalOf<NdpaColors> { noTheme("`NdpaColors`") }
internal val LocalNdpaTypography = staticCompositionLocalOf<NdpaTypography> { noTheme("`NdpaTypography`") }
internal val LocalNdpaShapes = staticCompositionLocalOf<NdpaShapes> { noTheme("`NdpaShapes`") }
internal val LocalNdpaSpacing = staticCompositionLocalOf<NdpaSpacing> { noTheme("`NdpaSpacing`") }
internal val LocalNdpaSizes = staticCompositionLocalOf<NdpaSizes> { noTheme("`NdpaSizes`") }
internal val LocalNdpaElevation = staticCompositionLocalOf<NdpaElevation> { noTheme("`NdpaElevation`") }

@Composable
fun NdpaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) DarkNdpaColors else LightNdpaColors

    val fontFamily = ndpaFontFamily()
    val typography = ndpaTypography(fontFamily)

    CompositionLocalProvider(
        LocalNdpaColors provides colors,
        LocalNdpaTypography provides typography,
        LocalNdpaShapes provides DefaultNdpaShapes,
        LocalNdpaSpacing provides DefaultNdpaSpacing,
        LocalNdpaSizes provides DefaultNdpaSizes,
        LocalNdpaElevation provides DefaultNdpaElevation,
        LocalContentColor provides colors.text,
    ) {
        MaterialTheme(
            colorScheme = if (darkTheme) NdpaDarkColorScheme else NdpaLightColorScheme,
            typography = typography.toMaterialTypography(fontFamily),
            shapes = NdpaMaterialShapes,
            content = content,
        )
    }
}

object NdpaTheme {

    val colors: NdpaColors
        @Composable
        @ReadOnlyComposable
        get() = LocalNdpaColors.current

    val typography: NdpaTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalNdpaTypography.current

    val shapes: NdpaShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalNdpaShapes.current

    val spacing: NdpaSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalNdpaSpacing.current

    val sizes: NdpaSizes
        @Composable
        @ReadOnlyComposable
        get() = LocalNdpaSizes.current

    val elevation: NdpaElevation
        @Composable
        @ReadOnlyComposable
        get() = LocalNdpaElevation.current
}
