package com.panov.sevastyan.ndpa.core.ui.timer

import kotlin.time.Duration

internal fun Duration.format(): String = toComponents { days, hours, minutes, seconds, _ ->
    val mm = minutes.padded()
    val ss = seconds.padded()

    when {
        days > 0 -> "$days:${hours.padded()}:$mm:$ss"
        hours > 0 -> "$hours:$mm:$ss"
        else -> "$mm:$ss"
    }
}

internal data class TimerDigits(
    val hours: String,
    val minutes: String,
    val seconds: String,
)

internal fun Duration.toTimerDigits(): TimerDigits = toComponents { hours, minutes, seconds, _ ->
    TimerDigits(
        hours = hours.padded(),
        minutes = minutes.padded(),
        seconds = seconds.padded(),
    )
}

internal fun Int.padded(): String = toString().padStart(2, '0')

internal fun Long.padded(): String = toString().padStart(2, '0')
