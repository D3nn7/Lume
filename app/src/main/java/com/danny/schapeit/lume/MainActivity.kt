package com.danny.schapeit.lume

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.TransitionManager
import androidx.transition.Visibility
import com.danny.schapeit.lume.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.alarmItem.root.setOnClickListener {

            TransitionManager.beginDelayedTransition(binding.alarmItem.alarmItemControlLinearlayout)

            if (binding.alarmItem.alarmItemControlLinearlayout.visibility == View.GONE) {
                binding.alarmItem.alarmItemControlLinearlayout.visibility = View.VISIBLE
                binding.alarmItem.alarmItemLabelIcon.visibility = View.VISIBLE
            } else {
                binding.alarmItem.alarmItemControlLinearlayout.visibility = View.GONE
                binding.alarmItem.alarmItemLabelIcon.visibility = View.GONE
            }
        }
    }
}