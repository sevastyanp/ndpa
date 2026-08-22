package com.panov.sevastyan.ndpa.core.design.elevation

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

/**
 * Flat system: shadows exist only on the bottom sheet and the Android overlay
 * (Wheel/Ring shadows are component-specific and live with the component).
 */
@Immutable
data class NdpaElevation(
    /** Bottom sheet. */
    val sheet: Shadow = Shadow(
        radius = 32.dp,
        color = Color.Black,
        offset = DpOffset(0.dp, 8.dp),
        alpha = 0.10f,
    ),
    /** Android overlay. */
    val overlay: Shadow = Shadow(
        radius = 40.dp,
        color = Color.Black,
        offset = DpOffset(0.dp, 0.dp),
        alpha = 0.12f,
    ),
)

internal val DefaultNdpaElevation = NdpaElevation()
