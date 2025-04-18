package com.danny.schapeit.lume

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.danny.schapeit.lume.databinding.FragmentAboutBinding

class AboutFragment: Fragment() {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onStart() {
        super.onStart()

        val sb = StringBuilder("Used Sounds")

        for (alarmTone in AlarmTone.entries) {
            sb.append("\n- ${alarmTone.displayName} by ${alarmTone.author}")
        }

        binding.aboutFragmentLicensesTextview.text = sb
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}