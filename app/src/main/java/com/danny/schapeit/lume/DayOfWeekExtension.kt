package com.danny.schapeit.lume

import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

fun DayOfWeek.toTwoLineString(locale: Locale = Locale.getDefault()): String {
    return this.getDisplayName(TextStyle.SHORT, locale)
}