package com.panov.sevastyan.ndpa.core.design.size

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class NdpaSizes(
    /** Standard icon. */
    val icon: Dp = 24.dp,
    /** Minimum interactive area. */
    val tapTarget: Dp = 48.dp,
    /** Divider thickness (color `line`). */
    val hairline: Dp = 1.dp,
    /** Horizontal screen/sheet padding. */
    val screenPadding: Dp = 24.dp,
)

internal val DefaultNdpaSizes = NdpaSizes()
