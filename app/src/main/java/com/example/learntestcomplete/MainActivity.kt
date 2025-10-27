package com.example.learntestcomplete

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.learntestcomplete.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>


    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
        binding.toolbar.setNavigationOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.END)) {
                binding.drawerLayout.closeDrawer(GravityCompat.END)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.END)
            }
        }

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> showToast("Головна")
                R.id.nav_profile -> showToast("Профіль")
                R.id.nav_settings -> showToast("Налаштування")
            }
            binding.drawerLayout.closeDrawer(GravityCompat.END)
            true
        }

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
    }
}
