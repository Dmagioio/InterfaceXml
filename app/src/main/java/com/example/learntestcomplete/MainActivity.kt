package com.example.learntestcomplete

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.learntestcomplete.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        Glide.with(this)
            .load("https://www.nylabone.com/-/media/project/oneweb/nylabone/images/dog101/10-intelligent-dog-breeds/golden-retriever-tongue-out.jpg?h=430&w=710&hash=7FEB820D235A44B76B271060E03572C7")
            .into(binding.imageView)

        binding.imageView.alpha = 0f
        ObjectAnimator.ofFloat(binding.imageView, "alpha", 0f, 1f).apply {
            duration = 1000
            start()
        }

        binding.btnClick.setOnClickListener {
            binding.tvReplacementWord.text = "Good job!"
            binding.tvReplacementAndroid.text = "Good job!"

            lifecycleScope.launch {
                var timerValue = binding.tvTimer.text.toString().toIntOrNull() ?: 0
                while (timerValue < 1000) {
                    delay(1000L)
                    timerValue++
                    binding.tvTimer.text = timerValue.toString()
                }
            }

            ObjectAnimator.ofFloat(binding.tvReplacementWord, "alpha", 0f, 1f).apply {
                duration = 1000
                start()
            }

            val slideUpFadeInAnimation =
                AnimationUtils.loadAnimation(this, R.anim.slide_up_and_fade_in)
            binding.tvReplacementAndroid.startAnimation(slideUpFadeInAnimation)

            binding.imageView.setImageResource(R.drawable.cat)
            ObjectAnimator.ofFloat(binding.imageView, "alpha", 0f, 1f).apply {
                duration = 1000
                start()
            }
        }

        binding.btnClick2.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.dog)
            ObjectAnimator.ofFloat(binding.imageView, "alpha", 0f, 1f).apply {
                duration = 1000
                start()
            }
        }

        binding.imageView.setOnClickListener {
            Glide.with(this)
                .asGif()
                .load(R.drawable.cat2)
                .into(binding.imageView)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, ExampleFragment())
            .commit()
    }
}