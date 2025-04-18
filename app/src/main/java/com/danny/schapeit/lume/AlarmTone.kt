package com.danny.schapeit.lume

import androidx.annotation.RawRes

enum class AlarmTone(val displayName: String, @RawRes val resource: Int, val author: String) {
    LOFI_ALARM("Lofi Alarm", R.raw.lofi_alarm, "Lesiakower")
}