package com.danny.schapeit.lume

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

data class AlarmData(
    val time: LocalTime,
    val label: String?,
    val tone: AlarmTone,
    var useVibration: Boolean,
    val recurringDays: Set<DayOfWeek> = setOf(),
    var isActive: Boolean,
    val createdAt: LocalDateTime,
)
