package com.panov.sevastyan.ndpa.core.design.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.text.font.FontFamily
import com.panov.sevastyan.ndpa.core.design.color.DarkNdpaColors
import com.panov.sevastyan.ndpa.core.design.color.LightNdpaColors
import com.panov.sevastyan.ndpa.core.design.color.NdpaColors
import com.panov.sevastyan.ndpa.core.design.shape.DefaultNdpaShapes
import com.panov.sevastyan.ndpa.core.design.shape.NdpaShapes
import com.panov.sevastyan.ndpa.core.design.typography.NdpaTypography

internal fun NdpaColors.toMaterialColorScheme(darkTheme: Boolean): ColorScheme =
    (if (darkTheme) darkColorScheme() else lightColorScheme()).copy(
        primary = accent,
        onPrimary = onAccent,
        primaryContainer = accentSoft,
        onPrimaryContainer = text,
        inversePrimary = accentSoft,
        secondary = accent,
        onSecondary = onAccent,
        secondaryContainer = accentSoft,
        onSecondaryContainer = text,
        tertiary = accent,
        onTertiary = onAccent,
        tertiaryContainer = accentSoft,
        onTertiaryContainer = text,
        background = bg,
        onBackground = text,
        surface = bg,
        onSurface = text,
        surfaceVariant = bgSunken,
        onSurfaceVariant = textMuted,
        // That copy() does not re-apply the factories' "surfaceTint defaults to primary" rule,
        // so it must be set explicitly.
        surfaceTint = accent,
        // Snackbar is the only dark plate: bg=text, text=bg
        inverseSurface = text,
        inverseOnSurface = bg,
        // No separate error color by design: error uses accent
        error = accent,
        onError = onAccent,
        errorContainer = accentSoft,
        onErrorContainer = text,
        outline = lineStrong,
        outlineVariant = line,
        scrim = scrim,
        surfaceContainerLowest = surface,
        surfaceContainerLow = surface,
        surfaceContainer = surface,
        surfaceContainerHigh = surface,
        surfaceContainerHighest = bgSunken,
        surfaceBright = surface,
        surfaceDim = bgSunken,
        primaryFixed = accentSoft,
        primaryFixedDim = accentSoft,
        onPrimaryFixed = text,
        onPrimaryFixedVariant = text,
        secondaryFixed = accentSoft,
        secondaryFixedDim = accentSoft,
        onSecondaryFixed = text,
        onSecondaryFixedVariant = text,
        tertiaryFixed = accentSoft,
        tertiaryFixedDim = accentSoft,
        onTertiaryFixed = text,
        onTertiaryFixedVariant = text,
    )

internal fun NdpaTypography.toMaterialTypography(fontFamily: FontFamily): Typography = Typography(
    fontFamily = fontFamily,
    displayLarge = numeral,
    displayMedium = numeral,
    displaySmall = numeralSmall,
    headlineLarge = headline,
    headlineMedium = headline,
    headlineSmall = headline,
    titleLarge = title,
    titleMedium = title,
    titleSmall = title,
    bodyLarge = bodyLarge,
    bodyMedium = body,
    bodySmall = body,
    labelLarge = button,
    labelMedium = label,
    labelSmall = label,
)

internal fun NdpaShapes.toMaterialShapes(): Shapes = Shapes(
    extraSmall = sm,
    small = sm,
    medium = md,
    large = lg,
    extraLarge = xl,
)

internal val NdpaLightColorScheme = LightNdpaColors.toMaterialColorScheme(darkTheme = false)
internal val NdpaDarkColorScheme = DarkNdpaColors.toMaterialColorScheme(darkTheme = true)
internal val NdpaMaterialShapes = DefaultNdpaShapes.toMaterialShapes()
