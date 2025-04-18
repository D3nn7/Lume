package com.danny.schapeit.lume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.danny.schapeit.lume.databinding.FragmentMainBinding
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

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

        context?.let {
            val testAlarmView = AlarmItemView(it)

            testAlarmView.setData(
                AlarmData(
                    time = LocalTime.MIDNIGHT,
                    label = "Call John Doe",
                    tone = AlarmTone.LOFI_ALARM,
                    useVibration = true,
                    recurringDays = setOf(
                        DayOfWeek.MONDAY,
                        DayOfWeek.THURSDAY,
                        DayOfWeek.SATURDAY
                    ),
                    isActive = true,
                    createdAt = LocalDateTime.now()
                ),
                parentFragmentManager = parentFragmentManager
            )

            binding.mainFragmentContentLinearlayout.addView(testAlarmView)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}