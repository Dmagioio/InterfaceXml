package com.example.learntestcomplete

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.learntestcomplete.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val name = s.toString().trim()

                if (name.isEmpty()) {
                    binding.editTextName.error = "Введіть ім'я"
                    binding.textViewResult.text = ""
                } else if (name.length < 3) {
                    binding.editTextName.error = "Мінімум 3 символи"
                    binding.textViewResult.text = ""
                } else {
                    binding.editTextName.error = null
                    binding.textViewResult.text = "Привіт, $name!"
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        binding.btnShowSheet.setOnClickListener {
            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            }
        }

        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> binding.tvTitle.text = "Розгорнуто 🔼"
                    BottomSheetBehavior.STATE_COLLAPSED -> binding.tvTitle.text = "Згорнуто 🔽"
                    BottomSheetBehavior.STATE_HIDDEN -> binding.tvTitle.text = "Сховано ❌"
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })

        val adapter = TabsAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Головна"
                1 -> tab.text = "Профіль"
                2 -> tab.text = "Налаштування"
            }
        }.attach()
    }
}