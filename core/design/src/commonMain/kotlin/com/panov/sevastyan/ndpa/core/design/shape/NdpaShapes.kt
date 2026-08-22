package com.panov.sevastyan.ndpa.core.design.shape

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

@Immutable
data class NdpaShapes(
    /** Small plates. */
    val sm: CornerBasedShape = RoundedCornerShape(8.dp),
    /** Chips, inputs, rows. */
    val md: CornerBasedShape = RoundedCornerShape(12.dp),
    /** Buttons. */
    val lg: CornerBasedShape = RoundedCornerShape(14.dp),
    /** Sheet top. */
    val xl: CornerBasedShape = RoundedCornerShape(24.dp),
    /** Ring, dot. */
    val full: CornerBasedShape = CircleShape,
)

internal val DefaultNdpaShapes = NdpaShapes()
