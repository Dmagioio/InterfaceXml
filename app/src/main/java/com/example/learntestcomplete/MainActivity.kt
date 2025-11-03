package com.example.learntestcomplete

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learntestcomplete.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator
import android.animation.ObjectAnimator
import android.animation.AnimatorSet
import android.content.Intent
import android.view.animation.AnimationUtils


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

        val moveX = ObjectAnimator.ofFloat(binding.buttonShow, "translationX", 0f, 300f)
        val rotate = ObjectAnimator.ofFloat(binding.buttonShow, "rotation", 0f, 60f)
        val fade = ObjectAnimator.ofFloat(binding.buttonShow, "alpha", 1f, 0.5f, 1f)
        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        setSupportActionBar(binding.toolbar)

        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
        binding.toolbar.setNavigationOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.END)) {
                binding.drawerLayout.closeDrawer(GravityCompat.END)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.END)
            }
        }

        val fruits = listOf("Яблуко", "Банан", "Апельсин", "Ківі", "Виноград", "Яблуко", "Банан", "Апельсин", "Ківі", "Виноград"  )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = FruitAdapter(fruits)

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> showToast("Головна")
                R.id.nav_profile -> {
                    val intent = Intent(this, LearnLayouts::class.java)
                    startActivity(intent)
                }
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

        binding.buttonShow.setOnClickListener {
            AnimatorSet().apply {
                playTogether(moveX, rotate, fade)
                duration = 1500
                start()
            }
        }

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        binding.btnShowSheet.setOnClickListener {
            binding.btnShowSheet.startAnimation(anim)

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

class FruitAdapter(private val fruits: List<String>) :
    RecyclerView.Adapter<FruitAdapter.FruitViewHolder>() {

    class FruitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fruit, parent, false)
        return FruitViewHolder(view)
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        holder.textView.text = fruits[position]
    }

    override fun getItemCount() = fruits.size
}
