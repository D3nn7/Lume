package com.danny.schapeit.lume

import android.content.Context
import android.graphics.Typeface
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isGone
import androidx.fragment.app.FragmentManager
import androidx.transition.TransitionManager
import com.danny.schapeit.lume.databinding.AlarmItemBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


class AlarmItemView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var data: AlarmData

    private val binding: AlarmItemBinding = AlarmItemBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setData(data: AlarmData,
                parentFragmentManager: FragmentManager) {

        this.data = data

        binding.alarmItemTimeTextview.text = data.time.toString()
        binding.alarmItemLabelTextview.text = data.label
        binding.alarmItemToneTextview.text = data.tone.displayName

        if (data.isActive) {
            binding.alarmItemTimeTextview.setTypeface(null, Typeface.BOLD);
        } else {
            binding.alarmItemTimeTextview.setTypeface(null, Typeface.NORMAL);
        }

        binding.alarmItemSwitch.isChecked = data.isActive

        val recurringWeekdayStringBuilder = StringBuilder()

        for (weekday in data.recurringDays) {
            if (data.recurringDays.indexOf(weekday) == 0) {
                recurringWeekdayStringBuilder.append(weekday.toTwoLineString())
            } else {
                recurringWeekdayStringBuilder.append(", ${weekday.toTwoLineString()}")
            }
        }

        binding.alarmItemWeekdaysTextview.text = recurringWeekdayStringBuilder

        changeVibrationCheckCircle()

        binding.alarmItemSwitch.setOnClickListener {
            data.isActive = !data.isActive
            setData(
                data = data,
                parentFragmentManager = parentFragmentManager
            )
        }

        binding.alarmItem.setOnClickListener {
            showOrHideAlarmOptions()
        }

        binding.alarmItemVibrationRelativelayout.setOnClickListener {
            data.useVibration = !data.useVibration
            onVibrateOptionClicked()
        }

        binding.alarmItemTimeTextview.setOnClickListener {
            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(6)
                .setMinute(0)
                .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
                .setTitleText("Select time for alarm")
                .build()

            picker.show(parentFragmentManager, "ALARM_SELECT_TIME")

            if (binding.alarmItemControlLinearlayout.isGone) {
                showOrHideAlarmOptions()
            }
        }
    }

    private fun onVibrateOptionClicked() {
        if(data.useVibration) {
            getSystemService(context, Vibrator::class.java)?.vibrate(
                VibrationEffect.createOneShot(
                    250,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        }

        changeVibrationCheckCircle()
    }

    private fun changeVibrationCheckCircle() {
        binding.alarmItemVibrationActiveCheckboxImageview.setImageDrawable(
            AppCompatResources.getDrawable(
                context,
                if(data.useVibration)
                    R.drawable.ic_circle_checked_24
                else
                    R.drawable.ic_circle_unchecked_24
            )
        )
    }

    private fun showOrHideAlarmOptions() {
        TransitionManager.beginDelayedTransition(binding.alarmItemControlLinearlayout)

        if (binding.alarmItemControlLinearlayout.isGone) {
            binding.alarmItemControlLinearlayout.visibility = View.VISIBLE
            binding.alarmItemLabelIcon.visibility = View.VISIBLE
        } else {
            binding.alarmItemControlLinearlayout.visibility = View.GONE
            binding.alarmItemLabelIcon.visibility = View.GONE
        }
    }
}
