package com.panov.sevastyan.ndpa.core.design.font

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ndpa.core.design.generated.resources.Res
import ndpa.core.design.generated.resources.golos_text_medium
import ndpa.core.design.generated.resources.golos_text_regular
import org.jetbrains.compose.resources.Font

@Composable
internal fun golosTextFontFamily(): FontFamily = FontFamily(
    Font(Res.font.golos_text_regular, FontWeight.Normal),
    Font(Res.font.golos_text_medium, FontWeight.Medium),
)
