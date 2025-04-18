package com.danny.schapeit.lume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionManager
import com.danny.schapeit.lume.databinding.FragmentMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onStart() {
        super.onStart()

        //TODO: Only test code. remove after correct implementation
        binding.alarmItem.root.setOnClickListener {

            showOrHideAlarmOptions()
        }

        binding.alarmItem.alarmItemTimeTextview.setOnClickListener {
            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(6)
                .setMinute(0)
                .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
                .setTitleText("Select time for alarm")
                .build()

            picker.show(parentFragmentManager, "ALARM_SELECT_TIME")

            if (binding.alarmItem.alarmItemControlLinearlayout.isGone) {
                showOrHideAlarmOptions()
            }
        }

        binding.topAppBar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.about -> {
                    findNavController().navigate(R.id.action_mainFragment_to_aboutFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun showOrHideAlarmOptions() {
        TransitionManager.beginDelayedTransition(binding.alarmItem.alarmItemControlLinearlayout)

        if (binding.alarmItem.alarmItemControlLinearlayout.isGone) {
            binding.alarmItem.alarmItemControlLinearlayout.visibility = View.VISIBLE
            binding.alarmItem.alarmItemLabelIcon.visibility = View.VISIBLE
        } else {
            binding.alarmItem.alarmItemControlLinearlayout.visibility = View.GONE
            binding.alarmItem.alarmItemLabelIcon.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}