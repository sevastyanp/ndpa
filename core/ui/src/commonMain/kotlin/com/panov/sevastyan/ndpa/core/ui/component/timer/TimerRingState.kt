package com.panov.sevastyan.ndpa.core.ui.component.timer

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import com.panov.sevastyan.ndpa.core.design.icon.Clock
import com.panov.sevastyan.ndpa.core.design.icon.Infinity
import com.panov.sevastyan.ndpa.core.design.icon.NdpaIcons
import com.panov.sevastyan.ndpa.core.strings.generated.resources.Res
import com.panov.sevastyan.ndpa.core.strings.generated.resources.timer_caption_in_focus
import com.panov.sevastyan.ndpa.core.strings.generated.resources.timer_caption_remaining
import com.panov.sevastyan.ndpa.core.strings.generated.resources.timer_switch_to_timed
import com.panov.sevastyan.ndpa.core.strings.generated.resources.timer_switch_to_untimed
import org.jetbrains.compose.resources.StringResource
import kotlin.time.Duration

@Immutable
sealed interface TimerRingState {

    @Immutable
    sealed interface Running : TimerRingState {
        data class Timed(
            val remaining: Duration,
            val total: Duration,
        ) : Running

        data class Untimed(
            val elapsed: Duration,
        ) : Running
    }

    @Immutable
    sealed interface Idle : TimerRingState {
        data object Untimed : Idle

        data class Timed(
            val duration: Duration,
            val selected: TimerRingSection?,
        ) : Idle
    }
}

internal val TimerRingState.Running.progress: Float
    get() = when (this) {
        is TimerRingState.Running.Timed ->
            if (total > Duration.ZERO) (1 - remaining / total).toFloat().coerceIn(0f, 1f) else 1f

        is TimerRingState.Running.Untimed -> 1f
    }

internal val TimerRingState.Running.duration: Duration
    get() = when (this) {
        is TimerRingState.Running.Timed -> remaining
        is TimerRingState.Running.Untimed -> elapsed
    }

internal val TimerRingState.Running.caption: StringResource
    get() = when (this) {
        is TimerRingState.Running.Timed -> Res.string.timer_caption_remaining
        is TimerRingState.Running.Untimed -> Res.string.timer_caption_in_focus
    }

internal val TimerRingState.Idle.modeToggleIcon: ImageVector
    get() = when (this) {
        is TimerRingState.Idle.Untimed -> NdpaIcons.Clock
        is TimerRingState.Idle.Timed -> NdpaIcons.Infinity
    }

internal val TimerRingState.Idle.modeToggleDescription: StringResource
    get() = when (this) {
        is TimerRingState.Idle.Untimed -> Res.string.timer_switch_to_timed
        is TimerRingState.Idle.Timed -> Res.string.timer_switch_to_untimed
    }
