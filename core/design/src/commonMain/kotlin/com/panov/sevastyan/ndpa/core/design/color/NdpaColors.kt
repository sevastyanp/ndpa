package com.panov.sevastyan.ndpa.core.design.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class NdpaColors(
    /** Screen background. */
    val bg: Color,
    /** Sunken areas, disabled fill. */
    val bgSunken: Color,
    /** Sheets, cards, banners. */
    val surface: Color,
    /** Warm base of the wheel disc. */
    val accentWash: Color,
    /** 1dp hairline. */
    val line: Color,
    /** Strong divider / outline. */
    val lineStrong: Color,
    /** Primary text. */
    val text: Color,
    /** Secondary text. */
    val textMuted: Color,
    /** Tertiary / disabled text. */
    val textFaint: Color,
    /** Activity & attention: arc, primary button, selected chip. */
    val accent: Color,
    /** Soft accent fill (banner). */
    val accentSoft: Color,
    /** Muted accent fill (swipe start tint). */
    val accentMuted: Color,
    /** Text/icon on accent fill. */
    val onAccent: Color,
    /** Post-factum success status. */
    val ok: Color,
    /** Success status surface. */
    val okSoft: Color,
    /** Alert status. */
    val alert: Color,
    /** Alert status surface. */
    val alertSoft: Color,
    /** Running meeting card fill. */
    val cardRunning: Color,
    /** Upcoming meeting card fill. */
    val cardUpcoming: Color,
    /** Free meeting card fill. */
    val cardFree: Color,
    /** Scrim under sheets. */
    val scrim: Color,
)

internal val LightNdpaColors = NdpaColors(
    bg = Paper,
    bgSunken = PaperSunken,
    surface = White,
    accentWash = EmberWash,
    line = Line,
    lineStrong = LineStrong,
    text = Ink,
    textMuted = InkMuted,
    textFaint = InkFaint,
    accent = Ember,
    accentSoft = EmberSoft,
    accentMuted = EmberMuted,
    onAccent = White,
    ok = Moss,
    okSoft = MossSoft,
    alert = Alert,
    alertSoft = AlertSoft,
    cardRunning = CardRunning,
    cardUpcoming = CardUpcoming,
    cardFree = CardFree,
    scrim = Scrim,
)

// TODO(dark-theme): the dark theme is not designed yet. Until it is, dark is a
// pixel-for-pixel alias of light so that call sites and the theme switch are already wired.
internal val DarkNdpaColors: NdpaColors = LightNdpaColors
