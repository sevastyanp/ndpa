package com.panov.sevastyan.ndpa.core.design.spacing

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** 4dp grid; Figma tokens space4…space40. */
@Immutable
data class NdpaSpacing(
    val xs: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 12.dp,
    val lg: Dp = 16.dp,
    val xl: Dp = 20.dp,
    val x2l: Dp = 24.dp,
    val x3l: Dp = 32.dp,
    val x4l: Dp = 40.dp,
)

internal val DefaultNdpaSpacing = NdpaSpacing()
